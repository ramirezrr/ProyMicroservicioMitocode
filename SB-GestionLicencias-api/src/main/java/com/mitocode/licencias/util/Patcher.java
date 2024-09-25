package com.mitocode.licencias.util;

import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.model.TipoLicencia;
import com.mitocode.licencias.model.Titular;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {

    public static void licenciaPatcher(Object existingObject, Object incompleteObject) throws IllegalAccessException {
        Class<?> objectClass = existingObject.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(incompleteObject);

            if (value != null) {
                // Si el valor es una instancia de Titular o TipoLicencia, realizar el parcheo recursivamente
                if (field.getType().equals(Titular.class) || field.getType().equals(TipoLicencia.class)) {
                    Object existingNestedObject = field.get(existingObject);
                    licenciaPatcher(existingNestedObject, value); // Parchar recursivamente los objetos anidados
                } else {
                    // Si es un campo simple, actualizar normalmente
                    field.set(existingObject, value);
                }
            }
            field.setAccessible(false);
        }
    }
}
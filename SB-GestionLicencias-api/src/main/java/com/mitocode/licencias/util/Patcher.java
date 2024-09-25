package com.mitocode.licencias.util;

import com.mitocode.licencias.model.Licencia;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public static void licenciaPatcher(Licencia existingLicencia, Licencia incompleteLicencia) throws IllegalAccessException {
        Class<?> licenciaClass = Licencia.class;
        Field[] licenciaFields = licenciaClass.getDeclaredFields();

        for (Field field : licenciaFields) {
            field.setAccessible(true);
            Object value = field.get(incompleteLicencia);
            if (value != null) {
                field.set(existingLicencia, value);
            }
            field.setAccessible(false);
        }
    }
}

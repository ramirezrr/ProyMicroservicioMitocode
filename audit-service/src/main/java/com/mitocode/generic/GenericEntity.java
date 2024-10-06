package com.mitocode.generic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
public class GenericEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1256654654L;

    private T t;
    private String className;

    public GenericEntity(T obj, String className) {
        this.t = obj;
        this.className = className;
    }


}

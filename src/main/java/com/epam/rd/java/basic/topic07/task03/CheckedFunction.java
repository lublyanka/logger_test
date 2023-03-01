package com.epam.rd.java.basic.topic07.task03;

import com.epam.rd.java.basic.topic07.task03.db.DBException;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws DBException;
}

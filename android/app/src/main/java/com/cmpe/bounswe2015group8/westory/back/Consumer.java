package com.cmpe.bounswe2015group8.westory.back;

/**
 * Created by bugrahan on 31/10/15.
 */
public interface Consumer<T> {
    public abstract void accept(T t);
}

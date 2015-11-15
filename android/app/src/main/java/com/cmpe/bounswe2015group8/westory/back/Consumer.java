package com.cmpe.bounswe2015group8.westory.back;

/**
 * Basic Consumer interface from Java 8.
 * Originally implemented by Bugrahan Memis as GetCallback interface, later
 * renamed as Consumer interface to comply with Java 8 standards.
 * @author bugrahan
 * @author xyllan
 * Date: 31/10/15.
 */
public interface Consumer<T> {
    public abstract void accept(T t);
}

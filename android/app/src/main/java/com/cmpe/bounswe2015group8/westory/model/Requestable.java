package com.cmpe.bounswe2015group8.westory.model;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Requestable<T>{
    private String endpoint;
    private Object data;
    private Class<T> dataClass;
    public Requestable(String endpoint, Object data, Class<T> dataClass) {
        this.endpoint = endpoint;
        this.data = data;
        this.dataClass = dataClass;
    }

    public String getEndpoint() {
        return endpoint;
    }
    public Object getData() {
        return data;
    }
    public Class<T> getDataClass() { return dataClass; }
}

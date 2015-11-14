package com.cmpe.bounswe2015group8.westory.model;

import org.apache.http.NameValuePair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Requestable<T>{
    private String endpoint;
    private Map<String,String> data;
    private Class<T> dataClass;
    public Requestable(String endpoint, Map<String,String> data, Class<T> dataClass) {
        this.endpoint = endpoint;
        this.data = data;
        this.dataClass = dataClass;
    }

    public String getEndpoint() {
        return endpoint;
    }
    public Map<String,String> getData() {
        return data;
    }
    public Class<T> getDataClass() { return dataClass; }
}

package com.cmpe.bounswe2015group8.westory.model;

import org.apache.http.NameValuePair;
import java.util.ArrayList;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Requestable {
    private String endpoint;
    private ArrayList<NameValuePair> data;
    public Requestable(String endpoint, ArrayList<NameValuePair> data) {
        this.endpoint = endpoint;
        this.data = data;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public ArrayList<NameValuePair> getData() {
        return data;
    }
}

package com.mugi.uac.utils;

public class RestRequestObject<T> {

    private T object;
    private String searchParams;

    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

}

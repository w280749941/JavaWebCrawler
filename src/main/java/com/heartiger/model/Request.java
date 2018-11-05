package com.heartiger.model;

import com.heartiger.shared.Parser;

public class Request {

    private String url;
    private Parser parser;

    public Request(String url, Parser parser) {
        this.url = url;
        this.parser = parser;
    }

    public String getUrl() {
        return url;
    }

    public Parser getParser() {
        return parser;
    }
}

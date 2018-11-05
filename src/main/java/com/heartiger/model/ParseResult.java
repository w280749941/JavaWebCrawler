package com.heartiger.model;

import java.util.List;

public class ParseResult {

    private List<Request> requests;
    private List<String> items;

    public ParseResult(List<Request> requests, List<String> items) {
        this.requests = requests;
        this.items = items;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<String> getItems() {
        return items;
    }
}

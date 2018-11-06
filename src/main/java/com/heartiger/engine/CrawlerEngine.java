package com.heartiger.engine;

import com.heartiger.shared.Parser;
import com.heartiger.fetcher.Fetcher;
import com.heartiger.model.ParseResult;
import com.heartiger.model.Request;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CrawlerEngine {

    private Fetcher fetcher;

    public CrawlerEngine(Fetcher fetcher) {
        this.fetcher = fetcher;
    }

    public void run(List<Request> requests) {
        Queue<Request> requestQueue = new LinkedList<>(requests);
        while(!requestQueue.isEmpty()){
            Request request = requestQueue.poll();
            try {
                String fetchResult = this.fetcher.fetch(request.getUrl());
                if(fetchResult != null){
                    Parser parser = request.getParser();
                    if(parser == null)
                        continue;
                    ParseResult parseResult = parser.parse(fetchResult);
                    for(Request newRequest: parseResult.getRequests())
                        requestQueue.offer(newRequest);
                }
            } catch (Exception e) {
                System.out.println("Failed to run fetcher on: " + request.getUrl());
            }
        }
    }
}

package com.heartiger.engine;

import com.heartiger.shared.Parser;
import com.heartiger.fetcher.Fetcher;
import com.heartiger.model.ParseResult;
import com.heartiger.model.Request;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrawlerEngine {

    private Fetcher fetcher;

    public CrawlerEngine(Fetcher fetcher) {
        this.fetcher = fetcher;
    }

    public void run(List<Request> requests) {
        Queue<Request> requestQueue = new ConcurrentLinkedQueue<>(requests);
        Map<String, Integer> workers = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        while(!requestQueue.isEmpty() || !workers.isEmpty()){
            executorService.submit(() -> doWork(requestQueue, workers));
        }
        executorService.shutdown();
    }

    private void doWork(Queue<Request> requestQueue, Map<String, Integer> workers) {
        workers.put(Thread.currentThread().getName(), 1);
        Request request = requestQueue.poll();
        synchronized (this){
            if(request == null){
                workers.remove(Thread.currentThread().getName());
                return;
            }
        }
        try {
            String fetchResult = this.fetcher.fetch(request.getUrl());
            if(fetchResult != null){
                Parser parser = request.getParser();
                if(parser == null)
                    return;
                ParseResult parseResult = parser.parse(fetchResult);
                for(Request newRequest: parseResult.getRequests())
                    requestQueue.offer(newRequest);
            }
        } catch (Exception e) {
            System.out.println("Failed to run fetcher on: " + request.getUrl());
            workers.remove(Thread.currentThread().getName());
            return;
        }
        workers.remove(Thread.currentThread().getName());
    }
}

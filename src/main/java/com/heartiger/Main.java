package com.heartiger;

import com.heartiger.engine.CrawlerEngine;
import com.heartiger.fetcher.Fetcher;
import com.heartiger.model.Request;
import com.heartiger.zhenaiparser.CityListParser;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        CrawlerEngine crawlerEngine = new CrawlerEngine(new Fetcher());
        crawlerEngine.run(Collections.singletonList(new Request("http://www.zhenai.com/zhenghun", CityListParser.getParser())));
    }
}

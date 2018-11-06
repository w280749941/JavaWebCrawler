package com.heartiger.zhenaiparser;

import com.heartiger.shared.Parser;
import com.heartiger.model.ParseResult;
import com.heartiger.model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityListParser implements Parser {

    private final static Pattern pattern = Pattern
            .compile("<a href=\"(http://www\\.zhenai\\.com/zhenghun/[0-9a-z]+)\"[^>]*>([^<]+)</a>");

    private final static Parser parser = new CityListParser();

    public static Parser getParser(){
        return parser;
    }

    private CityListParser(){}

    @Override
    public ParseResult parse(String data) {
        Matcher matcher = pattern.matcher(data);
        List<Request> urls = new ArrayList<>();
        List<String> items = new ArrayList<>();
        int limit = 20;
        while(matcher.find())
        {
            urls.add(new Request(matcher.group(1), CityParser.getParser()));
            items.add(matcher.group(2));
            System.out.println("Received city: " + matcher.group(2));
            limit--;
            if(limit == 0)
                break;
        }
        return new ParseResult(urls,items);
    }
}

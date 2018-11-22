package com.heartiger.zhenaiparser;

import com.heartiger.model.ParseResult;
import com.heartiger.model.Request;
import com.heartiger.shared.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityParser implements Parser {

    private final static Pattern pattern = Pattern
            .compile("<th><a href=\"(http://album\\.zhenai\\.com/u/[0-9]+)\" target=\"_blank\">([^<]+)</a></th>");

    private final static Parser parser = new CityParser();

    public static Parser getParser(){
        return parser;
    }

    private CityParser(){}

    @Override
    public ParseResult parse(String data) {
        Matcher matcher = pattern.matcher(data);
        List<Request> urls = new ArrayList<>();
        List<String> items = new ArrayList<>();
        while(matcher.find())
        {
            urls.add(new Request(matcher.group(1), ProfileParser.getParser()));
            items.add(matcher.group(2));
            //System.out.println("Received user: " + matcher.group(2));
        }
        return new ParseResult(urls,items);
    }
}

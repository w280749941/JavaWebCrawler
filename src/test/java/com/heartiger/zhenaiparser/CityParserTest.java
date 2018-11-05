package com.heartiger.zhenaiparser;

import com.heartiger.fetcher.Fetcher;
import com.heartiger.model.ParseResult;
import com.heartiger.shared.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CityParserTest {

    private Fetcher fetcher;
    private Parser parser;

    @Before
    public void setup(){
        this.fetcher = new Fetcher();
        this.parser = CityParser.getParser();
    }

    @Test
    public void CityListParserShouldReturnNotNull() throws IOException {
        String fetchResult = this.fetcher.fetch("http://www.zhenai.com/zhenghun/aba");
        ParseResult parseResult = this.parser.parse(fetchResult);
        Assert.assertNotEquals(parseResult.getItems().size(), 0);
    }
}
package com.heartiger.zhenaiparser;


import com.heartiger.fetcher.Fetcher;
import com.heartiger.model.ParseResult;
import com.heartiger.shared.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CityListParserTest {

    private Fetcher fetcher;
    private Parser parser;

    @Before
    public void setup(){
        this.fetcher = new Fetcher();
        this.parser = CityListParser.getParser();
    }

    @Test
    public void CityListParserShouldReturnNotNull() throws IOException {
        String fetchResult = this.fetcher.fetch("http://www.zhenai.com/zhenghun");
        ParseResult parseResult = this.parser.parse(fetchResult);
        Assert.assertNotEquals(parseResult.getItems().size(), 0);
    }
}

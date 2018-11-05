package com.heartiger.zhenaiparser;

import com.heartiger.fetcher.Fetcher;
import com.heartiger.model.ParseResult;
import com.heartiger.shared.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ProfileParserTest {

    private Fetcher fetcher;
    private Parser parser;

    @Before
    public void setup(){
        this.fetcher = new Fetcher();
        this.parser = ProfileParser.getParser();
    }

    @Test
    public void ProfileParserShouldReturnNotNull() throws IOException {
        String fetchResult = this.fetcher.fetch("http://album.zhenai.com/u/90156322");
        ParseResult parseResult = this.parser.parse(fetchResult);
        Assert.assertNotEquals(parseResult, 0);
    }
}

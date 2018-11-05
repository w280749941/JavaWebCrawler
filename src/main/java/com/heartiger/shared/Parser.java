package com.heartiger.shared;

import com.heartiger.model.ParseResult;

public interface Parser {
    ParseResult parse(String data);
}

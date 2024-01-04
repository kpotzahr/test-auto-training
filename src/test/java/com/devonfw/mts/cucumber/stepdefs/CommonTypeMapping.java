package com.devonfw.mts.cucumber.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class CommonTypeMapping {
    @Autowired
    private ObjectMapper objectMapper;

    @DefaultDataTableEntryTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }
}

package com.coreintegra.pftrust.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T map(String content, Class<T> valueType) throws JsonProcessingException, JsonMappingException {
        return objectMapper.readValue(content, valueType);
    }

}

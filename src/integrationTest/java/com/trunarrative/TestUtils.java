package com.trunarrative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {


    @Autowired
    private ObjectMapper objectMapper;

    public <T> T assertJsonMatchesResource(T object, String resourceName) throws IOException {
        assertThat(toNormalisedJson(object)).isEqualTo(toNormalisedJson(resourceToObject(object.getClass(), resourceName)));
        return object;
    }

    public <T> T resourceToObject(Class<T> expectedType, String resourceName) throws IOException {
        return objectMapper.readValue(getClass().getResource(resourceName), expectedType);
    }

    public String toNormalisedJson(Object value) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    }
}

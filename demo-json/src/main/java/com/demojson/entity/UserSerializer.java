package com.demojson.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        // 仅序列化 accountType 属性，且输出的 key 是 account-type
        jsonGenerator.writeStringField("account-type", user.getAccountType());
        jsonGenerator.writeEndObject();
    }
}

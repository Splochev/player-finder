package com.example.football.util;

public interface XmlProcessor {

    public String serialize(Object obj);

    public void serialize(Object obj, String fileName);

    public <T> T deserialize(String input, Class<T> toType);

    public <T> T deserializeFromFile(String fileName, Class<T> toType);
}

package com.nixlord.dunzo.util;

import com.google.gson.*;

public class JSONParser {
    public static void parser(){
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
//
//        Gson gson= builder.create();


        JsonParser parser = new JsonParser();

        String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        //create tree from JSON
        JsonElement rootNode = parser.parse(jsonString);
        JsonObject details = rootNode.getAsJsonObject();
        JsonElement element = details.getAsJsonArray("recognitionResults").getAsJsonArray();
    }
}

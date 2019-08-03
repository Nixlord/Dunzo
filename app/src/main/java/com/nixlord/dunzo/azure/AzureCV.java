package com.nixlord.dunzo.azure;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class AzureCV {

    public static final MediaType MEDIA_BINARY
            = MediaType.parse("application/octet-stream");
    public static final MediaType MEDIA_JSON
            = MediaType.parse("application/json");

    public static Request createPredictRequest(File image) throws Exception {
        String subscriptionKey = "cd5d8b8cb8f1498e8ce5ba0863daa03b";
        String endPoint = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze?visualFeatures=Categories,Description,Color,Objects,Tags";

        return new Request.Builder()
                .url(endPoint)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .post(RequestBody.create(MEDIA_BINARY, image))
                .build();
    }

    public static Request createRecognitionRequest(File image) throws Exception {
        String subscriptionKey = "cd5d8b8cb8f1498e8ce5ba0863daa03b";
        String endPoint = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/read/core/asyncBatchAnalyze";

        return new Request.Builder()
                .url(endPoint)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .post(RequestBody.create(MEDIA_BINARY, image))
                .build();
    }

}

package com.chriniko.examples.ninth.message;


public class CleanBodyRequest {

    private final String url;
    private final String bodyToClean;

    public CleanBodyRequest(String url, String bodyToClean) {
        this.url = url;
        this.bodyToClean = bodyToClean;
    }

    public String getUrl() {
        return url;
    }

    public String getBodyToClean() {
        return bodyToClean;
    }
}

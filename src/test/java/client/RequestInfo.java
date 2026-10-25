package client;

public class RequestInfo {
    private String url;
    private String body;

    public RequestInfo(String url, String body) {
        this.url = url;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }
}

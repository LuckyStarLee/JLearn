package com.luckylee.httpclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http")
public class HttpProperties {

    private Integer maxTotal;

    private Integer defaultMaxPerRoute;

    private Integer connectionRequestTimeout;

    private Integer connectTimeout;

    private Integer socketTimeout;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    @Override
    public String toString() {
        return "HttpProperties{" +
                "maxTotal=" + maxTotal +
                ", defaultMaxPerRoute=" + defaultMaxPerRoute +
                ", connectionRequestTimeout=" + connectionRequestTimeout +
                ", connectTimeout=" + connectTimeout +
                ", socketTimeout=" + socketTimeout +
                '}';
    }
}

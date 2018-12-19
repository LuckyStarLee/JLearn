package com.luckylee.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
@EnableConfigurationProperties(HttpProperties.class)
public class HttpClientAutoConfigure {
    private static final Logger log = LoggerFactory.getLogger(HttpClientAutoConfigure.class);

    @Resource
    private HttpProperties httpProperties;

    @Bean
    public CloseableHttpClient httpClient() {
        Registry<ConnectionSocketFactory> r = null;
        try {
            r = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", new SSLConnectionSocketFactory(
                            SSLContexts.custom().loadTrustMaterial(new TrustStrategy() {
                                @Override
                                public boolean isTrusted(X509Certificate[] chain, String authType) {
                                    return true;
                                }
                            }).build()
                            , NoopHostnameVerifier.INSTANCE)).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            log.warn(e.getMessage(), e);
        }
        final PoolingHttpClientConnectionManager poolingmgr = new PoolingHttpClientConnectionManager(r);
        poolingmgr.setMaxTotal(httpProperties.getMaxTotal() == null ? 200 : httpProperties.getMaxTotal());
        poolingmgr.setDefaultMaxPerRoute(
                httpProperties.getDefaultMaxPerRoute() == null ? 200 : httpProperties.getDefaultMaxPerRoute());
        log.debug("default http properties config {}", httpProperties);
        return HttpClients.custom().setConnectionManager(
                poolingmgr).setDefaultRequestConfig(defaultRequestConfig()).build();
    }

    @Bean
    public RequestConfig defaultRequestConfig() {
        return RequestConfig.custom().setConnectionRequestTimeout(
                httpProperties.getConnectionRequestTimeout() == null ? 10000 : httpProperties.getConnectionRequestTimeout()).setConnectTimeout(
                httpProperties.getConnectTimeout() == null ? 60000 : httpProperties.getConnectTimeout()).setSocketTimeout(
                httpProperties.getSocketTimeout() == null ? 60000 : httpProperties.getSocketTimeout()).build();
    }
}

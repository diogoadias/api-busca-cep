package com.example.cep.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {

	@Bean
    public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException  {
		
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
			 SSLContext sslContext = new SSLContextBuilder()
			         .loadTrustMaterial(null,acceptingTrustStrategy).build();
			        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);
			        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
			                .setSSLSocketFactory(sslConFactory)
			                .build();
			        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
			        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			        
			        return new RestTemplate(requestFactory);       
    }
}

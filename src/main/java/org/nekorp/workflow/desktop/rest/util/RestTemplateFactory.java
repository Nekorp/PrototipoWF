/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */
package org.nekorp.workflow.desktop.rest.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nekorp
 */
public class RestTemplateFactory {

    private String host;
    private int port;
    private String protocol;
    private String api;
    private String username;
    private String password;
    
    private HttpHost targetHost;
    private RestTemplate template;
    private CloseableHttpClient httpclient;
    
    public RestTemplate getTemplate() {
        return this.template;
    }
    @PostConstruct
    public void init() {
        targetHost = new HttpHost(host, port, protocol);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(username, password));
        //wildcard ssl certificate
        SSLContext sslContext = SSLContexts.createDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            sslContext,NoopHostnameVerifier.INSTANCE);
        
        CacheConfig cacheConfig = CacheConfig.custom()
                .setWeakETagOnPutDeleteAllowed(true)
                .setSharedCache(false)
                .setMaxCacheEntries(1000)
                .setMaxObjectSize(393216)
                .setNeverCacheHTTP10ResponsesWithQueryString(false)
                .build();
        File algo = new File("data/cache");
        httpclient = CachingHttpClientBuilder.create()
                .setCacheConfig(cacheConfig)
                .setCacheDir(algo)
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(10)
                .setConnectionTimeToLive(10, TimeUnit.SECONDS)
                .setDefaultCredentialsProvider(credsProvider)
                .setSSLSocketFactory(sslsf)
                .build();
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local
        // auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);
        
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactoryBasicAuth(httpclient, localContext);
        this.template = new RestTemplate();
        template.getMessageConverters().add(new BufferedImageHttpMessageConverter());
        template.setRequestFactory(factory);
    }

    public void shutdown() {
        try {
            httpclient.close();
        } catch (IOException ex) {
            Logger.getLogger(RestTemplateFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getRootUlr() {
        return targetHost.toURI() + api;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setApi(String api) {
        this.api = api;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

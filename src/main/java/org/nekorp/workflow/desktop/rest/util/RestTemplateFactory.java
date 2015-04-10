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

import java.io.IOException;
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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nekorp
 */
@Service
public class RestTemplateFactory {

    @Value("#{appConfig['app.backend.host']}")
    private String host;
    @Value("#{appConfig['app.backend.port']}")
    private int port;
    @Value("#{appConfig['app.backend.protocol']}")
    private String protocol;
    @Value("#{appConfig['app.backend.api.uri']}")
    private String api;
    @Value("#{appConfig['app.backend.api.version']}")
    private String version;
    @Value("#{appConfig['app.backend.api.username']}")
    private String username;
    @Value("#{appConfig['app.backend.api.password']}")
    private String password;
    
    private HttpHost targetHost;
    private RestTemplate template;
    private CloseableHttpClient httpclient;
    //private PoolingHttpClientConnectionManager connectionPool;
    
    public RestTemplate getTemplate() {
        return this.template;
    }
    @PostConstruct
    public void init() {
        targetHost = new HttpHost(host, port, protocol);
        //connectionPool = new PoolingHttpClientConnectionManager();
        //connectionPool.setDefaultMaxPerRoute(10);
        //connectionPool.setMaxTotal(20);
        
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(username, password));
        //wildcard ssl certificate
        SSLContext sslContext = SSLContexts.createDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            sslContext,NoopHostnameVerifier.INSTANCE);
        
        httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                //.setConnectionManager(connectionPool)
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
        //connectionPool.shutdown();
    }

    public String getRootUlr() {
        return targetHost.toURI()+ api + version;
    }
}

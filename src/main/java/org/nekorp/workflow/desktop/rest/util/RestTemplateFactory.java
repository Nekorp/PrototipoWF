/**
 *   Copyright 2013 Nekorp
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

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 */
public class RestTemplateFactory {

    //TODO configurar todo con archivo
    private HttpHost targetHost;
    //private static HttpHost targetHost = new HttpHost("nekorp-rest-sandbox.appspot.com", 443, "https");
    private RestTemplate template;
    private DefaultHttpClient httpclient;
    private String api = "/api";
    private String version= "/v1";
    
    public RestTemplate getTemplate() {
        return this.template;
    }
    
    public void init() {
        targetHost = new HttpHost("localhost", 8080, "http");
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
        cm.setDefaultMaxPerRoute(10);
        cm.setMaxTotal(20);
        this.httpclient = new DefaultHttpClient(cm);
        httpclient.getCredentialsProvider().setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials("user", "user"));
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpclient);
        this.template = new RestTemplate();
        template.setRequestFactory(factory);
    }
    
    public void shutdown() {
        httpclient.getConnectionManager().shutdown();
    }

    public String getRootUlr() {
        return targetHost.toURI()+ api + version;
    }
}

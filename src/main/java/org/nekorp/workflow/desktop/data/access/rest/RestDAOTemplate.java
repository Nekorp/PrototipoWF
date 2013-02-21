/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nekorp.workflow.desktop.data.access.rest;

import org.nekorp.workflow.desktop.rest.util.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
public abstract class RestDAOTemplate {

    @Autowired
    private RestTemplateFactory factory;

    public RestTemplate getTemplate() {
        return factory.getTemplate();
    }

    public String getRootUlr() {
        return factory.getRootUlr();
    }
}

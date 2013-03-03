/**
 *   Copyright 2012-2013 Nekorp
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
package org.nekorp.workflow.desktop.servicio.imp;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;


/**
 *
 * 
 */
@Service
public class ProxyUtil {
    private static final Logger LOGGER = Logger.getLogger(ProxyUtil.class);
    /**
     * recupera el objeto proxeado.
     * @param proxy el supuesto proxy
     * @return el objeto proxeado
     */
    public Object getTarget(Object proxy) {
        Object obj = proxy;
        if(AopUtils.isAopProxy(proxy)){
            try {
                Advised advised = (Advised) proxy;
                obj = advised.getTargetSource().getTarget();
            } catch (Exception ex) {
                ProxyUtil.LOGGER.error("No se logro recuperar el proxy", ex);
            }
        }
        return obj;
    }
}

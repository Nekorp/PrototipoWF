/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.servicio.login;

import org.nekorp.workflow.desktop.rest.util.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import technology.tikal.accounts.model.authentication.AuthenticationRequest;
import technology.tikal.accounts.model.session.SessionInfo;

/**
 *
 * @author Nekorp
 */
@Service
public class LoginServiceRest implements LoginService {

    @Autowired
    @Qualifier("accounts-RestTemplateFactory")
    private RestTemplateFactory factory;
    
    @Override
    public SessionInfo login(AuthenticationRequest request) {
        return factory.getTemplate().postForObject(factory.getRootUlr() + "/login", request, SessionInfo.class);
    }
}

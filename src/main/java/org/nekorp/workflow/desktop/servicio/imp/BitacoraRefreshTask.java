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


public class BitacoraRefreshTask implements Runnable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BitacoraRefreshTask.class);
    private BitacoraAnalyzerImp analyzer;

    public BitacoraRefreshTask(BitacoraAnalyzerImp analyzer) {
        this.analyzer = analyzer;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Thread.currentThread().sleep(1000l);
            } catch (InterruptedException ex) {
                BitacoraRefreshTask.LOGGER.error(ex);
            }
            analyzer.updateModel(this,null, null);
        }
    }
    
}

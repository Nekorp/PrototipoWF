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
package org.nekorp.workflow.desktop.servicio.monitor.imp;

import java.util.LinkedList;

/**
 *
 * @author Nekorp
 * @param <T>
 */
public class LimitedStack<T> {
  
    private final LinkedList<T> items;
    private final int maxSize;
    
    public LimitedStack(int maxSize) {
        this.maxSize = maxSize;
        items = new LinkedList<>();
    }
    
    public void push(T object) {
        this.items.addFirst(object);
        if (this.items.size() > maxSize) {
            this.items.removeLast();
        }
    }
    
    public T pop() {
        return this.items.removeFirst();
    }
    
    public boolean empty() {
        return this.items.isEmpty();
    }
    
    public void clear() {
        this.items.clear();
    }
}
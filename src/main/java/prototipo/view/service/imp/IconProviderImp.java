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

package prototipo.view.service.imp;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import prototipo.view.resource.imp.ImagenViewer;
import prototipo.view.service.IconProvider;

/**
 *
 */
@Service
public class IconProviderImp implements IconProvider {

    private Map<String,BufferedImage> cache;
    public IconProviderImp() {
        this.cache = new HashMap<>();
    }
    @Override
    public JPanel getIcon(String name) {
        BufferedImage img = cache.get(name);
        if (img == null) {
            try {
                ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
                Resource r = resourceResolver.getResource("/icon/" + name);
                img = ImageIO.read(r.getInputStream());
                this.cache.put(name, img);
            } catch (IOException ex) {
                throw new IllegalArgumentException("el icono no se logro cargar", ex);
            }
        }
        ImagenViewer panel = new ImagenViewer(img);
        panel.setOpaque(false);
        return panel;
    }

}

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
package org.nekorp.workflow.desktop.data.access.rest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.nekorp.workflow.desktop.data.access.ImageDAO;
import org.nekorp.workflow.desktop.modelo.upload.ImagenMetadata;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 */
@Service
public class ImagenDAOImp extends RestDAOTemplate implements ImageDAO {

    @Override
    public ImagenMetadata saveImage(BufferedImage image) {
        try {
            ImagenMetadata r = getTemplate().getForObject(getRootUlr() + "/upload/url", ImagenMetadata.class);
            File file = new File("data/upload.jpg");
            ImageIO.write(image, "jpg", file);
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("myFile", new FileSystemResource(file));
            r = getTemplate().postForObject(r.getUploadUrl(), form, ImagenMetadata.class);
            File cache = new File("data/" + r.getRawBlobKey());
            file.renameTo(cache);
            return r;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteImage(ImagenMetadata data) {
        getTemplate().delete(getRootUlr() + "/upload/imagenes/" + data.getRawBlobKey());
        File cache = new File("data/" + data.getRawBlobKey());
        if (cache.exists()) {
            cache.delete();
        }
    }

    @Override
    public BufferedImage loadImage(ImagenMetadata data) {
        try {
            File file = new File("data/" + data.getRawBlobKey());
            if (file.exists()) {
                return ImageIO.read(file);
            }
            BufferedImage img = getTemplate().getForObject(getRootUlr() + "/upload/imagenes/" + data.getRawBlobKey(), BufferedImage.class);
            ImageIO.write(img, "jpg", file);
            return img;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}

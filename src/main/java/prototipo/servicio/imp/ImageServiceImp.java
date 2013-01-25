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

package prototipo.servicio.imp;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import prototipo.servicio.ImageService;

/**
 *
 */
@Service
public class ImageServiceImp implements ImageService {
    @Value("#{appConfig['app.service.thumb.width']}")
    private int thumbWidth;
    @Value("#{appConfig['app.service.thumb.height']}")
    private int thumbHeight;
    @Value("#{appConfig['app.service.image.width']}")
    private int imageWidth;
    @Value("#{appConfig['app.service.image.height']}")
    private int imageHeight;
    
    @Override
    public BufferedImage resizeToStandarSize(BufferedImage image) {
        Dimension boundary = new Dimension(imageWidth, imageHeight);
        Dimension originalDimension = new Dimension(image.getWidth(), image.getHeight());
        Dimension newDimension = ImageServiceImp.getScaledDimension(originalDimension, boundary);
        ResampleOp resampleOp = new ResampleOp ((int)newDimension.getWidth(), (int)newDimension.getHeight());
        resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
        BufferedImage rescaled = resampleOp.filter(image, null);
        return rescaled;
    }

    @Override
    public BufferedImage getThumbnail(BufferedImage image) {
        Dimension boundary = new Dimension(thumbWidth, thumbHeight);
        Dimension originalDimension = new Dimension(image.getWidth(), image.getHeight());
        Dimension newDimension = ImageServiceImp.getScaledDimension(originalDimension, boundary);
        ResampleOp resampleOp = new ResampleOp ((int)newDimension.getWidth(), (int)newDimension.getHeight());
        resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
        BufferedImage rescaled = resampleOp.filter(image, null);
        return rescaled;
    }

    @Override
    public String toBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            Base64 util = new Base64();
            String result = util.encodeAsString(os.toByteArray());
            return result;
        } catch (IOException ex) {
            throw new IllegalArgumentException("error al convertir a base 64", ex);
        }
    }

    @Override
    public BufferedImage getImageFromBase64(String image) {
        try {
            Base64 util = new Base64();  
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(util.decode(image)));
            return bufImg;
        } catch (IOException ex) {
            throw new IllegalArgumentException("error al convertir a imagen desde string en base 64", ex);
        }
    }

    public static Dimension getScaledDimension(Dimension imgDimension, Dimension boundary) {
        double width;
        double height;
        if (imgDimension.getWidth() > boundary.getWidth()) {
            width = boundary.getWidth();
            height = (width*imgDimension.getHeight())/imgDimension.getWidth();
            if (!(height > boundary.getHeight())) {
                return new Dimension((int)width, (int)height);
            }
        }
        if (imgDimension.getHeight() > boundary.getHeight()) {
            height = boundary.getHeight();
            width = (height*imgDimension.getWidth())/imgDimension.getHeight();
            return new Dimension((int)width, (int)height);
        }
        return new Dimension((int)imgDimension.getWidth(), (int)imgDimension.getHeight());
    }
}

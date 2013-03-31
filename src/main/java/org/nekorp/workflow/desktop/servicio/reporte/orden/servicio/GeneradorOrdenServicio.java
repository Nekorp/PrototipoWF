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

package org.nekorp.workflow.desktop.servicio.reporte.orden.servicio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import org.nekorp.workflow.desktop.servicio.reporte.GeneradorReporte;
import org.nekorp.workflow.desktop.view.model.inventario.damage.DamageDetailsVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.resource.ShapeView;
import org.nekorp.workflow.desktop.view.resource.imp.DamageDetailGraphicsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("GeneradorOrdenServicio")
public class GeneradorOrdenServicio implements GeneradorReporte {

    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    @Qualifier("autoRightView")
    private ShapeView autoRightView;
    @Autowired
    @Qualifier("autoLeftView")
    private ShapeView autoLeftView;
    @Autowired
    @Qualifier("autoFrontView")
    private ShapeView autoFrontView;
    @Autowired
    @Qualifier("autoRearView")
    private ShapeView autoRearView;
    @Override
    public void generaReporte(File destination) {
        this.generaImagen(autoRightView, servicioVB.getDatosAuto().getDamage().getDerecha(), "derecha.jpg", 950, 480);
        this.generaImagen(autoLeftView, servicioVB.getDatosAuto().getDamage().getIzquierda(), "izquierda.jpg", 950, 480);
        this.generaImagen(autoFrontView, servicioVB.getDatosAuto().getDamage().getFrontal(), "frontal.jpg", 650, 480);
        this.generaImagen(autoRearView, servicioVB.getDatosAuto().getDamage().getTrasera(), "trasera.jpg", 650, 480);
    }

    public void generaImagen(ShapeView fondo, List<DamageDetailsVB> danios, String path, int width, int height) {
         try {
            Point contexto = new Point((width - fondo.getShapeWidth()) / 2,
                    (height - fondo.getShapeHeight()) / 2);
            BufferedImage off_Image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = off_Image.createGraphics();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, width, height);
            AffineTransform saveXform = g2.getTransform();
            AffineTransform toCenterAt = new AffineTransform();
            toCenterAt.translate(contexto.getX(), contexto.getY());
            g2.transform(toCenterAt);
            fondo.paint(g2);
            g2.setTransform(saveXform);
            for (DamageDetailsVB x: danios) {
                DamageDetailGraphicsView obj = new DamageDetailGraphicsView();
                obj.setPosicion(new Point(x.getX(), x.getY()));
                obj.setContexto(contexto);
                if (x.getX() <= fondo.getShapeWidth() / 2) {
                    if (x.getY() <= fondo.getShapeHeight() / 2) {
                        obj.setOrientacion(DamageDetailGraphicsView.SuperiorIzquierda);
                    } else {
                        obj.setOrientacion(DamageDetailGraphicsView.InferiorIzquierda);
                    }
                } else {
                    if (x.getY() <= fondo.getShapeHeight() / 2) {
                        obj.setOrientacion(DamageDetailGraphicsView.SuperiorDerecha);
                    } else {
                        obj.setOrientacion(DamageDetailGraphicsView.InferiorDerecha);
                    }
                }
                obj.setTexto(x.toString());
                obj.paint(g2);
            }
            File outputfile = new File(path);
            ImageIO.write(off_Image, "jpg", outputfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

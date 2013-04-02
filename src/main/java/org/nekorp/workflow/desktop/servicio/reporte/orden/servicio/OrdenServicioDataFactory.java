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
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.DatosAutoOS;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.DatosClienteOS;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.DatosOS;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.DetalleCostoOS;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.InventarioDamageOS;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
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
@Service
public class OrdenServicioDataFactory {
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicio;
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
    private String dateFormat = "dd/MM/yy";
    private String monedaFormat = "$#,##0.00";
    
    public List<DatosOS> getDatosOS() {
        try {
            DatosOS r = new DatosOS();
            File logo = new File("logo.jpg");
            r.setLogo(logo.getCanonicalPath());
            r.setNoServicio(servicio.getId());
            r.setFecha(buscaFechaRecepcionAuto());
            r.setDatosCliente(getDatosCliente());
            r.setAsesor(buscaAsesor());
            r.setDatosAuto(getDatosAuto());
            r.setObservaciones(buscarObservaciones());
            r.setCosto(getDetalleCosto());
            r.setInventarioDamage(generaInventarioDamageOS());
            List<DatosOS> lista = new LinkedList<>();
            lista.add(r);
            return lista;
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    private String buscaFechaRecepcionAuto() {
        for (EventoVB x: this.servicio.getBitacora().getEventos()) {
            if (x instanceof EventoEntregaVB) {
                EventoEntregaVB y = (EventoEntregaVB) x;
                if (StringUtils.equals(y.getNombreEvento(), "Entrada de Auto")) {
                    SimpleDateFormat f = new SimpleDateFormat(dateFormat);
                    return f.format(y.getFecha());
                }
            }
        }
        return "";
    }
    
    private String buscaAsesor() {
        for (EventoVB x: this.servicio.getBitacora().getEventos()) {
            if (x instanceof EventoEntregaVB) {
                EventoEntregaVB y = (EventoEntregaVB) x;
                if (StringUtils.equals(y.getNombreEvento(), "Entrada de Auto")) {
                    return y.getResponsable();
                }
            }
        }
        return "";
    }
    
    private String buscarObservaciones() {
        String r = "";
        for (EventoVB x: this.servicio.getBitacora().getEventos()) {
            if (x instanceof EventoGeneralVB) {
                EventoGeneralVB y = (EventoGeneralVB) x;
                if (StringUtils.equalsIgnoreCase(y.getEtiquetas(), "conclusiones") 
                        && !StringUtils.isEmpty(y.getDetalle())) {
                    if (!StringUtils.isEmpty(r)) {
                        r = r + ", ";
                    }
                    r = r + y.getDetalle();
                }
            }
        }
        return r;
    }
    
    private DatosClienteOS getDatosCliente() {
        DatosClienteOS cliente = new DatosClienteOS();
        cliente.setNombre(servicio.getCliente().getNombre());
        cliente.setDireccion(servicio.getCliente().getDomicilio().getCalle());
        cliente.setEmail(servicio.getCliente().getEmail());
        cliente.setTelefono(servicio.getCliente().getTelefonoUno().getValor());
        return cliente;
    }
    
    private DatosAutoOS getDatosAuto() {
        DatosAutoOS auto = new DatosAutoOS();
        auto.setMarca(servicio.getAuto().getMarca());
        auto.setTipo(servicio.getAuto().getTipo());
        auto.setModelo(servicio.getAuto().getModelo());
        auto.setColor(servicio.getAuto().getColor());
        auto.setPlacas(servicio.getAuto().getPlacas());
        auto.setKilometraje(servicio.getDatosAuto().getKilometraje());
        auto.setSerie(servicio.getAuto().getNumeroSerie());
        auto.setServicio(servicio.getDescripcion());
        return auto;
    }
    
    private List<DetalleCostoOS> getDetalleCosto() {
        List<DetalleCostoOS> lista = new LinkedList<>();
        DetalleCostoOS det;
        for (RegistroCostoVB x: servicio.getCostos()) {
            det = new DetalleCostoOS();
            det.setCantidad(x.getCantidad().toString());
            det.setDescripcion(x.getTipo() + " | " + x.getSubtipo() + " | " + x.getConcepto());
            DecimalFormat df = new DecimalFormat(monedaFormat);
            det.setCosto(df.format(x.getSubtotal().doubleValue()));
            lista.add(det);
        }
        return lista;
    }
    
    private InventarioDamageOS generaInventarioDamageOS() {
        try {
            InventarioDamageOS inv = new InventarioDamageOS();
            File derecha = new File("data/derecha.jpg");
            File izquierda = new File("data/izquierda.jpg");
            File frontal = new File("data/frontal.jpg");
            File trasera = new File("data/trasera.jpg");
            this.generaImagenDamage(autoRightView, servicio.getDatosAuto().getDamage().getDerecha(), derecha, 950, 480);
            this.generaImagenDamage(autoLeftView, servicio.getDatosAuto().getDamage().getIzquierda(), izquierda, 950, 480);
            this.generaImagenDamage(autoFrontView, servicio.getDatosAuto().getDamage().getFrontal(), frontal, 650, 480);
            this.generaImagenDamage(autoRearView, servicio.getDatosAuto().getDamage().getTrasera(), trasera, 650, 480);
            inv.setDerecha(derecha.getCanonicalPath());
            inv.setIzquierda(izquierda.getCanonicalPath());
            inv.setFrontal(frontal.getCanonicalPath());
            inv.setTrasera(trasera.getCanonicalPath());
            return inv;
        } catch (IOException ex) {
            throw new RuntimeException(ex); 
        }
    }
    
    private void generaImagenDamage(ShapeView fondo, List<DamageDetailsVB> danios, File outputfile, int width, int height) {
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
            ImageIO.write(off_Image, "jpg", outputfile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

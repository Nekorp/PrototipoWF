/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import prototipo.modelo.Folio;
import prototipo.modelo.Servicio;
import prototipo.modelo.ServicioIndex;
import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.modelo.cliente.Cliente;

/**
 * 
 * @author Marisa
 */
public class DAOServicio {

    private DAOCliente daoCliente;

    List<ServicioIndex> indiceServicios = new LinkedList<>();

    public String getFolio() {
        Folio result = new Folio();
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(
                    new FileInputStream("system/folio.xml")));
            result = (Folio) d.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOServicio.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        try {
            result.setActualValue(result.getActualValue() + 1);
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream("system/folio.xml")));
            e.writeObject(result);
            e.close();
            return result.getActualValue() + "";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOServicio.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return null;
    }

    public void guarda(Servicio servicio) {
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream("data/" + servicio.getId() + ".xml")));
            e.writeObject(servicio);
            e.close();
            ServicioIndex index = this.createIndex(servicio);
            this.indiceServicios.remove(index);
            this.indiceServicios.add(index);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(DAOServicio.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void init() {
        File directorio = new File("data");
        String[] listaDirectorio = directorio.list();
        for (String arch : listaDirectorio) {
            if (arch.endsWith("xml")) {
                Servicio servicio = this.carga(arch.substring(0,
                        arch.length() - 4));
                this.indiceServicios.add(this.createIndex(servicio));
            }
        }
    }

    public Servicio carga(String id) {
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(
                    new FileInputStream("data/" + id + ".xml")));
            Object result = d.readObject();
            d.close();
            return (Servicio) result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOServicio.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return null;
    }

    public List<ServicioIndex> getIndiceServicios() {
        List<ServicioIndex> response = new LinkedList<>();
        for (ServicioIndex obj : this.indiceServicios) {
            response.add(obj);
        }
        return response;
    }

    private ServicioIndex createIndex(Servicio servicio) {
        ServicioIndex index = new ServicioIndex();
        index.setIdCliente(servicio.getIdCliente());
        index.setIdServicio(servicio.getId());
        index.setNumeroSerieAuto(servicio.getDatosAuto().getNumeroSerie());
        index.setPlacasAuto(servicio.getDatosAuto().getPlacas());
        List<Cliente> clientes = this.daoCliente.getListaClientes();
        for (Cliente x: clientes) {
            if (x.getId().equals(servicio.getIdCliente())) {
                index.setNombreCliente(x.getNombre());
            }
        }
        for (Evento obj: servicio.getBitacora().getEventos()){
            if (obj instanceof EventoEntrega) {
                EventoEntrega ev = (EventoEntrega)obj;
                if (ev.getNombreEvento().compareTo("Entrada de Auto") == 0) {
                    index.setFechaRecepcion(ev.getFecha());
                }
            }
        }
        return index;
    }

    public void setDaoCliente(DAOCliente daoCliente) {
        this.daoCliente = daoCliente;
    }

}

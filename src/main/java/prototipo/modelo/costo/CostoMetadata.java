/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.costo;

import org.springframework.stereotype.Component;
import prototipo.servicio.Metadata;

/**
 *
 * @author Marisa
 */
@Component
public class CostoMetadata implements Metadata {
    private Double total;
    public CostoMetadata() {
        this.total = 0d;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
}

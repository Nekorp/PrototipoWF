/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.costo;

import org.springframework.stereotype.Component;
import prototipo.modelo.currency.Moneda;
import prototipo.servicio.Metadata;

/**
 *
 * @author Marisa
 */
@Component
public class CostoMetadata implements Metadata {
    private Moneda total;
    public CostoMetadata() {
        this.total = new Moneda();
    }

    public Moneda getTotal() {
        return total;
    }

    public void setTotal(Moneda total) {
        this.total = total;
    }
    
}

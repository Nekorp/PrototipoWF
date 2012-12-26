/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo;

/**
 * 
 * @author Marisa
 */
public class Telefono {
    private String label;
    private String valor;

    public Telefono() {
        label = "";
        valor = "";
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}

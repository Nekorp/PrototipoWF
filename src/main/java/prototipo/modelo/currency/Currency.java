/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.currency;

/**
 *
 * gracias java por tu perdida de precision.
 */
public class Currency {
    private int entero;
    private int decimal;

    public Currency(int entero, int decimal) {
        this.entero = entero;
        this.decimal = decimal;
    }
    
//    Currency multiplica(Integer factor) {
//        Currency = new
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.entero;
        hash = 67 * hash + this.decimal;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Currency other = (Currency) obj;
        if (this.entero != other.entero) {
            return false;
        }
        if (this.decimal != other.decimal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return entero + "." + decimal;
    }
}

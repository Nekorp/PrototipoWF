/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.currency;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.apache.commons.lang.StringUtils;

/**
 *
 * gracias java por tu perdida de precision.
 * no usar los getter y setter estan ahi para serializar :a
 */
public class Moneda {
    private long entero;
    private long decimal;
    
    public Moneda() {
    }
    
    public Moneda(long ent) {
        this();
        this.entero = ent;
    }
    
    public Moneda(long ent, long dec) {
        this.entero = ent;
        this.decimal = dec;
    }
    
    public Moneda suma(Moneda op) {
        if (op == null) {
            return this;
        }
        long rDec = decimal + op.decimal;
        long rEnt = entero + op.entero + (rDec/100);
        rDec = rDec % 100;
        return new Moneda(rEnt, rDec);
    }
    
    public Moneda resta(Moneda op) {
        if (op == null) {
            return this;
        }
        long rDec = decimal - op.decimal;
        long rEnt = entero - op.entero + (rDec/100);
        rDec = rDec % 100;
        return new Moneda(rEnt, rDec);
    }
    
    public Moneda multiplica(Integer escala) {
        if (escala == null) {
            return new Moneda();
        }
        long rDec = decimal * escala;
        long rEnt = (entero * escala) + (rDec/100);
        rDec = rDec % 100;
        return new Moneda(rEnt, rDec);
    }
    
//    private BigDecimal toBigDecimal(Moneda m) {
//        long num = (m.entero * 100) + decimal;
//        return new BigDecimal(num);
//    }

    @Deprecated
    public long getEntero() {
        return entero;
    }
    @Deprecated
    public void setEntero(long entero) {
        this.entero = entero;
    }
    @Deprecated
    public long getDecimal() {
        return decimal;
    }
    @Deprecated
    public void setDecimal(long decimal) {
        this.decimal = decimal;
    }
    @Override
    public String toString() {
        DecimalFormat fd = new DecimalFormat("00");
        DecimalFormat fe = new DecimalFormat("0");
        return fe.format(entero) + "." + fd.format(this.decimal);
    }
    public static Moneda valueOf(String s) {
        if (s == null) {
            return new Moneda();
        }
        if (s.length() == 0) {
            return new Moneda();
        }
        if (s.matches("(\\d*)|(\\d*.\\d{0,2})")) {
            if (StringUtils.contains(s, '.')) {
                int puntoIndex = StringUtils.indexOf(s, '.');
                String[] partes = StringUtils.split(s, '.');
                if (partes.length == 0) {
                    return new Moneda();
                }
                if (partes.length == 1) {
                    if (puntoIndex > 0) {
                        return new Moneda(Long.parseLong(partes[0]));
                    } else {
                        return new Moneda(0, Long.parseLong(partes[0]));
                    }
                }
                return new Moneda(Long.parseLong(partes[0]), Long.parseLong(partes[1]));
            } else {
                return new Moneda(Long.parseLong(s));
            }
        } else {
            throw new IllegalArgumentException("no es una cantidad");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.entero ^ (this.entero >>> 32));
        hash = 83 * hash + (int) (this.decimal ^ (this.decimal >>> 32));
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
        final Moneda other = (Moneda) obj;
        if (this.entero != other.entero) {
            return false;
        }
        if (this.decimal != other.decimal) {
            return false;
        }
        return true;
    }
    
    
}

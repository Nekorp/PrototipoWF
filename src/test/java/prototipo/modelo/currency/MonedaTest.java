/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.currency;

import junit.framework.TestCase;

/**
 *
 * @author Marisa
 */
public class MonedaTest extends TestCase {
    
    public MonedaTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of suma method, of class Moneda.
     */
    public void testSuma() {
        System.out.println("suma");
        Moneda op;
        Moneda instance;
        Moneda expResult;
        Moneda result;
        
        op = new Moneda();
        instance = new Moneda();
        expResult = new Moneda();
        result = instance.suma(op);
        assertEquals(expResult, result);
    }

    /**
     * Test of resta method, of class Moneda.
     */
    public void testResta() {
//        System.out.println("resta");
//        Moneda op = null;
//        Moneda instance = new Moneda();
//        Moneda expResult = null;
//        Moneda result = instance.resta(op);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of multiplica method, of class Moneda.
     */
    public void testMultiplica() {
//        System.out.println("multiplica");
//        Moneda op = null;
//        Moneda instance = new Moneda();
//        Moneda expResult = null;
//        Moneda result = instance.multiplica(op);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Moneda.
     */
    public void testToString() {
//        System.out.println("toString");
//        Moneda instance = new Moneda();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Moneda.
     */
    public void testValueOf() {
        System.out.println("valueOf");
        String s = "";
        Moneda expResult = new Moneda();
        Moneda result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        s = ".";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        s = null;
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        s = "0.";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        s = ".0";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        s = "0.0";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        s = "0";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(1);
        s = "1";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(1);
        s = "1.";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(0,1);
        s = ".1";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(0,1);
        s = "0.1";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(1,1);
        s = "1.1";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(0,11);
        s = ".11";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(1,11);
        s = "1.11";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
        expResult = new Moneda(11,11);
        s = "11.11";
        result = Moneda.valueOf(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Moneda.
     */
    public void testHashCode() {
//        System.out.println("hashCode");
//        Moneda instance = new Moneda();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Moneda.
     */
    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        Moneda instance = new Moneda();
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}

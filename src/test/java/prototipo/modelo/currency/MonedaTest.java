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
package prototipo.modelo.currency;

import junit.framework.TestCase;

/**
 *
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
        System.out.println("multiplica");
        Moneda a = null;
        Moneda b = new Moneda();
        Moneda expResult = new Moneda();
        Moneda result = b.multiplica(a);
        assertEquals(expResult, result);
        
        a = new Moneda(1);
        b = new Moneda();
        expResult = new Moneda();
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = new Moneda(1);
        b = new Moneda(1);
        expResult = new Moneda(1);
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = new Moneda(1,01);
        b = new Moneda(1);
        expResult = new Moneda(1,01);
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = new Moneda(1,01);
        b = new Moneda(1,01);
        expResult = new Moneda(1,02);
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = new Moneda(1,11);
        b = new Moneda(1,11);
        expResult = new Moneda(1,23);
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = new Moneda(2,36);
        b = new Moneda(2,36);
        expResult = new Moneda(5,57);
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = new Moneda(0,01);
        b = new Moneda(100,0);
        expResult = new Moneda(1,0);
        result = a.multiplica(b);
        assertEquals(expResult, result);
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

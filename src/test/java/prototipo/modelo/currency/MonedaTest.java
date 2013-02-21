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
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;

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
        MonedaVB op;
        MonedaVB instance;
        MonedaVB expResult;
        MonedaVB result;
        
        op = new MonedaVB();
        instance = new MonedaVB();
        expResult = new MonedaVB();
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
        MonedaVB a = new MonedaVB();
        MonedaVB b = new MonedaVB();
        MonedaVB expResult = new MonedaVB();
        MonedaVB result = b.multiplica(a);
        assertEquals(expResult, result);
        
        a = MonedaVB.valueOf("1");
        b = new MonedaVB();
        expResult = new MonedaVB();
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = MonedaVB.valueOf("1");
        b = MonedaVB.valueOf("1");
        expResult = MonedaVB.valueOf("1");
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = MonedaVB.valueOf("1.01");
        b = MonedaVB.valueOf("1");
        expResult = MonedaVB.valueOf("1.01");
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = MonedaVB.valueOf("1.01");
        b = MonedaVB.valueOf("1.01");
        expResult = MonedaVB.valueOf("1.02");
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = MonedaVB.valueOf("1.11");
        b = MonedaVB.valueOf("1.11");
        expResult = MonedaVB.valueOf("1.23");
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = MonedaVB.valueOf("2.36");
        b = MonedaVB.valueOf("2.36");
        expResult = MonedaVB.valueOf("5.57");
        result = a.multiplica(b);
        assertEquals(expResult, result);
        
        a = MonedaVB.valueOf("0.01");
        b = MonedaVB.valueOf("100");
        expResult = MonedaVB.valueOf("1.0");
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
        MonedaVB expResult = new MonedaVB();
        MonedaVB result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        s = ".0";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        s = null;
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        s = "0.0";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        s = ".0";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        s = "0.0";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        s = "0";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("1");
        s = "1";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("1");
        s = "1.0";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("0.1");
        s = ".1";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("0.1");
        s = "0.1";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("1.1");
        s = "1.1";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("0.11");
        s = ".11";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("1.11");
        s = "1.11";
        result = MonedaVB.valueOf(s);
        assertEquals(expResult, result);
        expResult = MonedaVB.valueOf("11.11");
        s = "11.11";
        result = MonedaVB.valueOf(s);
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

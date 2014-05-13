/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans.util.validator;

import junit.framework.TestCase;

/**
 *
 * @author pingeso
 */
public class RutTest extends TestCase {
    
    public RutTest(String testName) {
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
     * Test of check method, of class Rut.
     */
    public void testCheck() {
        
        System.out.println("Evaluando rut válido:");
        assertEquals(true, evaluarRut("173196474")); //válido 
        
        System.out.println("Evaluando rut invalido con formato correcto:");
        assertEquals(false, evaluarRut("173196475")); //invalido formato correcto
        
        System.out.println("Evaluando rut invalido con formato invalido:");
        assertEquals(false, evaluarRut("1731@6474")); //invalido formato incorrecto
        
        System.out.println("Evaluando rut con formato completo:");
        assertEquals(true, evaluarRut("17.319.647-4")); //válido formato completo
        
        System.out.println("Evaluando rut válido con formato sin puntos y con guión:");
        assertEquals(true, evaluarRut("17319647-4")); //válido con formato sin puntos y con guión
        
        System.out.println("Evaluando rut válido con formato incompleto:");
        assertEquals(true, evaluarRut("17.3196474")); //válido con formato incompleto             
    
    }
    
    /*
        Función que evalua el rut utilizando el método check de la clase Rut
    */
    public boolean evaluarRut(String rut){
        System.out.println("checking Rut ->".concat(rut));
        Rut instance = new Rut();
        boolean result = instance.check(rut);       
        
        return result;
    }



    
}

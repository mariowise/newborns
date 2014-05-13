/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.DeliveryType;
import entities.MotherFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pingeso
 */
public class DeliveryTypeFacadeLocalTest {
    
    public DeliveryTypeFacadeLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class DeliveryTypeFacadeLocal.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DeliveryTypeFacadeLocal instance = null;
        try {
            instance = (DeliveryTypeFacadeLocal)container.getContext().lookup("java:global/classes/DeliveryTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeliveryTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DeliveryType deliveryType = new DeliveryType();
        
        deliveryType.setId(100L);
        deliveryType.setName("Cesarea");
        
        instance.create(deliveryType);
        assertEquals(true, instance.find(100L)!=null);
        
        container.close();
    }

    /**
     * Test of edit method, of class DeliveryTypeFacadeLocal.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DeliveryTypeFacadeLocal instance = null;
        try {
            instance = (DeliveryTypeFacadeLocal)container.getContext().lookup("java:global/classes/DeliveryTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeliveryTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DeliveryType deliveryType = new DeliveryType();        
        deliveryType.setId(100L);
        deliveryType.setName("Parto Normal");
              
        instance.edit(deliveryType);
        deliveryType = instance.find(100L);
        assertEquals(true, deliveryType.getName().equals("Parto Normal"));
        
        container.close();
    }

    /**
     * Test of remove method, of class DeliveryTypeFacadeLocal.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");        
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DeliveryTypeFacadeLocal instance = null;
        try {
            instance = (DeliveryTypeFacadeLocal)container.getContext().lookup("java:global/classes/DeliveryTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeliveryTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DeliveryType deliveryType = new DeliveryType();        
        deliveryType.setId(100L);
        
        instance.remove(deliveryType);
        assertEquals(true, instance.find(100l)==null);
        container.close();
    }

    public class DeliveryTypeFacadeLocalImpl implements DeliveryTypeFacadeLocal {

        public void create(DeliveryType deliveryType) {
        }

        public void edit(DeliveryType deliveryType) {
        }

        public void remove(DeliveryType deliveryType) {
        }

        public DeliveryType find(Object id) {
            return null;
        }

        public List<DeliveryType> findAll() {
            return null;
        }

        public List<DeliveryType> findRange(int[] range) {
            return null;
        }

        public int count() {
            return 0;
        }
    }
    
}

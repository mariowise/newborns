/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.AccountType;
import entities.AssociativeComponentType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pingeso
 */
public class AssociativeComponentTypeFacadeLocalTest {
    
    public AssociativeComponentTypeFacadeLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class AssociativeComponentTypeFacadeLocal.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AssociativeComponentTypeFacadeLocal instance = null;
        try {
            instance = (AssociativeComponentTypeFacadeLocal)container.getContext().lookup("java:global/classes/AssociativeComponentTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AssociativeComponentTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AssociativeComponentType associativeComponentType = new AssociativeComponentType();
        associativeComponentType.setId(50L);
        associativeComponentType.setName("hola");
        
        
        
        instance.create(associativeComponentType);
        
        assertEquals(true, instance.find(50L)!=null);
        
        container.close();
        
    }

    /**
     * Test of edit method, of class AssociativeComponentTypeFacadeLocal.
     */
    @Test
    public void testEdit() {        
        System.out.println("edit");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AssociativeComponentTypeFacadeLocal instance = null;
        try {
            instance = (AssociativeComponentTypeFacadeLocal)container.getContext().lookup("java:global/classes/AssociativeComponentTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AssociativeComponentTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        AssociativeComponentType associativeComponentType = new AssociativeComponentType();
        associativeComponentType.setId(50L);
        associativeComponentType.setName("chao");
        
        instance.edit(associativeComponentType);
        
        associativeComponentType = instance.find(50L);
        
        assertEquals(true, associativeComponentType.getName().equals("chao"));
        
        
        container.close();
    }

    /**
     * Test of remove method, of class AssociativeComponentTypeFacadeLocal.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AssociativeComponentTypeFacadeLocal instance = null;
        try {
            instance = (AssociativeComponentTypeFacadeLocal)container.getContext().lookup("java:global/classes/AssociativeComponentTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AssociativeComponentTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AssociativeComponentType associativeComponentType = new AssociativeComponentType();
        associativeComponentType.setId(50L);
        
        instance.remove(associativeComponentType);
        assertEquals(true, instance.find(50L)==null);
        
        container.close();
        
    }

   
    public class AssociativeComponentTypeFacadeLocalImpl implements AssociativeComponentTypeFacadeLocal {

        public void create(AssociativeComponentType associativeComponentType) {
        }

        public void edit(AssociativeComponentType associativeComponentType) {
        }

        public void remove(AssociativeComponentType associativeComponentType) {
        }

        public AssociativeComponentType find(Object id) {
            return null;
        }

        public List<AssociativeComponentType> findAll() {
            return null;
        }

        public List<AssociativeComponentType> findRange(int[] range) {
            return null;
        }

        public int count() {
            return 0;
        }
    }
    
}

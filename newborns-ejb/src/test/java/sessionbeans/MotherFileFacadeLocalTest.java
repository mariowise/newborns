/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.MotherFile;
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
public class MotherFileFacadeLocalTest {
    
    public MotherFileFacadeLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class MotherFileFacadeLocal.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MotherFileFacadeLocal instance = null;
        try {
            instance = (MotherFileFacadeLocal)container.getContext().lookup("java:global/classes/MotherFileFacade");
        } catch (NamingException ex) {
            Logger.getLogger(MotherFileFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MotherFile motherFile = new MotherFile();
        
        motherFile.setId(100L);
        motherFile.setRut("173196474");
        motherFile.setFirstName("Maria");
        
        instance.create(motherFile);
        assertEquals(true, instance.find(100L)!=null);
        
        container.close();
        
    }

    /**
     * Test of edit method, of class MotherFileFacadeLocal.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MotherFileFacadeLocal instance = null;
        try {
            instance = (MotherFileFacadeLocal)container.getContext().lookup("java:global/classes/MotherFileFacade");
        } catch (NamingException ex) {
            Logger.getLogger(MotherFileFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MotherFile motherFile = new MotherFile();
        
        motherFile.setId(100L);
        motherFile.setRut("173196474");
        motherFile.setFirstName("Juana");
        
        instance.edit(motherFile);
        
        motherFile = instance.find(100L);
        assertEquals(true, motherFile.getFirstName().equals("Juana"));
        
        container.close();
    }

    /**
     * Test of remove method, of class MotherFileFacadeLocal.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");        
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MotherFileFacadeLocal instance = null;
        try {
            instance = (MotherFileFacadeLocal)container.getContext().lookup("java:global/classes/MotherFileFacade");
        } catch (NamingException ex) {
            Logger.getLogger(MotherFileFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MotherFile motherFile = new MotherFile();
        
        motherFile.setId(100L);
        
        instance.remove(motherFile);
        assertEquals(true, instance.find(100L)==null);
        
        container.close();
    }
    
    

    public class MotherFileFacadeLocalImpl implements MotherFileFacadeLocal {

        public void create(MotherFile motherFile) {
        }

        public void edit(MotherFile motherFile) {
        }

        public void remove(MotherFile motherFile) {
        }

        public MotherFile find(Object id) {
            return null;
        }

        public List<MotherFile> findAll() {
            return null;
        }

        public List<MotherFile> findRange(int[] range) {
            return null;
        }

        public int count() {
            return 0;
        }
    }
    
}

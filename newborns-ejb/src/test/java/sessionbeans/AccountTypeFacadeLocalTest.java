/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.AccountType;
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
public class AccountTypeFacadeLocalTest {
    
    public AccountTypeFacadeLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class AccountTypeFacadeLocal.
     */
    @Test
    public void testCreate() {
        System.out.println("Crear un tipo de cuenta:");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AccountTypeFacadeLocal instance = null;
        try {
            instance = (AccountTypeFacadeLocal)container.getContext().lookup("java:global/classes/AccountTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AccountTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        AccountType accountType = new AccountType();
        
        accountType.setId((long)19);
        accountType.setName("admin");
        
        instance.create(accountType);
        assertEquals(true, instance.find((long)19)!=null);
        
        container.close();
        
    }

    /**
     * Test of edit method, of class AccountTypeFacadeLocal.
     */
    @Test
    public void testEdit() {
        System.out.println("Modificar un tipo de cuenta: ");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AccountTypeFacadeLocal instance=null;
        try {
            instance = (AccountTypeFacadeLocal)container.getContext().lookup("java:global/classes/AccountTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AccountTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        AccountType accountType = new AccountType();
        accountType.setId(19L);
        accountType.setName("matrona");
        
        instance.edit(accountType);
        
        accountType = instance.find(19L);
        
        assertEquals(true, accountType.getName().equals("matrona"));
        
        
        container.close();
    }

    /**
     * Test of remove method, of class AccountTypeFacadeLocal.
     */
    @Test
    public void testRemove() {
        System.out.println("Eliminar un tipo de cuenta: ");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AccountTypeFacadeLocal instance=null;
        try {
            instance = (AccountTypeFacadeLocal)container.getContext().lookup("java:global/classes/AccountTypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AccountTypeFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        AccountType accountType = new AccountType();
        accountType.setId(19L);
        //accountType.setName("matrona");
        instance.remove(accountType);
        assertEquals(true, instance.find(19L)==null);
        
        container.close();
    }


    public class AccountTypeFacadeLocalImpl implements AccountTypeFacadeLocal {

        public void create(AccountType accountType) {
        }

        public void edit(AccountType accountType) {
        }

        public void remove(AccountType accountType) {
        }

        public AccountType find(Object id) {
            return null;
        }

        public List<AccountType> findAll() {
            return null;
        }

        public List<AccountType> findRange(int[] range) {
            return null;
        }

        public int count() {
            return 0;
        }
    }
    
}

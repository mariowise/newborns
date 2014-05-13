/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Account;
import entities.AccountType;
import static entities.Account_.accountType;
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
public class AccountFacadeLocalTest {
    
    public AccountFacadeLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class AccountFacadeLocal.
     */
    
    /*
    @Test
    public void testCreate() {
        System.out.println("create");     
        
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AccountFacadeLocal instance = null;
        try {
            instance = (AccountFacadeLocal)container.getContext().lookup("java:global/classes/AccountFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AccountFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        AccountType accountType = new AccountType();
        accountType.setId(24L);
        accountType.setName("tester");
        
        Account account = new Account();
        account.setRut("173196474");
        account.setEmail("nico@usach.cl");
        account.setName("Nicolas");
        account.setPassword("password");
        account.setPhone("1234567");
        
        account.setAccountType(accountType);
        
        
        instance.create(account);
        assertEquals(true, instance.find("173196474")!=null);
        
        container.close();
        
    }
    */
    

    /**
     * Test of edit method, of class AccountFacadeLocal.
     */
    
    /*
    @Test
    public void testEdit() {
        System.out.println("edit");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AccountFacadeLocal instance = null;
        try {
            instance = (AccountFacadeLocal)container.getContext().lookup("java:global/classes/AccountFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AccountFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Account account = new Account();
        account.setRut("173196474");
        account.setName("Nicolas");
        
        instance.edit(account);
        
        account = instance.find("173196474");
        
        assertEquals(true, account.getName().equals("Nicolas"));
        
        container.close();

    }
    */

    /**
     * Test of remove method, of class AccountFacadeLocal.
     */
    
    /*
    @Test
    public void testRemove() {
        System.out.println("remove");
                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AccountFacadeLocal instance = null;
        try {
            instance = (AccountFacadeLocal)container.getContext().lookup("java:global/classes/AccountFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AccountFacadeLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Account account = new Account();
        account.setRut("173196474");
        
        instance.remove(account);
        
        assertEquals(true, instance.find("173196474"));
        
        container.close();
    }
    */



    public class AccountFacadeLocalImpl implements AccountFacadeLocal {

        public void create(Account account) {
        }

        public void edit(Account account) {
        }

        public void remove(Account account) {
        }

        public Account find(Object id) {
            return null;
        }

        public List<Account> findAll() {
            return null;
        }

        public List<Account> findRange(int[] range) {
            return null;
        }

        public int count() {
            return 0;
        }
    }
    
}

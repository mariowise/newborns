/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import entities.Account;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.util.JsfUtil;
import managedbeans.util.SessionUtil;
import sessionbeans.AccountFacadeLocal;

/**
 *
 * @author pingeso
 */
@Named(value = "SessionController")
@RequestScoped
public class SessionController {

    @Inject
    private SessionUtil sessionUtil;
    
    private String rut;
    
    private String password;
    
    /**
     * Creates a new instance of Session
     */
    public SessionController() {
    
    }

    public SessionUtil getSessionUtil() {
        return sessionUtil;
    }

    public void setSessionUtil(SessionUtil sessionUtil) {
        this.sessionUtil = sessionUtil;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {
        System.out.println("Calling login for " + rut);

        if (sessionUtil.login(rut, password)) {
            System.out.println("SessionController: Usuaro logeado de forma exitosa: " + rut);
            JsfUtil.redirect(sessionUtil.route66());
        } 
        else {
            System.out.println("SessionController: Login fail");
        }
    }
    
    public void logout() {
        sessionUtil.logout();
        JsfUtil.redirect(sessionUtil.logout());
    }
    
    public boolean hasIdentity() {
        return sessionUtil.hasIdentity();
    }
    
}

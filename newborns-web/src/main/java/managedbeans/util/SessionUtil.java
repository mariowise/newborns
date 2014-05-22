/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans.util;

import entities.Account;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import sessionbeans.AccountFacadeLocal;

/**
 *
 * @author Aldo
 */
@Named(value = "SessionUtil")
@SessionScoped
public class SessionUtil implements Serializable {

    @EJB
    private AccountFacadeLocal ejbFacade;
    
    private String rut;
    
    public SessionUtil() {
    
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    
    public Account getCurrentUser() {
        return ejbFacade.find(rut);
    }
    
    public boolean login(String _rut, String _password) {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        rut = _rut;

        try {
            if(!hasIdentity()) {
                request.login(_rut, _password);
                System.out.println("SessionUtil: SessionScope created for " + _rut);
            } else {
                System.out.println("SessionUtil: User allready logged");
            }
            
            return true;
        } 
        catch (Exception e) {
            System.out.println("SessionUtil: User or password not found");
            // mc.mensajeRetroalimentacion("Error", "Usuario y/o contraseña incorrecta");
        }
        return false;
    }

    public String logout() {
        System.out.println("SessionUtil: Logout for " + rut);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        return "/faces/index.xhtml";
    }

    public boolean hasIdentity(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        if(request.getRemoteUser() == null){
            return false;
        }
        return true;
    }
    
    public String route66() {
        System.out.println("Calling SessionUtil.route66");
        
        Account currentAccount = ejbFacade.find(rut);
        if(currentAccount.getAccountType().getName().compareTo("admin") == 0) {
            return "/faces/roles/admin/index.xhtml";
        }
        if(currentAccount.getAccountType().getName().compareTo("fonoaudiologo") == 0) {
            return "/faces/roles/fonoaudiologo/index.xhtml";
        }
        if(currentAccount.getAccountType().getName().compareTo("matrona") == 0) {
            return "/faces/roles/matrona/index.xhtml";
        }
        if(currentAccount.getAccountType().getName().compareTo("medico") == 0) {
            return "/faces/roles/medico/index.xhtml";
        }
        if(currentAccount.getAccountType().getName().compareTo("administrativo") == 0) {
            return "/faces/roles/administrativo/index.xhtml";
        }
        return "/faces/roles/index.xhtml";    
    }
}
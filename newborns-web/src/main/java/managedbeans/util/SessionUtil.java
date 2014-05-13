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
    
    /**
     * Funcion que valida la autentificacion de un usuario a traves de su contraseña
     * @param _rut string que contiene el rut del usuario
     * @param _password string que contiene la password del usuario
     * @return verdadero si el usuario pudo ingresar al sistema, falso en caso contrario
     */
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
    
    /**
     * Funcion que desconecta al usuario del sistema
     * @return se retorna la direccion a la pagina de inicio
     */

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
    
    /**
     * Funcion que redirige al usuario a la ruta que corresponda a su rol
     * @return retorna la direccion donde debe ser redigirido el usuario
     */
    
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
        return "/faces/roles/index.xhtml";    
    }
}
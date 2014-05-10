/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans.util;

import java.security.MessageDigest;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pingeso
 */
@Named(value = "Session")
@RequestScoped
public class Session {

    private String rut;
    
    private String password;
    
    /**
     * Creates a new instance of Session
     */
    public Session() {
    
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
    
    public String login () {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) 
        context.getExternalContext().getRequest();
        try {
            request.login(this.rut, this.password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed."));
            System.out.println("### Login failed (" + this.rut + ", " + this.password + ")");
            return "error";
        }
        return "/faces/roles/index.xhtml";
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) 
        context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }
    
    private String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
}

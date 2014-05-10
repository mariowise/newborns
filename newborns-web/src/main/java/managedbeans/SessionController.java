/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.util.JsfUtil;
import managedbeans.util.SessionUtil;

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
    }
    
//    private String route66() {
//        System.out.println("Calling SessionController.route66");
//        currentAccount = getEjbFacade().find(rut);
//        if(currentAccount.getAccountType().getName().compareTo("admin") == 0) {
//            return "roles/admin/index";
//        }
//        if(currentAccount.getAccountType().getName().compareTo("fonoaudiologo") == 0) {
//            return "roles/fonoaudiologo/index";
//        }
//        if(currentAccount.getAccountType().getName().compareTo("matrona") == 0) {
//            return "roles/matrona/index";
//        }
//        if(currentAccount.getAccountType().getName().compareTo("medico") == 0) {
//            return "roles/medico/index";
//        }
//        return "roles/index";
//    }    
    
//    public String login () {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) 
//        context.getExternalContext().getRequest();
//        try {
//            System.out.println("Login(" + rut + ", " + password + ")");
//            request.login(rut, password);
//        } catch (ServletException e) {
//            context.addMessage(null, new FacesMessage("Login failed."));
//            System.out.println("### Login failed (" + rut + ", " + password + ")");
//            return "Error: Login rejected";
//        }
//        return route66();
//    }
//
//    public void logout() {
//        System.out.println("Calling SessionController.logout");
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) 
//        context.getExternalContext().getRequest();
//        try {
//            request.logout();
//        } catch (ServletException e) {
//            context.addMessage(null, new FacesMessage("Logout failed."));
//        }
//    }
//    
//    private String route66() {
//        System.out.println("Calling SessionController.route66");
//        currentAccount = getEjbFacade().find(rut);
//        if(currentAccount.getAccountType().getName().compareTo("admin") == 0) {
//            return "roles/admin/index";
//        }
//        if(currentAccount.getAccountType().getName().compareTo("fonoaudiologo") == 0) {
//            return "roles/fonoaudiologo/index";
//        }
//        if(currentAccount.getAccountType().getName().compareTo("matrona") == 0) {
//            return "roles/matrona/index";
//        }
//        if(currentAccount.getAccountType().getName().compareTo("medico") == 0) {
//            return "roles/medico/index";
//        }
//        return "roles/index";
//    }
//    
//    private String sha256(String base) {
//        try{
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(base.getBytes("UTF-8"));
//            StringBuffer hexString = new StringBuffer();
//
//            for (int i = 0; i < hash.length; i++) {
//                String hex = Integer.toHexString(0xff & hash[i]);
//                if(hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            
//            return hexString.toString();
//            
//        } catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }
    
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package managedbeans.util.validator;

import entities.Account;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import sessionbeans.AccountFacadeLocal;

@FacesValidator("Validator.AccessValidator")
public class AccessValidator implements Validator {

    private String rut;
    
    @EJB
    private AccountFacadeLocal ejbFacade;
    private Account selected;
    
    public AccessValidator() {
    }
    
    private AccountFacadeLocal getFacade() {
        return ejbFacade;
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        rut = value.toString();
        if (!accessRut(rut)) {

            FacesMessage msg = new FacesMessage("Usuario Deshabilitado, Contactese con la Administración", "Access Rut.");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            throw new ValidatorException(msg);
        }
    }

    public boolean accessRut(String rut) {
        boolean validacion = true;
        selected = getFacade().find(rut);
        if(selected != null){
            validacion = selected.isCurrentState();
        }
        return validacion;
    }
}
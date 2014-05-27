/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans.util.validator;

import entities.Account;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;
import sessionbeans.AccountFacadeLocal;

@FacesValidator("rutValidator")
public class Rut implements Validator, ClientValidator {
    
    @EJB
    private AccountFacadeLocal ejbFacade;
    
    public boolean check(String rut) {  
        boolean validacion = false;
        try {  
            rut =  rut.toUpperCase();            
            System.out.println(rut);
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");

            if(rut.endsWith("K")){
                rut = rut.replace("K", "11");
                int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 2));
                char dv = rut.charAt(rut.length() - 2);
                int m = 0, s = 1;
                for (; rutAux != 0; rutAux /= 10)
                        s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
                    if (dv == (char) (s != 0 ? s + 47 : 75))
                        validacion = true;
            }
            else{
                int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
                char dv = rut.charAt(rut.length() - 1);
                int m = 0, s = 1;
                for (; rutAux != 0; rutAux /= 10)
                    s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
                if (dv == (char) (s != 0 ? s + 47 : 75))
                    validacion = true;
            }
        }
        catch (Exception e) {
            System.out.println("managedbeans.util.validator.Rut.validate(): Exception throwed on Rut validation of " + rut);
        }
        return validacion;
    }
    
    private boolean checkExistence(String rut) {
        rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
        Account selected = ejbFacade.find(rut);
        if(selected != null) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(!check(value.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, value + " no es un Rut válido", 
                value + " no es un Rut válido"));
        }
        if(checkExistence(value.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, value + " ya existe en el sistema", 
                value + " ya existe en el sistema"));
        }
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return "rutValidator";
    }
}

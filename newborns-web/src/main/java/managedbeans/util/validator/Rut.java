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
    
    /**
    * Función que retorna un boolean para ver si el parámetro de entrada
    * Rut es válido, esta función tiene como parámetro de entrada un string.
    * @param rut string que contiene el rut que se desea verificar
    * @return boolean retorna verdadero si el rut es válido
    */ 
    public boolean check(String rut) {  
        boolean validacion = false;
        try {  
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10)
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            if (dv == (char) (s != 0 ? s + 47 : 75))
                validacion = true;
        }
        catch (NumberFormatException e) {
            System.out.println("managedbeans.util.validator.Rut.validate(): Exception throwed on Rut validation of " + rut);
        }
        return validacion;
    }
    
    /**
    *  Función que verifica la existencia de un rut en la base de datos.
    *  @param Rut string que vontiene el rut que se desea consultar
    *  @return boolean retorna verdadero si el rut existe en la base de datos
    */
    private boolean checkExistence(String rut) {
        Account selected = ejbFacade.find(rut);
        if(selected != null) {
            return true;
        }
        return false;
    }
    
    /**
    *  Función que valida en la interfaz si un rut es válido y no existe en la base de datos.
    *  @param  context contiene todos los estados de información de los pre-requisitos 
    *  relacionado a la ejecución de una petición de JavaServer Face, y la renderización de la 
    *  respuesta correspondiente.
    *  @param component componentes de interfaz de usuario que tienen un objeto Iteración de 
    *  negocios y la petición de un objetivo utilizado a lo largo de una solicitud de página
    *  y devuelven el HTML adecuado a la plantilla solicitada
    *  @param value objeto con los datos a evaluar
    *  
    */
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

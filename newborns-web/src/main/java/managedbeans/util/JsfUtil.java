package managedbeans.util;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {
    
    /**
     * Funcion que muestra los items seleccionados
     * @param entities lista de items disponibles para seleccionar
     * @param selectOne boleano que retorna verdadero si el item fue seleccionado
     * @return lista de los items seleccionados
     */
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }
    
    /**
     * Funcion que verifica el estado del fallo de una validacion
     * @return verdadero si fallo la validacion
     */

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }
    
    /**
     * Funcion que retorna un mensaje para una determinada excepcion
     * @param ex tipo de excepcion 
     * @param defaultMsg string que contiene el mensaje con el detalle a mostrar
     */

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }
    
    /**
     * Fucnion que retorna un error junto a una lista de mensajes con los detalles
     * del error
     * @param messages lista de mensajes que contienen el detalle a mostrar
     */
    
    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }
    
    /**
     * Funcion que retorna un mensaje de error junto a un mensaje con el detalle
     * del error
     * @param msg string que contiene el detalle del mensaje a mostrar
     */

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    /**
     * Funcion que retorna un mensaje de validacion junto a un mensaje con el detalle
     * de la validacion
     * @param msg string que contiene el detalle del mensaje a mostrar
     */

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    /**
     * Funcion que retorna un string con los parametros asociados a una llave
     * @param key string que contiene el valor de la llave primaria
     * @return 
     */
    
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }
    
    /**
     * Funcion que retorna un objeto de acuerdo al parametro ingresado
     * @param requestParameterName string que contiene el parametro con que se desea
     * consultar el objeto
     * @param converter es una interfaz que describe una clase Java que puede convertir 
     * un objeto a string  y un string a objeto de datos corresponden a la representacion 
     * de modelos adecuados para la renderizacion
     * @param component componentes de interfaz de usuario que tienen un objeto Iteración de 
     *  negocios y la petición de un objetivo utilizado a lo largo de una solicitud de página
     *  y devuelven el HTML adecuado a la plantilla solicitada
     * @return retorna un objeto relacionado al parametro requerido
     */

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
    
    public static enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }
    
    /**
     * Funcion que redirige al usuario
     * @param path string que contiene la direccion a la cual se redirigira el usuario
     */
    
    public static void redirect(String path) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + path);
        } catch (IOException ex) {
            System.out.println("JsfUtil: Cannot redirect");
        }
    }
}

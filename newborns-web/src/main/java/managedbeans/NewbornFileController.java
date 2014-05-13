package managedbeans;

import entities.NewbornFile;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.NewbornFileFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("newbornFileController")
@SessionScoped
public class NewbornFileController implements Serializable {

    @EJB
    private NewbornFileFacadeLocal ejbFacadeLocal;
    private List<NewbornFile> items = null;
    private NewbornFile selected;

    public NewbornFileController() {
    }

    public NewbornFile getSelected() {
        return selected;
    }

    public void setSelected(NewbornFile selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NewbornFileFacadeLocal getFacade() {
        return ejbFacadeLocal;
    }

    public NewbornFile prepareCreate() {
        selected = new NewbornFile();
        initializeEmbeddableKey();
        return selected;
    }
    
    /**
     * Crear una ficha de recien nacido
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NewbornFileCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    /**
     * Modificar una ficha de recien nacido
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NewbornFileUpdated"));
    }
    
    /**
     * Eliminar una ficha de recien nacido
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NewbornFileDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    /**
     * Lista de fichas de recien nacidos
     * @return 
     */
    public List<NewbornFile> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    /**
     * Funcion que llama a la unidad de persistencia y envia un mensaje al usuario
     * @param persistAction unidad de persistencia
     * @param successMessage string que contiene un mensaje de validacion
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Obtiene la ficha de recien nacido con el valor ingresado
     * @param id identificador de las ficha de recien nacido
     * @return ficha recien nacido con el identificador solicitado
     */
    public NewbornFile getNewbornFile(java.lang.Long id) {
        return getFacade().find(id);
    }

    /**
    * Lista todas las fichas de recien nacido del sistema
    * @return 
    */
    public List<NewbornFile> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    /**
     * Lista todas las fichas de recien nacido disponibles del sistema para ser seleccionados
     * @return 
    */    
    public List<NewbornFile> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = NewbornFile.class)
    public static class NewbornFileControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NewbornFileController controller = (NewbornFileController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "newbornFileController");
            return controller.getNewbornFile(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof NewbornFile) {
                NewbornFile o = (NewbornFile) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), NewbornFile.class.getName()});
                return null;
            }
        }

    }

}

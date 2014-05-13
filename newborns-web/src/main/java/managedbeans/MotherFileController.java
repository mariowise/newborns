package managedbeans;

import entities.MotherFile;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.MotherFileFacadeLocal;

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

@Named("motherFileController")
@SessionScoped
public class MotherFileController implements Serializable {

    @EJB
    private MotherFileFacadeLocal ejbFacadeLocal;
    private List<MotherFile> items = null;
    private MotherFile selected;

    public MotherFileController() {
    }

    public MotherFile getSelected() {
        return selected;
    }

    public void setSelected(MotherFile selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MotherFileFacadeLocal getFacade() {
        return ejbFacadeLocal;
    }

    public MotherFile prepareCreate() {
        selected = new MotherFile();
        initializeEmbeddableKey();
        return selected;
    }

    /**
     * Crear ficha de la madre
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MotherFileCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    /**
     * Modificar ficha de la madre
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MotherFileUpdated"));
    }

    /**
     * Eliminar ficha de la madre
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MotherFileDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Lista de fichas de las madres
     * @return 
     */
    public List<MotherFile> getItems() {
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
     * Obtiene la ficha de la madre con el valor ingresado
     * @param id identificador de las ficha de la madre
     * @return ficha de la madre con el identificador solicitado
     */
    public MotherFile getMotherFile(java.lang.Long id) {
        return getFacade().find(id);
    }

    /**
    * Lista todas las fichas de la madre del sistema
    * @return 
    */
    public List<MotherFile> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    /**
     * Lista todas las fichas de la madre disponibles del sistema para ser seleccionados
     * @return 
    */
    public List<MotherFile> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MotherFile.class)
    public static class MotherFileControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MotherFileController controller = (MotherFileController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "motherFileController");
            return controller.getMotherFile(getKey(value));
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
            if (object instanceof MotherFile) {
                MotherFile o = (MotherFile) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MotherFile.class.getName()});
                return null;
            }
        }

    }

}

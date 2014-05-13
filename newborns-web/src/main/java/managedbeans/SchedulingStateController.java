package managedbeans;

import entities.SchedulingState;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.SchedulingStateFacadeLocal;

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

@Named("schedulingStateController")
@SessionScoped
public class SchedulingStateController implements Serializable {

    @EJB
    private SchedulingStateFacadeLocal ejbFacadeLocal;
    private List<SchedulingState> items = null;
    private SchedulingState selected;

    public SchedulingStateController() {
    }

    public SchedulingState getSelected() {
        return selected;
    }

    public void setSelected(SchedulingState selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SchedulingStateFacadeLocal getFacade() {
        return ejbFacadeLocal;
    }
    
    public SchedulingState prepareCreate() {
        selected = new SchedulingState();
        initializeEmbeddableKey();
        return selected;
    }
    
    /**
     * Crear un estado de agendamiento
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SchedulingStateCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    /**
     * Modificar un estado de agendamiento
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SchedulingStateUpdated"));
    }

    /**
     * Elimina un estado de agendamiento
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SchedulingStateDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Lista de estados de agendamiento
     * @return 
     */
    public List<SchedulingState> getItems() {
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
     * Obtiene el estado de agendamiento con el valor ingresado
     * @param id identificador del estado de agendamiento
     * @return estado de agendamiento con el identificador solicitado
     */
    public SchedulingState getSchedulingState(java.lang.Long id) {
        return getFacade().find(id);
    }

    /**
    * Lista todos los estados de agendamiento del sistema
    * @return 
    */
    public List<SchedulingState> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    /**
     * Lista todos los estados de agendamiento disponibles del sistema para ser seleccionados
     * @return 
    */
    public List<SchedulingState> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = SchedulingState.class)
    public static class SchedulingStateControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SchedulingStateController controller = (SchedulingStateController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "schedulingStateController");
            return controller.getSchedulingState(getKey(value));
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
            if (object instanceof SchedulingState) {
                SchedulingState o = (SchedulingState) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SchedulingState.class.getName()});
                return null;
            }
        }

    }

}

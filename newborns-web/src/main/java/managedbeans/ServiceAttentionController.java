package managedbeans;

import entities.Person;
import entities.ServiceAttention;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.ServiceAttentionFacadeLocal;

@Named("serviceAttentionController")
@SessionScoped
public class ServiceAttentionController implements Serializable {

    @EJB
    private ServiceAttentionFacadeLocal ejbFacadeLocal;
    private List<ServiceAttention> items = null;
    private ServiceAttention selected;
    
    private Person personSelected;

    public ServiceAttentionController() {
    }

    public ServiceAttention getSelected() {
        return selected;
    }

    public void setSelected(ServiceAttention selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public ServiceAttentionFacadeLocal getFacade() {
        return ejbFacadeLocal;
    }

    public ServiceAttention prepareCreate() {
        selected = new ServiceAttention();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ServiceAttentionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ServiceAttentionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ServiceAttentionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ServiceAttention> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

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

    public ServiceAttention getServiceAttention(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ServiceAttention> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ServiceAttention> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ServiceAttention.class)
    public static class ServiceAttentionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ServiceAttentionController controller = (ServiceAttentionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "serviceAttentionController");
            return controller.getServiceAttention(getKey(value));
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
            if (object instanceof ServiceAttention) {
                ServiceAttention o = (ServiceAttention) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ServiceAttention.class.getName()});
                return null;
            }
        }

    }

    public Person getPersonSelected() {
        return personSelected;
    }

    public void setPersonSelected(Person personSelected) {
        this.personSelected = personSelected;
    }

}

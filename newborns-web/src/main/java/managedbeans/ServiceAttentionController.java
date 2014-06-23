package managedbeans;

import entities.ServiceAttention;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import managedbeans.util.SessionUtil;
import sessionbeans.ServiceAttentionFacadeLocal;

@Named("serviceAttentionController")
@SessionScoped
public class ServiceAttentionController implements Serializable {

    @EJB
    private ServiceAttentionFacadeLocal ejbFacade;
    private List<ServiceAttention> items = null;
    private List<ServiceAttention> allItems = null;
    private ServiceAttention selected;
    
    private List<ServiceAttention> filteredItems;

    @Inject
    private SessionUtil sessionUtil;
    
    @Inject
    private MotherController motherController;
    
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

    private ServiceAttentionFacadeLocal getFacade() {
        return ejbFacade;
    }

    public ServiceAttention prepareCreate() {
        selected = new ServiceAttention();
        selected.setCreator(sessionUtil.getCurrentUser());
        selected.setMother(motherController.getSelected());
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
        motherController.refreshSelected();
        items = motherController.getSelected().getAttentions();
        return items;
    }
    
    public List<ServiceAttention> getAllItems() {
        allItems = getFacade().findAll();
        return allItems;
    }
    
    public Map getRegisteredItems(String attentionType) {
        
        getAllItems();
        Map registeredItems = new HashMap<>();
        if (attentionType.contentEquals("Mother")) {
            for(ServiceAttention item : allItems) {
                if (item.getMother() != null) {
                    Calendar myCal = new GregorianCalendar();
                    myCal.setTime(item.getCreatedAt());
                    String key = String.valueOf(myCal.get(Calendar.YEAR));
                    int value = 0;
                    if (registeredItems.get(key) != null) {
                        value = (int) registeredItems.get(key);
                    }
                    registeredItems.put(key , value + 1);            
                } 
            }                        
        } else {
            for(ServiceAttention item : allItems) {                
                Calendar myCal = new GregorianCalendar();
                myCal.setTime(item.getCreatedAt());
                String key = String.valueOf(myCal.get(Calendar.YEAR));
                int value = 0;
                if (registeredItems.get(key) != null) {
                    value = (int) registeredItems.get(key);
                }
                registeredItems.put(key , value + 1); 
            }
        }
        return registeredItems;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if(persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                } else if (persistAction != PersistAction.DELETE) {
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

    public List<ServiceAttention> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<ServiceAttention> filteredItems) {
        this.filteredItems = filteredItems;
    }

}

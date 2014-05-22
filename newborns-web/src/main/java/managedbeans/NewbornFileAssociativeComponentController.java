package managedbeans;

import entities.NewbornFileAssociativeComponent;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.NewbornFileAssociativeComponentFacadeLocal;

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

@Named("newbornFileAssociativeComponentController")
@SessionScoped
public class NewbornFileAssociativeComponentController implements Serializable {

    @EJB
    private NewbornFileAssociativeComponentFacadeLocal ejbFacadeLocal;
    private List<NewbornFileAssociativeComponent> items = null;
    private NewbornFileAssociativeComponent selected;

    public NewbornFileAssociativeComponentController() {
    }

    public NewbornFileAssociativeComponent getSelected() {
        return selected;
    }

    public void setSelected(NewbornFileAssociativeComponent selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NewbornFileAssociativeComponentFacadeLocal getFacade() {
        return ejbFacadeLocal;
    }

    public NewbornFileAssociativeComponent prepareCreate() {
        selected = new NewbornFileAssociativeComponent();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NewbornFileAssociativeComponentCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NewbornFileAssociativeComponentUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NewbornFileAssociativeComponentDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<NewbornFileAssociativeComponent> getItems() {
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

    public NewbornFileAssociativeComponent getNewbornFileAssociativeComponent(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<NewbornFileAssociativeComponent> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<NewbornFileAssociativeComponent> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = NewbornFileAssociativeComponent.class)
    public static class NewbornFileAssociativeComponentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NewbornFileAssociativeComponentController controller = (NewbornFileAssociativeComponentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "newbornFileAssociativeComponentController");
            return controller.getNewbornFileAssociativeComponent(getKey(value));
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
            if (object instanceof NewbornFileAssociativeComponent) {
                NewbornFileAssociativeComponent o = (NewbornFileAssociativeComponent) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), NewbornFileAssociativeComponent.class.getName()});
                return null;
            }
        }

    }

}

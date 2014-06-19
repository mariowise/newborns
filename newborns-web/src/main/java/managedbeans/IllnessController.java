package managedbeans;

import entities.Illness;
import java.io.Serializable;
import java.util.ArrayList;
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
import sessionbeans.IllnessFacadeLocal;

@Named("illnessController")
@SessionScoped
public class IllnessController implements Serializable {

    @EJB
    private IllnessFacadeLocal ejbFacade;
    private List<Illness> items = null;
    private Illness selected;

    public IllnessController() {
    }

    public Illness getSelected() {
        return selected;
    }

    public void setSelected(Illness selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IllnessFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Illness prepareCreate() {
        selected = new Illness();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IllnessCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IllnessUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IllnessDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Illness> getItems() {
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

    public Illness getIllness(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Illness> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Illness> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Illness> filterIllness(String query) {
        List<Illness> allIllness = getFacade().findAll();
        List<Illness> filteredIllness = new ArrayList<Illness>();
        
        for(int i = 0; i < allIllness.size(); i++) {
            Illness skin = allIllness.get(i);
            if(skin.getName().toLowerCase().startsWith(query)) {
                filteredIllness.add(skin);
            }                
        }
        
        return filteredIllness;
    }

    @FacesConverter(forClass = Illness.class)
    public static class IllnessControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IllnessController controller = (IllnessController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "illnessController");
            return controller.getIllness(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Illness) {
                Illness o = (Illness) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Illness.class.getName()});
                return null;
            }
        }

    }

}

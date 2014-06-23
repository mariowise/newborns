package managedbeans;

import entities.core.Addiction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.MapExpression;
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
import sessionbeans.AddictionFacadeLocal;

@Named("addictionController")
@SessionScoped
public class AddictionController implements Serializable {

    @EJB
    private AddictionFacadeLocal ejbFacade;
    private List<Addiction> items = null;
    private Addiction selected;
    
    @Inject
    private MotherController motherController;

    public AddictionController() {
    }

    public Addiction getSelected() {
        return selected;
    }

    public void setSelected(Addiction selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AddictionFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Addiction prepareCreate() {
        selected = new Addiction();
        selected.setMother(motherController.getSelected());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AddictionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AddictionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AddictionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Addiction> getItems() {
        motherController.refreshSelected();            
        items = motherController.getSelected().getAddictions();        
        refreshSelected();
        return items;
    }
    
    public List<Addiction> getAllItems() {           
        items = getFacade().findAll();
        if (items == null) {
            items = new ArrayList<Addiction>();
        }
        return items;
    }
    
    public Map getAddictionsByType(String addictionType) {
        List<Addiction> allAddictions = getAllItems();
        Map mapSelectedAddictions = new HashMap<>();
        
        for(Addiction addiction : allAddictions) {
            if (addictionType.equals(addiction.getType().getName())) {
                Calendar myCal = new GregorianCalendar();
                myCal.setTime(addiction.getRecordDate());
                String key = String.valueOf(myCal.get(Calendar.YEAR));
                int value = 0;
                if (mapSelectedAddictions.get(key) != null) {
                    value = (int) mapSelectedAddictions.get(key);
                }
                mapSelectedAddictions.put(key , value + 1);
            }
        }
        
        return mapSelectedAddictions;
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

    public Addiction getAddiction(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public void refreshSelected() {
        if (selected != null) {
            Long id = selected.getId();
            if(id!=null){
                selected = getAddiction(id);
            }
        }
    }

    public List<Addiction> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Addiction> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Addiction.class)
    public static class AddictionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AddictionController controller = (AddictionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "addictionController");
            return controller.getAddiction(getKey(value));
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
            if (object instanceof Addiction) {
                Addiction o = (Addiction) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Addiction.class.getName()});
                return null;
            }
        }

    }

}

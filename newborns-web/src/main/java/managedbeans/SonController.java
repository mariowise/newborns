package managedbeans;

import entities.core.Profile;
import entities.core.Son;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import sessionbeans.SonFacadeLocal;

@Named("sonController")
@SessionScoped
public class SonController implements Serializable {

    @EJB
    private SonFacadeLocal ejbFacade;
    private List<Son> items = null;
    private List<Son> allItems = null;
    private Son selected;
    private boolean extremePremature;
    
    @Inject
    private MotherController motherController;
    
    @Inject
    private DeliveryController deliveryController;
    
    @Inject
    private ProfileController profileController;
    
    @Inject
    private ExamPhaseController examPhaseController;
    
    @Inject
    private SessionUtil sessionUtil;
    
    private List<Son> filteredItems;
    
    public SonController() {
    }

    public Son getSelected() {
        return selected;
    }

    public void setSelected(Son selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SonFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Son prepareCreate() {
        selected = new Son();
        selected.setProfile(new Profile());
        selected.setMother(motherController.getSelected());
        selected.setDelivery(deliveryController.getSelected());
        selected.setExamPhase(examPhaseController.getExamPhase(new Long(1)));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SonCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            allItems = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SonUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SonDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            allItems = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Son> getItems() {
        motherController.refreshSelected();
        items = motherController.getSelected().getSons();
        refreshSelected();
        return items;
    }
    
    public List<Son> getAllItems() {
        allItems = getFacade().findAll();
        return allItems;
    }
    
    public List<Son> getPrematureItems() {
        List<Son> allSons = getAllItems();
        List<Son> selectedSons = new ArrayList<Son>();
        for(Son son : allSons) {
            if (son.getExtremePremature()) {
                selectedSons.add(son);
            }
        }
        return selectedSons;
    }
    
    public int countPrematureItems() {
        List<Son> allSons = getAllItems();
        int amount = 0;
        for(Son son : allSons) {
            if (son.getExtremePremature()) {
                amount++;
            }
        }
        return amount;
    }
    
    public Map getRegisteredItems() {
        
        getAllItems();
        Map registeredItems = new HashMap<>();
        
        for(Son item : allItems) {            
            String key = item.getYear().getId().toString();
            int value = 0;
            if (registeredItems.get(key) != null) {
                value = (int) registeredItems.get(key);
            }
            registeredItems.put(key , value + 1);            
        }
        return registeredItems;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        Premature();
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if(persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                } else if(persistAction != PersistAction.DELETE) {
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

    public Son getSon(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public void Premature(){
        if (selected.getPediatricExamination()<32){
            extremePremature=true;            
        } else {
            extremePremature=false;            
        }
        selected.setExtremePremature(extremePremature);
    }
    public void refreshSelected() {
        if (selected != null) {
            Long id = selected.getId();
            if(id!=null){
                selected = getSon(id);
            }
        }
    }

    public List<Son> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Son> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Son.class)
    public static class SonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SonController controller = (SonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sonController");
            return controller.getSon(getKey(value));
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
            if (object instanceof Son) {
                Son o = (Son) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Son.class.getName()});
                return null;
            }
        }

    }

    public List<Son> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Son> filteredItems) {
        this.filteredItems = filteredItems;
    }
    
    public void profileView() {
        profileController.setSelected(selected.getProfile());
        profileController.refreshSelected();
        JsfUtil.redirect("/faces/roles/" + sessionUtil.getCurrentUser().getAccountType().getName() + 
                "/index-profile.xhtml");
    }
    
    public void restartPhases() {
        selected.setExamPhase(examPhaseController.getExamPhase(new Long(1)));
        persist(PersistAction.UPDATE, "Se ha reiniciado el proceso de tamizaje a la Fase 1");
        refreshSelected();
    }

}

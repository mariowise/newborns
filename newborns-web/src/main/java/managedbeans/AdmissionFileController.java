package managedbeans;

import entities.AdmissionFile;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
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
import sessionbeans.AdmissionFileFacadeLocal;


@Named("admissionFileController")
@SessionScoped
public class AdmissionFileController implements Serializable {


    @EJB private AdmissionFileFacadeLocal ejbFacadeLocal;
    private List<AdmissionFile> items = null;
    private List<AdmissionFile> filteredAdmissionFiles = null;
    private AdmissionFile selected;

    public AdmissionFileController() {
    }

    public AdmissionFile getSelected() {
        return selected;
    }

    public void setSelected(AdmissionFile selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmissionFileFacadeLocal getFacade() {
        return ejbFacadeLocal;
    }

    public AdmissionFile prepareCreate() {
        selected = new AdmissionFile();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AdmissionFileCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AdmissionFileUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AdmissionFileDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmissionFile> getItems() {
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

    public AdmissionFile getAdmissionFile(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<AdmissionFile> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmissionFile> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=AdmissionFile.class)
    public static class AdmissionFileControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdmissionFileController controller = (AdmissionFileController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "admissionFileController");
            return controller.getAdmissionFile(getKey(value));
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
            if (object instanceof AdmissionFile) {
                AdmissionFile o = (AdmissionFile) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AdmissionFile.class.getName()});
                return null;
            }
        }

    }
    
    public boolean filterByRut(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }

    public List<AdmissionFile> getFilteredAdmissionFiles() {
        return filteredAdmissionFiles;
    }

    public void setFilteredAdmissionFiles(List<AdmissionFile> filteredAdmissionFiles) {
        this.filteredAdmissionFiles = filteredAdmissionFiles;
    }

}

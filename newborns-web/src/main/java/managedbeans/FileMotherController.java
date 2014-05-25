package managedbeans;

import entities.File;
import entities.FileMother;
import java.io.Serializable;
import java.util.Date;
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
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import managedbeans.util.SessionUtil;
import sessionbeans.FileFacadeLocal;
import sessionbeans.FileMotherFacadeLocal;

@Named("fileMotherController")
@SessionScoped
public class FileMotherController implements Serializable {

    @EJB
    private FileMotherFacadeLocal ejbFacade;
    private List<FileMother> items = null;
    private FileMother selected;
    
    @Inject
    private SessionUtil sessionUtil;

    public FileMotherController() {
    }

    public FileMother getSelected() {
        return selected;
    }

    public void setSelected(FileMother selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FileMotherFacadeLocal getFacade() {
        return ejbFacade;
    }

    public FileMother prepareCreate() {
        selected = new FileMother();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FileMotherCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FileMotherUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FileMotherDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FileMother> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if(persistAction == PersistAction.CREATE) {
                    // Creando la ficha
                    System.out.println("Creando la Ficha");
                    File newFile = new File();
                    newFile.setCreatedAt(new Date());
                    newFile.setCreator(sessionUtil.getCurrentUser());
                    
                    // Creando ficha de la madre
                    selected.setFile(newFile);
                    getFacade().edit(selected);
                }
                else if (persistAction != PersistAction.DELETE) {
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

    public FileMother getFileMother(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<FileMother> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FileMother> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FileMother.class)
    public static class FileMotherControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FileMotherController controller = (FileMotherController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fileMotherController");
            return controller.getFileMother(getKey(value));
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
            if (object instanceof FileMother) {
                FileMother o = (FileMother) object;
                return getStringKey(o.getFileCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FileMother.class.getName()});
                return null;
            }
        }

    }

}

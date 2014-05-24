package managedbeans;

import entities.File;
import entities.FileNewborn;
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
import sessionbeans.FileFacadeLocal;
import sessionbeans.FileNewbornFacadeLocal;

@Named("fileNewbornController")
@SessionScoped
public class FileNewbornController implements Serializable {

    @EJB
    private FileNewbornFacadeLocal ejbFacade;
    private List<FileNewborn> items = null;
    private FileNewborn selected;
    
    @EJB
    private FileFacadeLocal fileFacade;

    public FileNewbornController() {
    }

    public FileNewborn getSelected() {
        return selected;
    }

    public void setSelected(FileNewborn selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FileNewbornFacadeLocal getFacade() {
        return ejbFacade;
    }

    public FileNewborn prepareCreate() {
        selected = new FileNewborn();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FileNewbornCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FileNewbornUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FileNewbornDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FileNewborn> getItems() {
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
                    // Creación de la ficha
                    System.out.println("Creando ficha");
                    File newFile = new File();
                    fileFacade.edit(newFile);
                    selected.setFile(newFile);
                    
                    // Creación de la fichaNeonato
                    System.out.println("Creando ficha Neonato");
                    getFacade().edit(selected);
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

    public FileNewborn getFileNewborn(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<FileNewborn> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FileNewborn> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FileNewborn.class)
    public static class FileNewbornControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FileNewbornController controller = (FileNewbornController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fileNewbornController");
            return controller.getFileNewborn(getKey(value));
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
            if (object instanceof FileNewborn) {
                FileNewborn o = (FileNewborn) object;
                return getStringKey(o.getFileCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FileNewborn.class.getName()});
                return null;
            }
        }

    }

}

package managedbeans;

import entities.AccountType;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.AccountTypeFacadeLocal;

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

@Named("accountTypeController")
@SessionScoped
public class AccountTypeController implements Serializable {

    @EJB
    private AccountTypeFacadeLocal ejbFacade;
    private List<AccountType> items = null;
    private AccountType selected;

    public AccountTypeController() {
    }

    public AccountType getSelected() {
        return selected;
    }

    public void setSelected(AccountType selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AccountTypeFacadeLocal getFacade() {
        return ejbFacade;
    }

    public AccountType prepareCreate() {
        selected = new AccountType();
        initializeEmbeddableKey();
        return selected;
    }

    /**
     * Crear un tipo de usuario
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AccountTypeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Modificar tipo de usuario
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AccountTypeUpdated"));
    }

    /**
     * Eliminar tipo de usuario
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AccountTypeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Lista de tipos de usuario
     * @return 
     */
    public List<AccountType> getItems() {
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
     * Obtiene el tipo de cuenta con el valor ingresado
     * @param id identificador del tipo de cuenta
     * @return tipo de cuenta con el identificador solicitado
     */    
    public AccountType getAccountType(java.lang.Long id) {
        return getFacade().find(id);
    }

    /**
    * Lista todos los tipos de usuario del sistema
    * @return 
    */
    public List<AccountType> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    /**
     * Lista todos los tipos de usuario disponibles del sistema para ser seleccionados
     * @return 
    */
    public List<AccountType> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AccountType.class)
    public static class AccountTypeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AccountTypeController controller = (AccountTypeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "accountTypeController");
            return controller.getAccountType(getKey(value));
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
            if (object instanceof AccountType) {
                AccountType o = (AccountType) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AccountType.class.getName()});
                return null;
            }
        }

    }

}

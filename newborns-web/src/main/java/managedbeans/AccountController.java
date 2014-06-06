package managedbeans;

import entities.Account;
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
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import managedbeans.util.Mailer;
import managedbeans.util.SessionUtil;
import sessionbeans.AccountFacadeLocal;

@Named("accountController")
@SessionScoped
public class AccountController implements Serializable {

    @EJB
    private AccountFacadeLocal ejbFacade;
    private List<Account> items = null;
    private Account selected;
    
    @Inject
    private SessionUtil sessionUtil;
    
    @Inject 
    private Mailer sendMail;
    
    private String rut;
    
    private String email;
    
    private boolean currentState;

    public AccountController() {
    }

    public Account getSelected() {
        return selected;
    }

    public void setSelected(Account selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AccountFacadeLocal getFacade() {
        return ejbFacade;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCurrentState() {
        return currentState;
    }

    public void setCurrentState(boolean currentState) {
        this.currentState = currentState;
    }

    public Account prepareCreate() {
        selected = new Account();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "El usuario ha sido creado");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "El usuario ha sido actualizado");
    }
    
    public void updateAndGo(String destiny) {
        persist(PersistAction.UPDATE, "El usuario ha sido actualizado");
        JsfUtil.redirect(destiny);
    }

    public void destroy() {
        if(sessionUtil.getCurrentUser().getRut().compareTo(selected.getRut()) != 0) {
            persist(PersistAction.DELETE, "El usuario se ha deshabilitado");
            if (!JsfUtil.isValidationFailed()) {
                selected = null; // Remove selection
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            JsfUtil.addErrorMessage("No puede eliminar su propia cuenta.");
        }
    }
    
    public void restorePassword() {
        System.out.println("AccountController: restore password triggered for user " + selected.getRut());
        String newPassword;
        try {
            newPassword = sendMail.sendPasswordRecoveryMessage(selected);
            selected.setPassword(newPassword);
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Se ha enviado un correo electrónico reestableciendo la contraseña.");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("No ha sido posible reestablecer la contraseña, inténtelo mas tarde.");
        }
    }
    
    public void recoverPassword() {
        System.out.println("AccountController: recover password triggered for user " + rut);
        selected = getAccount(rut);
        if(selected.getEmail().compareTo(email) == 0) {
            restorePassword();
        } else {
            JsfUtil.addErrorMessage("Los datos no coinciden con ningún usuario registrado.");
        }
        rut = "";
        email = "";
    }

    public List<Account> getItems() {
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
                    if (currentState==false) {
                        currentState = true;                        
                    }else{
                        currentState = false;
                    }
                    getFacade().edit(selected);
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

    public Account getAccount(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Account> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Account> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Account.class)
    public static class AccountControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AccountController controller = (AccountController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "accountController");
            return controller.getAccount(getKey(value));
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
            if (object instanceof Account) {
                Account o = (Account) object;
                return getStringKey(o.getRut());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Account.class.getName()});
                return null;
            }
        }

    }

}

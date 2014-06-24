package managedbeans;

import entities.ExamPhase;
import entities.tau.Exam;
import entities.tau.OaeAabr;
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
import sessionbeans.OaeAabrFacadeLocal;

@Named("oaeAabrController")
@SessionScoped
public class OaeAabrController implements Serializable {

    @EJB
    private OaeAabrFacadeLocal ejbFacade;
    private List<OaeAabr> items = null;
    private OaeAabr selected;
    private int phase = 1;
    
    @Inject 
    private SonController sonController;
    
    @Inject 
    private ExamPhaseController examPhaseController;
    
    @Inject
    private ExamController examController;
    
    public OaeAabrController() {
    }

    public OaeAabr getSelected() {
        return selected;
    }

    public void setSelected(OaeAabr selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    protected void initializeEmbeddableKey() {
    }

    private OaeAabrFacadeLocal getFacade() {
        return ejbFacade;
    }

    public OaeAabr prepareCreate() {
        selected = new OaeAabr();
        selected.setExam(new Exam());
        selected.getExam().setCreated(new Date());
        selected.getExam().setSon(sonController.getSelected());
        selected.getExam().setPhase(examPhaseController.getExamPhase(Long.parseLong(Integer.toString(phase))));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OaeAabrCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OaeAabrUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OaeAabrDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<OaeAabr> getItems() {
        sonController.refreshSelected();
        refreshSelected();
        items = getFacade().findBySon(sonController.getSelected(), phase);
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                    updateSon(selected.getFinalResult());
                } else if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                    examController.setSelected(selected.getExam());
                    examController.update();
                    updateSon(selected.getFinalResult());
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
    
    private void updateSon(String order) {
        System.out.println("Update son para order: " + order);
        ExamPhase ep;
                
        if(order.compareTo("Re-Screening") == 0) {
            System.out.println("Re-Screening");
            ep = examPhaseController.getExamPhase(new Long(2));
            System.out.println("- Seteando " + ep);
            sonController.getSelected().setExamPhase(ep);
        } else if(order.compareTo("Control") == 0) {
            ep = examPhaseController.getExamPhase(new Long(3));
            sonController.getSelected().setExamPhase(ep);
        }
        sonController.update();
        sonController.refreshSelected();
        refreshSelected();
    }

    public OaeAabr getOaeAabr(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public void refreshSelected() {
        if(selected != null) {
            if(selected.getId() != null) {
                selected = getOaeAabr(selected.getId());
            }
        }
    }

    public List<OaeAabr> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<OaeAabr> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = OaeAabr.class)
    public static class OaeAabrControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OaeAabrController controller = (OaeAabrController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "oaeAabrController");
            return controller.getOaeAabr(getKey(value));
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
            if (object instanceof OaeAabr) {
                OaeAabr o = (OaeAabr) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OaeAabr.class.getName()});
                return null;
            }
        }

    }
    
    public boolean checkCreateButton() {
        if(sonController.getSelected() == null)
            return false;
        ExamPhase examPhase = sonController.getSelected().getExamPhase();
        
        // System.out.println("Phase: " + phase);
        // System.out.println("son.phase: " + sonController.getSelected().getExamPhase());
        
        if(phase == 1) {
            if(examPhase == null)
                return true;
            else if(examPhase.getId() == 1)
                return true;
        } else if(phase == 2) {
            if(examPhase == null)
                return false;
            else if(examPhase.getId() == 2)
                return true;
        } else if(phase == 3) { 
            if(examPhase == null)
                return false;
            if(examPhase.getId() == 3)
                return true;
        }
        return false;
    }

}

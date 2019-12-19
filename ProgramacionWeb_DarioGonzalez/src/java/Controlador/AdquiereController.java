package Controlador;

import Modelo_bd.Adquiere;
import Controlador.util.JsfUtil;
import Controlador.util.JsfUtil.PersistAction;

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

@Named("adquiereController")
@SessionScoped
public class AdquiereController implements Serializable {

    @EJB
    private Controlador.AdquiereFacade ejbFacade;
    private List<Adquiere> items = null;
    private Adquiere selected;

    public AdquiereController() {
    }

    public Adquiere getSelected() {
        return selected;
    }

    public void setSelected(Adquiere selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getAdquierePK().setLibroCodigo(selected.getLibro().getCodigo());
        selected.getAdquierePK().setUsuarioCedula(selected.getUsuario().getCedula());
    }

    protected void initializeEmbeddableKey() {
        selected.setAdquierePK(new Modelo_bd.AdquierePK());
    }

    private AdquiereFacade getFacade() {
        return ejbFacade;
    }

    public Adquiere prepareCreate() {
        selected = new Adquiere();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AdquiereCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AdquiereUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AdquiereDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Adquiere> getItems() {
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

    public Adquiere getAdquiere(Modelo_bd.AdquierePK id) {
        return getFacade().find(id);
    }

    public List<Adquiere> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Adquiere> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Adquiere.class)
    public static class AdquiereControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdquiereController controller = (AdquiereController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "adquiereController");
            return controller.getAdquiere(getKey(value));
        }

        Modelo_bd.AdquierePK getKey(String value) {
            Modelo_bd.AdquierePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Modelo_bd.AdquierePK();
            key.setUsuarioCedula(values[0]);
            key.setLibroCodigo(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Modelo_bd.AdquierePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsuarioCedula());
            sb.append(SEPARATOR);
            sb.append(value.getLibroCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Adquiere) {
                Adquiere o = (Adquiere) object;
                return getStringKey(o.getAdquierePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Adquiere.class.getName()});
                return null;
            }
        }

    }

}

package Beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped

public class Login {

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void loginUser(){     
        
        if (user != null && password!= null) {
            if (user.equals("dax") && password.equals("1234")) {
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Bienvenido " + user));
            }else{
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",null + "Debe Ingresar Usuario y Contraseña"));
            }
        }

        //FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Welcome " + user + " " + password));
    }
    
    public Login() {
    
    }
}
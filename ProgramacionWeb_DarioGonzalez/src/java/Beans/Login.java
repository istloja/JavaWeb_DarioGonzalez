package Beans;

import Modelo.Usuarios;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped

public class Login {

    private List <Usuarios> lista_usuarios;
    private String user;
    private String pasword;
   
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
    
    
    
    public String loginUser(){   
        
        String url = null;
        
        for (Usuarios usuario : lista_usuarios) {
            
            if (user!=null && user.equals(usuario.getUsuario()) && pasword!=null && pasword.equals(usuario.getContrasenia())) {
                System.out.println("ingreso");
                
                HttpSession sesion = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                sesion.setAttribute("user", user);
                
                switch(usuario.getRol()){
                    
                    case "Estudiante":
                        System.out.println("estudiante");
                        url = "paginaEstudiante.xhtml?faces-redirect=true";
                        break;
                        
                    case "Docente":
                        System.out.println("docente");
                        url = "paginaDocente.xhtml?faces-redirect=true";
                        break;
                        
                    case "Administrador":
                        System.out.println("administrador");
                        url = "paginaAdministrador.xhtml?faces-redirect=true";
                        break;
                }
            break;    
            }else{
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",null));
            }
        }        
        return url;
    }
    
    public Login() {
        lista_usuarios = new ArrayList<Usuarios>();
        lista_usuarios.add(new Usuarios("Dario", "Dax", "1234", "Estudiante"));
        lista_usuarios.add(new Usuarios("Steve", "steve", "1234", "Docente"));
        lista_usuarios.add(new Usuarios("Chimbo", "chimbo", "1234", "Estudiante"));
        lista_usuarios.add(new Usuarios("Cesar", "cesar", "1234", "Estudiante"));   
        lista_usuarios.add(new Usuarios("Cristian", "cristian", "1234", "Docente"));
        lista_usuarios.add(new Usuarios("Joan", "joan", "1234", "Estudiante"));
        lista_usuarios.add(new Usuarios("Alexis", "alexis", "1234", "Docente"));
        lista_usuarios.add(new Usuarios("Jefferson", "jefferson", "1234", "Administrador"));

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Modelo_bd.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Dax
 */
@Stateless
@Path("modelo_bd.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ProgramacionWeb_DarioGonzalezPU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Usuario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Path("crear_usuario")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String crearUsuario(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("rol") Integer rol, 
                            @FormParam("user_name") String user_name, @FormParam("password") String password){
    
        Usuario usuario = new  Usuario();
        
        usuario.setCedula(cedula);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setRol(rol);
        usuario.setUserName(user_name);
        usuario.setPassword(password); 
        
        super.create(usuario);
        
        return "Usuario Creado";
    }
    
   
    
    @POST
    @Path("editar_usuario")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String editarUsuario(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("rol") Integer rol, 
                            @FormParam("user_name") String user_name, @FormParam("password") String password){
        
        Usuario usuario = new  Usuario();
                
        if (nombre != null && nombre != usuario.getNombre()) {
            
            usuario.setNombre(nombre);
        }
        if (apellido!= null && apellido != usuario.getApellido()) {
            usuario.setApellido(apellido);
            
        }
        if (rol != null && rol != usuario.getRol()) {
            usuario.setRol(rol);
        }
        
        if (user_name != null && user_name != usuario.getUserName()) {
             usuario.setUserName(user_name);
        }
       
        if (password != null && password != usuario.getPassword()) {
            usuario.setPassword(password);
        }
        
        super.edit(usuario);
        
        return "Usuario Editado";
    }
    
    @POST
    @Path("eliminar_usuario")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String eliminarUsuario(@FormParam("cedula") String cedula){
        
        super.remove(super.find(cedula));
        return "Usuario Eliminado";
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo_bd.Libro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dax
 */
@Stateless
public class LibroFacade extends AbstractFacade<Libro> {

    @PersistenceContext(unitName = "ProgramacionWeb_DarioGonzalezPU")
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List <Libro> Libros(){
    
        String valor = "Yo, Robot";
        List <Libro> libro = null;
        TypedQuery<Libro> consulta = getEntityManager().createQuery("SELECT l FROM Libro l WHERE l.codigo = :codigo",Libro.class);
        consulta.setParameter("codigo",2);
        libro = consulta.getResultList();
        
        return libro;
    }
            
    public LibroFacade() {
        super(Libro.class);
    }
    
}

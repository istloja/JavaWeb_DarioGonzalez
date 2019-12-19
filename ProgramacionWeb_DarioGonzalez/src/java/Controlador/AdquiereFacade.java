/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo_bd.Adquiere;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dax
 */
@Stateless
public class AdquiereFacade extends AbstractFacade<Adquiere> {

    @PersistenceContext(unitName = "ProgramacionWeb_DarioGonzalezPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdquiereFacade() {
        super(Adquiere.class);
    }
    
}

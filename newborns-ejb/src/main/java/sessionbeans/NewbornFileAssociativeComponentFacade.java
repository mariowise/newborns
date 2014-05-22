/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.NewbornFileAssociativeComponent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sylar
 */
@Stateless
public class NewbornFileAssociativeComponentFacade extends AbstractFacade<NewbornFileAssociativeComponent> implements NewbornFileAssociativeComponentFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewbornFileAssociativeComponentFacade() {
        super(NewbornFileAssociativeComponent.class);
    }
    
}

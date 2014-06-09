/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Delivery;
import entities.core.Revival;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sylar
 */
@Stateless
public class RevivalFacade extends AbstractFacade<Revival> implements RevivalFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    @TransactionAttribute(REQUIRED)
    public void createWithDelivery(Revival revival) {
        em.persist(revival.getDelivery());
        em.persist(revival);
    }
    public RevivalFacade() {
        super(Revival.class);
    }
    
}

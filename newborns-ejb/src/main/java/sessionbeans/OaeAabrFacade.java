/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Son;
import entities.tau.OaeAabr;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pingeso
 */
@Stateless
public class OaeAabrFacade extends AbstractFacade<OaeAabr> implements OaeAabrFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<OaeAabr> findBySon(Son son, int phase) {
        Query q = em.createQuery("SELECT oae FROM OaeAabr oae JOIN oae.exam.son s JOIN oae.exam e WHERE s.id = :sonId AND e.phase.id = :phaseId");
        q.setParameter("sonId", son.getId());
        q.setParameter("phaseId", phase);
        return q.getResultList();
    }

    public OaeAabrFacade() {
        super(OaeAabr.class);
    }
    
}

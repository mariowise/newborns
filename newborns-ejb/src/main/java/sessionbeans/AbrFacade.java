/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Son;
import entities.tau.Abr;
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
public class AbrFacade extends AbstractFacade<Abr> implements AbrFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Abr> findBySon(Son son) {
        Query q = em.createQuery("SELECT a FROM Abr a JOIN a.exam.son s JOIN a.exam e WHERE s.id = :sonId");
        q.setParameter("sonId", son.getId());
        return q.getResultList();
    }

    public AbrFacade() {
        super(Abr.class);
    }
    
}

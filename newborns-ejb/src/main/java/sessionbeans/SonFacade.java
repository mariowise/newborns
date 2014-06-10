/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Son;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pingeso
 */
@Stateless
public class SonFacade extends AbstractFacade<Son> implements SonFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;
    
//    @EJB
//    private SonIdFacade sonIdFacade;
//
//    @Override
//    public void create(Son son) {
//        System.out.println("SonFacade::create (overrride)");
//        getEntityManager().persist(son);
//        getEntityManager().flush();
//        getEntityManager().refresh(son);
//    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SonFacade() {
        super(Son.class);
    }
    
}

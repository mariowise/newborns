/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.YearsCount;
import entities.core.Son;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author pingeso
 */
@Stateless
public class SonFacade extends AbstractFacade<Son> implements SonFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;
    
    @EJB
    private YearsCountFacadeLocal yearsCountFacade;
    
    @Override
    @Transactional
    public void create(Son son) {
        System.out.println("SonFacade::create (overrride)");
        
        Date currentDate = new Date();
        int year = currentDate.getYear();
        int offset;
        
        YearsCount yc = yearsCountFacade.find(year);
        if(yc == null) {
            yc = new YearsCount();
            yc.setId(new Long(year));
            yc.setCount(0);
            yearsCountFacade.create(yc);
        }
        yc.setCount(yc.getCount()+1);
        yearsCountFacade.edit(yc);
        son.setYear(yc);
        son.setTicket(yc.getCount());

        getEntityManager().persist(son);
        getEntityManager().flush();
        getEntityManager().refresh(son);        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SonFacade() {
        super(Son.class);
    }
    
}

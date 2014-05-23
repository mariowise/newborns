/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Forecast;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pingeso
 */
@Stateless
public class ForecastFacade extends AbstractFacade<Forecast> implements ForecastFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ForecastFacade() {
        super(Forecast.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Person;
import entities.ServiceAttention;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pingeso
 */
@Stateless
public class ServiceAttentionFacade extends AbstractFacade<ServiceAttention> implements ServiceAttentionFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceAttentionFacade() {
        super(ServiceAttention.class);
    }
     
    public List<ServiceAttention> getServiceAttentionByPerson(Person person){
        List<ServiceAttention> serviceAttentions = new ArrayList();
        List<ServiceAttention> temp = this.findAll();
        if ((temp != null) && (person != null)) {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getAdmissionFile().getId()==person.getId()) {
                    serviceAttentions.add(temp.get(i));
                }
            }            
        }
        return serviceAttentions;
    }
}

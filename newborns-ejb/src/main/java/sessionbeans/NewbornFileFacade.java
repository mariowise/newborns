/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.NewbornFile;
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
public class NewbornFileFacade extends AbstractFacade<NewbornFile> implements NewbornFileFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewbornFileFacade() {
        super(NewbornFile.class);
    }
    
    public List<NewbornFile> findByKeyword(String key) {
        List<NewbornFile> firstNameList = findByFirstName(key);
        List<NewbornFile> firstLastNameList = findByFirstLastName(key);
        List<NewbornFile> secondLastNameList = findBySecondLastName(key);
        List<NewbornFile> rutList = findByRut(key);
        
        List<NewbornFile> resultList = null;
        
        if (firstNameList!=null) {
            for (int i = 0; i < firstNameList.size(); i++) {
                resultList.add(firstNameList.get(i));
            }            
        }
        if (firstLastNameList!=null) {
            for (int i = 0; i < firstLastNameList.size(); i++) {
                resultList.add(firstLastNameList.get(i));
            }            
        }
        if (secondLastNameList!=null) {
            for (int i = 0; i < secondLastNameList.size(); i++) {
                resultList.add(secondLastNameList.get(i));
            }            
        }
        if (rutList!=null) {
            for (int i = 0; i < rutList.size(); i++) {
                resultList.add(rutList.get(i));
            }            
        }
        
        return resultList;
    }
    
    public List<NewbornFile> findByFirstName(String key) {
        Query queryFirstName = em.createNamedQuery("NewbornFile.findByFirstName")
                                        .setParameter("firstName", key);
        return queryFirstName.getResultList();
    }
    
    public List<NewbornFile> findByFirstLastName(String key) {
        Query querySecondLastName = em.createNamedQuery("NewbornFile.findByFirstLastName")
                                        .setParameter("firstLastName", key);
        return querySecondLastName.getResultList();
    }
    
    public List<NewbornFile> findBySecondLastName(String key) {
        Query querySecondLastName = em.createNamedQuery("NewbornFile.findBySecondLastName")
                                        .setParameter("secondLastName", key);
        return querySecondLastName.getResultList();
    }
    
    public List<NewbornFile> findByRut(String key) {
        Query queryRut = em.createNamedQuery("NewbornFile.findByRut")
                                        .setParameter("rut", key);
        return queryRut.getResultList();
    }
    
}

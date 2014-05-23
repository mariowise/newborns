/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.File;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pingeso
 */
@Stateless
public class FileFacade extends AbstractFacade<File> implements FileFacadeLocal {
    @PersistenceContext(unitName = "cl.diinf.newborns-ejb.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FileFacade() {
        super(File.class);
    }
    
}

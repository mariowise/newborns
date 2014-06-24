/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Son;
import entities.tau.Abr;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface AbrFacadeLocal {

    void create(Abr abr);

    void edit(Abr abr);

    void remove(Abr abr);

    Abr find(Object id);

    List<Abr> findAll();

    List<Abr> findRange(int[] range);
    
    List<Abr> findBySon(Son son);

    int count();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Son;
import entities.tau.OaeAabr;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface OaeAabrFacadeLocal {

    void create(OaeAabr oaeAabr);

    void edit(OaeAabr oaeAabr);

    void remove(OaeAabr oaeAabr);

    OaeAabr find(Object id);

    List<OaeAabr> findAll();

    List<OaeAabr> findRange(int[] range);
    
    List<OaeAabr> findBySon(Son son, int phase);

    int count();
    
}

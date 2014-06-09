/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Party;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface PartyFacadeLocal {

    void create(Party party);

    void edit(Party party);

    void remove(Party party);

    Party find(Object id);

    List<Party> findAll();

    List<Party> findRange(int[] range);

    int count();
    
}

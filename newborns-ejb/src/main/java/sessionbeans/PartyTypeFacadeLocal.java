/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.PartyType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface PartyTypeFacadeLocal {

    void create(PartyType partyType);

    void edit(PartyType partyType);

    void remove(PartyType partyType);

    PartyType find(Object id);

    List<PartyType> findAll();

    List<PartyType> findRange(int[] range);

    int count();
    
}

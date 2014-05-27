/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Illness;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface IllnessFacadeLocal {

    void create(Illness illness);

    void edit(Illness illness);

    void remove(Illness illness);

    Illness find(Object id);

    List<Illness> findAll();

    List<Illness> findRange(int[] range);

    int count();
    
}

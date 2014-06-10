/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.SonId;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface SonIdFacadeLocal {

    void create(SonId sonId);

    void edit(SonId sonId);

    void remove(SonId sonId);

    SonId find(Object id);

    List<SonId> findAll();

    List<SonId> findRange(int[] range);

    int count();
    
}

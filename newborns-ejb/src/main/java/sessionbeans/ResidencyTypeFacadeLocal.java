/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.ResidencyType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface ResidencyTypeFacadeLocal {

    void create(ResidencyType residencyType);

    void edit(ResidencyType residencyType);

    void remove(ResidencyType residencyType);

    ResidencyType find(Object id);

    List<ResidencyType> findAll();

    List<ResidencyType> findRange(int[] range);

    int count();
    
}

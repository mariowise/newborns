/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.MotherFileAssociativeComponent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface MotherFileAssociativeComponentFacadeLocal {

    void create(MotherFileAssociativeComponent motherFileAssociativeComponent);

    void edit(MotherFileAssociativeComponent motherFileAssociativeComponent);

    void remove(MotherFileAssociativeComponent motherFileAssociativeComponent);

    MotherFileAssociativeComponent find(Object id);

    List<MotherFileAssociativeComponent> findAll();

    List<MotherFileAssociativeComponent> findRange(int[] range);

    int count();
    
}

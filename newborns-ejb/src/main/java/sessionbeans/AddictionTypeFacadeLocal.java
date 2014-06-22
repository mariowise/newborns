/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.AddictionType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface AddictionTypeFacadeLocal {

    void create(AddictionType addictionType);

    void edit(AddictionType addictionType);

    void remove(AddictionType addictionType);

    AddictionType find(Object id);

    List<AddictionType> findAll();

    List<AddictionType> findRange(int[] range);

    int count();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.MotherFile;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface MotherFileFacadeLocal {

    void create(MotherFile motherFile);

    void edit(MotherFile motherFile);

    void remove(MotherFile motherFile);

    MotherFile find(Object id);

    List<MotherFile> findAll();

    List<MotherFile> findRange(int[] range);

    int count();
    
}

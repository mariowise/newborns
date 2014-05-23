/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.FileMother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface FileMotherFacadeLocal {

    void create(FileMother fileMother);

    void edit(FileMother fileMother);

    void remove(FileMother fileMother);

    FileMother find(Object id);

    List<FileMother> findAll();

    List<FileMother> findRange(int[] range);

    int count();
    
}

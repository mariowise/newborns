/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.FileNewborn;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface FileNewbornFacadeLocal {

    void create(FileNewborn fileNewborn);

    void edit(FileNewborn fileNewborn);

    void remove(FileNewborn fileNewborn);

    FileNewborn find(Object id);

    List<FileNewborn> findAll();

    List<FileNewborn> findRange(int[] range);

    int count();
    
}

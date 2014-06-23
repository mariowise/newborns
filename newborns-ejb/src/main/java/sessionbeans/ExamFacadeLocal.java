/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.tau.Exam;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface ExamFacadeLocal {

    void create(Exam exam);

    void edit(Exam exam);

    void remove(Exam exam);

    Exam find(Object id);

    List<Exam> findAll();

    List<Exam> findRange(int[] range);

    int count();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.ExamPhase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface ExamPhaseFacadeLocal {

    void create(ExamPhase examPhase);

    void edit(ExamPhase examPhase);

    void remove(ExamPhase examPhase);

    ExamPhase find(Object id);

    List<ExamPhase> findAll();

    List<ExamPhase> findRange(int[] range);

    int count();
    
}

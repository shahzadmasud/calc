/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.jpa;

import org.springframework.data.repository.CrudRepository;
import sm.net.calc.model.Machine;

/**
 *
 * @author shahzadmasud
 */
public interface MachineRepository extends CrudRepository<Machine, Long>{
    
}

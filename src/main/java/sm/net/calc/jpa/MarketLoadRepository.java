/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import sm.net.calc.model.MarketLoad;

/**
 *
 * @author shahzadmasud
 */
public interface MarketLoadRepository extends JpaRepository<MarketLoad, Long>{
    
}

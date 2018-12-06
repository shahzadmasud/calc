/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import sm.net.calc.model.Region;

/**
 *
 * @author shahzadmasud
 */
public interface RegionRepository extends JpaRepository<Region, Long> {

}

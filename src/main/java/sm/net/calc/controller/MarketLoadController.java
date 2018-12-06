/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sm.net.calc.jpa.MachineRegionRepository;
import sm.net.calc.jpa.MachineRepository;
import sm.net.calc.jpa.MarketLoadRepository;
import sm.net.calc.jpa.MarketTypeRepository;
import sm.net.calc.jpa.RegionRepository;
import sm.net.calc.model.Machine;
import sm.net.calc.model.MachineRegion;
import sm.net.calc.model.MarketLoad;
import sm.net.calc.model.MarketType;
import sm.net.calc.model.Region;

/**
 *
 * @author shahzadmasud
 */
@RestController
public class MarketLoadController {

    @Autowired
    private MarketLoadRepository marketLoadRepository;

    @Autowired
    private MarketTypeRepository marketTypeRepository;

    @GetMapping("/market/load/all")
    @ApiOperation(value = "All MarketLoad", response = Iterable.class)
    public Iterable<MarketLoad> all() {
        return marketLoadRepository.findAll();
    }

    @PostMapping("/market/load/create")
    @ApiOperation(value = "This API would help in creating a new MarketLoad", response = MarketLoad.class)
    public MarketLoad create(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "markettype_id", required = true) Long marketTypeId,
            @RequestParam(value = "contracts", required = false) Long contracts,
            @RequestParam(value = "users", required = false) Long users) {
        
        Optional<MarketType> mt = marketTypeRepository.findById(marketTypeId);
        if (mt.isPresent() == false) {
            return new MarketLoad("Invalid MarketType id [" + marketTypeId + "]");
        }

        MarketLoad g = new MarketLoad(name);
        g.setMarketType(mt.get());
        g.setContracts(contracts);
        g.setUsers(users);

        return marketLoadRepository.save(g);
    }

    @PostMapping("/market/load/update")
    @ApiOperation(value = "This API would help in updating a MarketLoad", response = MarketLoad.class)
    public MarketLoad update(
            @RequestParam(value = "marketload_id", required = true) Long marketloadId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "markettype_id", required = true) Long marketTypeId,
            @RequestParam(value = "contracts", required = false) Long contracts,
            @RequestParam(value = "users", required = false) Long users) {

        if (marketloadId == null || marketloadId == 0) {
            return new MarketLoad("Provide a valid MarketLoad id");
        }

        boolean changed = false;

        Optional<MarketLoad> val = marketLoadRepository.findById(marketloadId);
        if (val.isPresent() == false) {
            return new MarketLoad("Provide a valid MarketLoad id");
        }

        MarketLoad g = val.get();
        if (name != null) {
            g.setName(name);
            changed = true;
        }

        if (marketTypeId != null) {
            Optional<MarketType> m = marketTypeRepository.findById(marketTypeId);
            if (m.isPresent() == false) {
                return new MarketLoad("Invalid Market Type id [" + marketTypeId + "]");
            } else {
                g.setMarketType(m.get());
                changed = true;
            }
        }

        if ( contracts != null ) {
            g.setContracts(contracts);
            changed = true;
        }
        
        if ( users != null ) {
            g.setUsers(users);
            changed = true;
        }
        
        if (changed) {
            marketLoadRepository.save(g);
        }

        return g;
    }

    @GetMapping("/market/load/remove")
    @ApiOperation(value = "This API would help in removing an existing MarketLoad", response = MarketLoad.class)
    public MarketLoad remove(
            @RequestParam(value = "machineload_id", required = true) String machineloadId) {
        if (null != machineloadId) {
            Optional<MarketLoad> c = marketLoadRepository.findById(Long.parseLong(machineloadId));
            if (c.isPresent()) {
                marketLoadRepository.deleteById(Long.parseLong(machineloadId));
                return c.get();
            }
        }
        return new MarketLoad(machineloadId + " doesn't exists ... ");
    }

    @GetMapping("/market/load/{marketloadId}")
    @ApiOperation(value = "Specific MarketLoad", response = Optional.class)
    public Optional<MarketLoad> get(@PathVariable Long marketloadId) {
        return marketLoadRepository.findById(marketloadId);
    }

}

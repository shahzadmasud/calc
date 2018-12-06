/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.controller;

import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sm.net.calc.jpa.MachineRegionRepository;
import sm.net.calc.jpa.MachineRepository;
import sm.net.calc.jpa.RegionRepository;
import sm.net.calc.model.Machine;
import sm.net.calc.model.MachineRegion;
import sm.net.calc.model.Region;

/**
 *
 * @author shahzadmasud
 */
@RestController
public class MachineRegionController {

    @Autowired
    private MachineRegionRepository machineRegionRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/machine/region/all")
    @ApiOperation(value = "All MachineRegion", response = Iterable.class)
    public Iterable<MachineRegion> all() {
        return machineRegionRepository.findAll();
    }

    @PostMapping("/machine/region/create")
    @ApiOperation(value = "This API would help in creating a new MachineRegion", response = MachineRegion.class)
    public MachineRegion create(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "machine_id", required = true) Long machineId,
            @RequestParam(value = "region_id", required = false) Long regionId,
            @RequestParam(value = "price", required = false) Double price) {

        Optional<Machine> m = machineRepository.findById(machineId);
        if (m.isPresent() == false) {
            return new MachineRegion("Invalid machine id [" + machineId + "]");
        }

        Optional<Region> r = regionRepository.findById(regionId);
        if (r.isPresent() == false) {
            return new MachineRegion("Invalid Region Id [" + regionId + "]");
        }

        MachineRegion g = new MachineRegion(name);
        g.setRegion(r.get());
        g.setMachine(m.get());
        g.setPrice(price);

        return machineRegionRepository.save(g);
    }

    @PostMapping("/machine/region/update")
    @ApiOperation(value = "This API would help in updating a MachineRegion", response = MachineRegion.class)
    public MachineRegion update(
            @RequestParam(value = "machineregion_id", required = true) Long machineregionId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "machine_id", required = true) Long machineId,
            @RequestParam(value = "region_id", required = false) Long regionId,
            @RequestParam(value = "price", required = false) Double price) {

        if (machineregionId == null || machineregionId == 0) {
            return new MachineRegion("Provide a valid Region id");
        }

        boolean changed = false;

        Optional<MachineRegion> val = machineRegionRepository.findById(machineregionId);
        if (val.isPresent() == false) {
            return new MachineRegion("Provide a valid Machine Region id");
        }

        MachineRegion g = val.get();
        if (name != null) {
            g.setName(name);
            changed = true;
        }

        if (machineId != null) {
            Optional<Machine> m = machineRepository.findById(machineId);
            if (m.isPresent() == false) {
                return new MachineRegion("Invalid machine id [" + machineId + "]");
            } else {
                g.setMachine(m.get());
                changed = true;
            }
        }

        if (regionId != null) {
            Optional<Region> r = regionRepository.findById(regionId);
            if (r.isPresent() == false) {
                return new MachineRegion("Invalid Region Id [" + regionId + "]");
            }
            else {
                g.setRegion(r.get());
                changed = true;
            }
        }

        if ( price != null ) {
            g.setPrice(price);
            changed = true;
        }
        
        if (changed) {
            machineRegionRepository.save(g);
        }

        return g;
    }

    @GetMapping("/machine/region/remove")
    @ApiOperation(value = "This API would help in removing an existing MachineRegion", response = MachineRegion.class)
    public MachineRegion remove(
            @RequestParam(value = "machineregion_id", required = true) String machineRegionId) {
        if (null != machineRegionId) {
            Optional<MachineRegion> c = machineRegionRepository.findById(Long.parseLong(machineRegionId));
            if (c.isPresent()) {
                machineRegionRepository.deleteById(Long.parseLong(machineRegionId));
                return c.get();
            }
        }
        return new MachineRegion(machineRegionId + " doesn't exists ... ");
    }

    @GetMapping("/machine/region/{machineregionId}")
    @ApiOperation(value = "Specific MachineRegion", response = Iterable.class)
    public Optional<MachineRegion> get(@PathVariable Long machineregionId) {
        return machineRegionRepository.findById(machineregionId);
    }

}

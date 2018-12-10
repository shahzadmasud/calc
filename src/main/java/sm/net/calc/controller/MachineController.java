/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.controller;

import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sm.net.calc.jpa.MachineRepository;
import sm.net.calc.model.Machine;

/**
 *
 * @author shahzadmasud
 */
@RestController
public class MachineController {

    @Autowired
    private MachineRepository machineRepository;

    @GetMapping("/machine/all")
    @ApiOperation(value = "All Machines", response = Iterable.class)
    public Iterable<Machine> allRootCategories() {
        return machineRepository.findAll();
    }

    @PostMapping("/machine/create")
    @ApiOperation(value = "This API would help in creating a new Machine", response = Machine.class)
    @Transactional(rollbackFor = RollbackException.class)
    public Machine create(
            @RequestParam(value = "name", required = true, defaultValue = "OK") String name,
            @RequestParam(value = "include_sql_ent", required = false) Boolean include,
            @RequestParam(value = "no_vcpus", required = false) Integer vCPUs,
            @RequestParam(value = "ram", required = false) Integer ram,
            @RequestParam(value = "temp_storage", required = false) Integer tempStorage,
            @RequestParam(value = "harddisk", required = false) Integer harddisk) {
        Machine g = new Machine(name);
        g.setIncludeSQLEnt(include);
        g.setNoOfVCPUs(vCPUs);
        g.setRam(ram);
        g.setTempStorage(tempStorage);
        g.setHardDiskSSD(harddisk);

            return machineRepository.save(g);

    }

    @PostMapping("/machine/update")
    @ApiOperation(value = "This API would help in updating a Machine", response = Machine.class)
    public Machine updateCategory(
            @RequestParam(value = "machine_id", required = true) Long machineId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "include_sql_ent", required = false) Boolean include,
            @RequestParam(value = "no_vcpus", required = false) Integer vCPUs,
            @RequestParam(value = "ram", required = false) Integer ram,
            @RequestParam(value = "temp_storage", required = false) Integer tempStorage,
            @RequestParam(value = "harddisk", required = false) Integer harddisk) {

        if (machineId == null || machineId == 0) {
            return new Machine("Provide a valid machine id");
        }

        boolean changed = false;

        Optional<Machine> val = machineRepository.findById(machineId);
        if (val.isPresent() == false) {
            return new Machine("Provide a valid machine id");
        }
        Machine g = val.get();
        if (name != null) {
            g.setName(name);
            changed = true;
        }

        if (include != null) {
            g.setIncludeSQLEnt(include);
            changed = true;
        }

        if (vCPUs != null) {
            g.setNoOfVCPUs(vCPUs);
            changed = true;
        }

        if (ram != null) {
            g.setRam(ram);
            changed = true;
        }

        if (tempStorage != null) {
            g.setTempStorage(tempStorage);
            changed = true;
        }

        if (harddisk != null) {
            g.setHardDiskSSD(harddisk);
        }

        if (changed) {
            machineRepository.save(g);
        }

        return g;
    }

    @GetMapping("/machine/remove")
    @ApiOperation(value = "This API would help in removing an existing machine", response = Machine.class)
    public Machine removeCategory(
            @RequestParam(value = "machine_id", required = true) String machineId) {
        if (null != machineId) {
            Optional<Machine> c = machineRepository.findById(Long.parseLong(machineId));
            if (c.isPresent()) {
                machineRepository.deleteById(Long.parseLong(machineId));
                return c.get();
            }
        }
        return new Machine(machineId + " doesn't exists ... ");
    }

    @GetMapping("/machine/{machineId}")
    @ApiOperation(value = "Specific Machine", response = Iterable.class)
    public Optional<Machine> machine(@PathVariable Long machineId) {
        return machineRepository.findById(machineId);
    }

}

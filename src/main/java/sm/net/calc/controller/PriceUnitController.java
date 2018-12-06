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
import sm.net.calc.jpa.PriceUnitRepository;
import sm.net.calc.jpa.RegionRepository;
import sm.net.calc.model.Machine;
import sm.net.calc.model.PriceUnit;
import sm.net.calc.model.Region;

/**
 *
 * @author shahzadmasud
 */
@RestController
public class PriceUnitController {


    @Autowired
    private PriceUnitRepository priceunitRepository;

    @GetMapping("/priceunit/all")
    @ApiOperation(value = "All PriceUnit", response = Iterable.class)
    public Iterable<PriceUnit> all() {
        return priceunitRepository.findAll();
    }

    @PostMapping("/priceunit/create")
    @ApiOperation(value = "This API would help in creating a new PriceUnit", response = PriceUnit.class)
    public PriceUnit create(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String desc) {
        if (name.trim().length() == 0) {
            return new PriceUnit("Provide Region name ... ");
        }
        PriceUnit g = new PriceUnit(name);
        g.setDesc(desc);

        return priceunitRepository.save(g);
    }

    @PostMapping("/priceunit/update")
    @ApiOperation(value = "This API would help in updating a PriceUnit", response = PriceUnit.class)
    public PriceUnit update(
            @RequestParam(value = "priceunit_id", required = true) Long priceUnitId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String desc) {

        if (priceUnitId == null || priceUnitId == 0) {
            return new PriceUnit("Provide a valid Region id");
        }

        boolean changed = false;

        Optional<PriceUnit> val = priceunitRepository.findById(priceUnitId);
        if (val.isPresent() == false) {
            return new PriceUnit("Provide a valid Region id");
        }
        PriceUnit g = val.get();
        if (name != null) {
            g.setName(name);
            changed = true;
        }

        if (desc != null) {
            g.setDesc(desc);
            changed = true;
        }
        if (changed) {
            priceunitRepository.save(g);
        }

        return g;
    }

    @GetMapping("/priceunit/remove")
    @ApiOperation(value = "This API would help in removing an existing PriceUnit", response = PriceUnit.class)
    public PriceUnit remove(
            @RequestParam(value = "priceunit_id", required = true) String priceUnitId) {
        if (null != priceUnitId) {
            Optional<PriceUnit> c = priceunitRepository.findById(Long.parseLong(priceUnitId));
            if (c.isPresent()) {
                priceunitRepository.deleteById(Long.parseLong(priceUnitId));
                return c.get() ;
            }
        }
        return new PriceUnit(priceUnitId + " doesn't exists ... ");
    }

    @GetMapping("/priceunit/{priceunitId}")
    @ApiOperation(value = "Specific PriceUnit", response = Iterable.class)
    public Optional<PriceUnit> get(@PathVariable Long priceunitId) {
        return priceunitRepository.findById(priceunitId);
    }

}

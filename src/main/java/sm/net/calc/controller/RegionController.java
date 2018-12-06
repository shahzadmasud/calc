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
import sm.net.calc.jpa.RegionRepository;
import sm.net.calc.model.Machine;
import sm.net.calc.model.Region;

/**
 *
 * @author shahzadmasud
 */
@RestController
public class RegionController {


    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/region/all")
    @ApiOperation(value = "All Region", response = Iterable.class)
    public Iterable<Region> allRootCategories() {
        return regionRepository.findAll();
    }

    @PostMapping("/region/create")
    @ApiOperation(value = "This API would help in creating a new Region", response = Region.class)
    public Region create(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String desc) {
        if (name.trim().length() == 0) {
            return new Region("Provide Region name ... ");
        }
        Region g = new Region(name);
        g.setDesc(desc);

        return regionRepository.save(g);
    }

    @PostMapping("/region/update")
    @ApiOperation(value = "This API would help in updating a Region", response = Region.class)
    public Region updateCategory(
            @RequestParam(value = "region_id", required = true) Long regionId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String desc) {

        if (regionId == null || regionId == 0) {
            return new Region("Provide a valid Region id");
        }

        boolean changed = false;

        Optional<Region> val = regionRepository.findById(regionId);
        if (val.isPresent() == false) {
            return new Region("Provide a valid Region id");
        }
        Region g = val.get();
        if (name != null) {
            g.setName(name);
            changed = true;
        }

        if (desc != null) {
            g.setDesc(desc);
            changed = true;
        }
        if (changed) {
            regionRepository.save(g);
        }

        return g;
    }

    @GetMapping("/region/remove")
    @ApiOperation(value = "This API would help in removing an existing machine", response = Machine.class)
    public Region removeCategory(
            @RequestParam(value = "region_id", required = true) String regionId) {
        if (null != regionId) {
            Optional<Region> c = regionRepository.findById(Long.parseLong(regionId));
            if (c.isPresent()) {
                regionRepository.deleteById(Long.parseLong(regionId));
                return c.get() ;
            }
        }
        return new Region(regionId + " doesn't exists ... ");
    }

    @GetMapping("/region/{regionId}")
    @ApiOperation(value = "Specific Machine", response = Iterable.class)
    public Optional<Region> machine(@PathVariable Long regionId) {
        return regionRepository.findById(regionId);
    }

}

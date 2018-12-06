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
import sm.net.calc.jpa.MarketTypeRepository;
import sm.net.calc.model.MarketType;
import sm.net.calc.model.PriceUnit;

/**
 *
 * @author shahzadmasud
 */
@RestController
public class MarketTypeController {


    @Autowired
    private MarketTypeRepository marketTypeRepository;

    @GetMapping("/market/type/all")
    @ApiOperation(value = "All MarketType", response = Iterable.class)
    public Iterable<MarketType> all() {
        return marketTypeRepository.findAll();
    }

    @PostMapping("/market/type/create")
    @ApiOperation(value = "This API would help in creating a new MarketType", response = MarketType.class)
    public MarketType create(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String desc) {
        if (name.trim().length() == 0) {
            return new MarketType("Provide MarketType name ... ");
        }
        MarketType g = new MarketType(name);
        g.setDesc(desc);

        return marketTypeRepository.save(g);
    }

    @PostMapping("/market/type/update")
    @ApiOperation(value = "This API would help in updating a MarketType", response = MarketType.class)
    public MarketType update(
            @RequestParam(value = "markettype_id", required = true) Long marketTypeId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String desc) {

        if (marketTypeId == null || marketTypeId == 0) {
            return new MarketType("Provide a valid Region id");
        }

        boolean changed = false;

        Optional<MarketType> val = marketTypeRepository.findById(marketTypeId);
        if (val.isPresent() == false) {
            return new MarketType("Provide a valid Region id");
        }
        MarketType g = val.get();
        if (name != null) {
            g.setName(name);
            changed = true;
        }

        if (desc != null) {
            g.setDesc(desc);
            changed = true;
        }
        if (changed) {
            marketTypeRepository.save(g);
        }

        return g;
    }

    @GetMapping("/market/type/remove")
    @ApiOperation(value = "This API would help in removing an existing MarketType", response = PriceUnit.class)
    public MarketType remove(
            @RequestParam(value = "markettype_id", required = true) String markettypeId) {
        if (null != markettypeId) {
            Optional<MarketType> c = marketTypeRepository.findById(Long.parseLong(markettypeId));
            if (c.isPresent()) {
                marketTypeRepository.deleteById(Long.parseLong(markettypeId));
                return c.get() ;
            }
        }
        return new MarketType(markettypeId + " doesn't exists ... ");
    }

    @GetMapping("/market/type/{markettypeId}")
    @ApiOperation(value = "Specific MarketType", response = Iterable.class)
    public Optional<MarketType> get(@PathVariable Long markettypeId) {
        return marketTypeRepository.findById(markettypeId);
    }

}

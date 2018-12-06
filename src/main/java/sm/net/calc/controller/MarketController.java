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
import sm.net.calc.jpa.MachineRepository;
import sm.net.calc.jpa.MarketRepository;
import sm.net.calc.jpa.MarketTypeRepository;
import sm.net.calc.model.Machine;
import sm.net.calc.model.Market;
import sm.net.calc.model.MarketType;
import sm.net.calc.model.PriceUnit;

/**
 *
 * @author shahzadmasud
 */
@RestController
public class MarketController {

    @Autowired
    private MarketTypeRepository marketTypeRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MarketRepository marketRepository;

    @GetMapping("/market/all")
    @ApiOperation(value = "All Market", response = Iterable.class)
    public Iterable<Market> all() {
        return marketRepository.findAll();
    }

    @PostMapping("/market/create")
    @ApiOperation(value = "This API would help in creating a new Market", response = Market.class)
    public Market create(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "primary_markettype_id", required = false) Long primaryMarketTypeId,
            @RequestParam(value = "secondary_markettype_id", required = false) Long secondaryMarketTypeId,
            @RequestParam(value = "component_machine_id", required = false) Long componentMachineId,
            @RequestParam(value = "component_machine_count", required = false) Long componentMachineCount,
            @RequestParam(value = "appserver_machine_id", required = false) Long appServerMachineId,
            @RequestParam(value = "appserver_machine_count", required = false) Long appServerMachineCount,
            @RequestParam(value = "webserver_machine_id", required = false) Long webServerMachineId,
            @RequestParam(value = "webserver_machine_count", required = false) Long webServerMachineCount,
            @RequestParam(value = "dbserver_machine_id", required = false) Long dbServerMachineId,
            @RequestParam(value = "dbserver_machine_count", required = false) Long dbServerMachineCount
    ) {

        if (name.trim().length() == 0) {
            return new Market("Provide Market name ... ");
        }

        Optional<MarketType> primary = marketTypeRepository.findById(primaryMarketTypeId);
        if (primary.isPresent() == false) {
            return new Market("Invalid Primary Market Type id [ " + primaryMarketTypeId + "]");
        }

        Optional<MarketType> secondary = marketTypeRepository.findById(secondaryMarketTypeId);
        if (secondary.isPresent() == false) {
            return new Market("Invalid Secondary Market Type id [ " + secondaryMarketTypeId + "]");
        }

        Optional<Machine> component = machineRepository.findById(componentMachineId);
        if (component.isPresent() == false) {
            return new Market("Invalid Component Machine id [ " + componentMachineId + "]");
        }

        Optional<Machine> appServer = machineRepository.findById(appServerMachineId);
        if (appServer.isPresent() == false) {
            return new Market("Invalid AppServer Machine id [ " + appServerMachineId + "]");
        }

        Optional<Machine> webServer = machineRepository.findById(webServerMachineId);
        if (webServer.isPresent() == false) {
            return new Market("Invalid WebServer Machine id [ " + webServerMachineId + "]");
        }

        Optional<Machine> dbServer = machineRepository.findById(dbServerMachineId);
        if (dbServer.isPresent() == false) {
            return new Market("Invalid dbServer Machine id [ " + dbServerMachineId + "]");
        }

        Market g = new Market(name);
        g.setPrimary(primary.get());
        g.setSecondary(secondary.get());
        g.setComponent(component.get());
        g.setCountComponnt(componentMachineCount);
        g.setAppServer(appServer.get());
        g.setCountAppServer(appServerMachineCount);
        g.setWebServer(webServer.get());
        g.setCountWebServer(webServerMachineCount);
        g.setDbServer(dbServer.get());
        g.setCountDbServer(componentMachineCount);

        return marketRepository.save(g);
    }

    @PostMapping("/market/update")
    @ApiOperation(value = "This API would help in updating a Market", response = Market.class)
    public Market update(
            @RequestParam(value = "market_id", required = true) Long marketTypeId,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "primary_markettype_id", required = false) Long primaryMarketTypeId,
            @RequestParam(value = "secondary_markettype_id", required = false) Long secondaryMarketTypeId,
            @RequestParam(value = "component_machine_id", required = false) Long componentMachineId,
            @RequestParam(value = "component_machine_count", required = false) Long componentMachineCount,
            @RequestParam(value = "appserver_machine_id", required = false) Long appServerMachineId,
            @RequestParam(value = "appserver_machine_count", required = false) Long appServerMachineCount,
            @RequestParam(value = "webserver_machine_id", required = false) Long webServerMachineId,
            @RequestParam(value = "webserver_machine_count", required = false) Long webServerMachineCount,
            @RequestParam(value = "dbserver_machine_id", required = false) Long dbServerMachineId,
            @RequestParam(value = "dbserver_machine_count", required = false) Long dbServerMachineCount) {

        if (marketTypeId == null || marketTypeId == 0) {
            return new Market("Provide a valid Region id");
        }

        boolean changed = false;

        Optional<Market> val = marketRepository.findById(marketTypeId);
        if (val.isPresent() == false) {
            return new Market("Provide a valid Region id");
        }
        Market g = val.get();
        if (name != null) {
            g.setName(name);
            changed = true;
        }

        if (primaryMarketTypeId != null) {
            Optional<MarketType> primary = marketTypeRepository.findById(primaryMarketTypeId);
            if (primary.isPresent() == false) {
                return new Market("Invalid Primary Market Type id [ " + primaryMarketTypeId + "]");
            } else {
                g.setPrimary(primary.get());
                changed = true;
            }
        }

        if (secondaryMarketTypeId != null) {
            Optional<MarketType> secondary = marketTypeRepository.findById(secondaryMarketTypeId);
            if (secondary.isPresent() == false) {
                return new Market("Invalid Secondary Market Type id [ " + secondaryMarketTypeId + "]");
            } else {
                g.setSecondary(secondary.get());
                changed = true;
            }
        }

        if (componentMachineId != null) {
            Optional<Machine> component = machineRepository.findById(componentMachineId);
            if (component.isPresent() == false) {
                return new Market("Invalid Component Machine id [ " + componentMachineId + "]");
            } else {
                g.setComponent(component.get());
                changed = true;
            }
        }

        if (appServerMachineId != null) {
            Optional<Machine> appServer = machineRepository.findById(appServerMachineId);
            if (appServer.isPresent() == false) {
                return new Market("Invalid AppServer Machine id [ " + appServerMachineId + "]");
            } else {
                g.setAppServer(appServer.get());
                changed = true;
            }
        }

        if (webServerMachineId != null) {
            Optional<Machine> webServer = machineRepository.findById(webServerMachineId);
            if (webServer.isPresent() == false) {
                return new Market("Invalid WebServer Machine id [ " + webServerMachineId + "]");
            } else {
                g.setWebServer(webServer.get());
                changed = true;
            }
        }

        if (dbServerMachineId != null) {
            Optional<Machine> dbServer = machineRepository.findById(dbServerMachineId);
            if (dbServer.isPresent() == false) {
                return new Market("Invalid dbServer Machine id [ " + dbServerMachineId + "]");
            } else {
                g.setDbServer(dbServer.get());
                changed = true;
            }
        }

        if (componentMachineCount != null) {
            g.setCountComponnt(componentMachineCount);
            changed = true;
        }

        if (appServerMachineCount != null) {
            g.setCountAppServer(appServerMachineCount);
            changed = true;
        }

        if (webServerMachineCount != null) {
            g.setCountWebServer(webServerMachineCount);
            changed = true;
        }

        if (dbServerMachineCount != null) {
            g.setCountDbServer(dbServerMachineCount);
            changed = true;
        }

        if (changed) {
            marketRepository.save(g);
        }

        return g;
    }

    @GetMapping("/market/remove")
    @ApiOperation(value = "This API would help in removing an existing Market", response = Market.class)
    public Market remove(
            @RequestParam(value = "market_id", required = true) String marketId
    ) {
        if (null != marketId) {
            Optional<Market> c = marketRepository.findById(Long.parseLong(marketId));
            if (c.isPresent()) {
                marketTypeRepository.deleteById(Long.parseLong(marketId));
                return c.get();
            }
        }
        return new Market(marketId + " doesn't exists ... ");
    }

    @GetMapping("/market/{marketId}")
    @ApiOperation(value = "Specific Market", response = Iterable.class)
    public Optional<MarketType> get(@PathVariable Long marketId
    ) {
        return marketTypeRepository.findById(marketId);
    }

}

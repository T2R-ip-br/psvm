package com.sukhoev.psms.rack.controller;

import com.sukhoev.psms.hardware.entity.Hardware;
import com.sukhoev.psms.hardware.entity.TypeCurrent;
import com.sukhoev.psms.hardware.entity.TypeHardware;
import com.sukhoev.psms.hardware.service.HardwareService;
import com.sukhoev.psms.rack.entity.ConnectingHardware;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.service.ConnectingHardwareService;
import com.sukhoev.psms.rack.service.RackConfigurationService;
import com.sukhoev.psms.rack.service.RackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/rack-configuration")
@AllArgsConstructor
public class RackConfigurationController {

    private final RackConfigurationService rackConfigurationService;
    private final RackService rackService;
    private final ConnectingHardwareService connectingHardwareService;
    private final HardwareService hardwareService;

    @RequestMapping("/add/{rackId}")
    public String addRackConfiguration(
            @PathVariable("rackId") Long rackId,
            Model model
    ) {

        List<Rack> racks = rackService.findAll();
        List<ConnectingHardware> connectingHardware = connectingHardwareService.findAllByRackId(rackId);
        List<Hardware> hardware = hardwareService.findAll();
        model.addAttribute("rackConfiguration", new RackConfiguration());
        model.addAttribute("racks", racks);
        model.addAttribute("connectingHardware", connectingHardware);
        model.addAttribute("hardware", hardware);
        model.addAttribute("rackId", rackService.findById(rackId));
        return "add-rack-configuration";
    }

    @PostMapping("/add/{rackId}")
    public ModelAndView addRackConfiguration(
            @PathVariable("rackId") int rackId,
            @ModelAttribute RackConfiguration rackConfiguration) {
        rackConfigurationService.save(rackConfiguration);
        return new ModelAndView("redirect:/api/v1/rack/" + rackId);
    }

    @GetMapping("/delete/{rackConfigurationId}")
    public String deleteRackConfiguration(
            @PathVariable("rackConfigurationId") Long rackConfigurationId,
            Model model
    ) {
        RackConfiguration rackConfiguration = rackConfigurationService.findById(rackConfigurationId);
        model.addAttribute("rackConfiguration", rackConfiguration);
        return "delete-rack-configuration";
    }

    @PostMapping("/delete/{rackConfigurationId}")
    public ModelAndView deleteRackConfiguration(
            @PathVariable("rackConfigurationId") Long rackConfigurationId
    ) {
        Long rackId = rackConfigurationService.findById(rackConfigurationId).getRack().getId();
        rackConfigurationService.deleteRackConfiguration(rackConfigurationId);
        return new ModelAndView("redirect:/api/v1/rack/" + rackId);
    }
}

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<ConnectingHardware> connectingHardware = connectingHardwareService.findAll();
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
}

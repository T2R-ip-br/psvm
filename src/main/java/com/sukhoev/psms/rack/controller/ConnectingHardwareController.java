package com.sukhoev.psms.rack.controller;

import com.sukhoev.psms.hardware.entity.Hardware;
import com.sukhoev.psms.hardware.service.HardwareService;
import com.sukhoev.psms.rack.entity.ConnectingHardware;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.service.ConnectingHardwareService;
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
@RequestMapping(path = "/api/v1/connecting-hardware")
@AllArgsConstructor
public class ConnectingHardwareController {

    private final ConnectingHardwareService connectingHardwareService;
    private final HardwareService hardwareService;
    private final RackService rackService;

    @RequestMapping("/add/{rackId}")
    public String addConnectingHardware(
            @PathVariable("rackId") Long rackId,
            Model model
    ) {
        List<Hardware> hardware = hardwareService.findAll();
        List<Rack> racks = rackService.findAll();
        model.addAttribute("connectingHardware", new ConnectingHardware());
        model.addAttribute("hardware", hardware);
        model.addAttribute("racks", racks);
        model.addAttribute("rackId", rackService.findById(rackId));
        return "add-connecting-hardware";
    }

    @PostMapping("/add/{rackId}")
    public ModelAndView addConnectingHardware(
            @PathVariable("rackId") int rackId,
            @ModelAttribute ConnectingHardware connectingHardware) {
        connectingHardwareService.save(connectingHardware);
        return new ModelAndView("redirect:/api/v1/rack/" + rackId);
    }
}

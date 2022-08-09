package com.sukhoev.psms.hardware.controller;

import com.sukhoev.psms.hardware.entity.Hardware;
import com.sukhoev.psms.hardware.entity.TypeCurrent;
import com.sukhoev.psms.hardware.entity.TypeHardware;
import com.sukhoev.psms.hardware.service.HardwareService;
import com.sukhoev.psms.hardware.service.TypeCurrentService;
import com.sukhoev.psms.hardware.service.TypeHardwareService;
import com.sukhoev.psms.rack.entity.Rack;
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
@RequestMapping(path = "/api/v1/hardware")
@AllArgsConstructor
public class HardwareController {

    private final HardwareService hardwareService;
    private final TypeCurrentService typeCurrentService;
    private final TypeHardwareService typeHardwareService;

    @RequestMapping("/add/{rackId}")
    public String addRack(
            @PathVariable("rackId") int rackId,
            Model model
    ) {
        List<TypeCurrent> typeCurrents = typeCurrentService.findAll();
        List<TypeHardware> typeHardware = typeHardwareService.findAll();
        model.addAttribute("hardware", new Hardware());
        model.addAttribute("typeCurrents", typeCurrents);
        model.addAttribute("typeHardware", typeHardware);
        model.addAttribute("rackId", rackId);
        return "add-hardware";
    }

    @PostMapping("/add/{rackId}")
    public ModelAndView addRack(
            @PathVariable("rackId") int rackId,
            @ModelAttribute Hardware hardware) {
        hardwareService.save(hardware);
        return new ModelAndView("redirect:/api/v1/rack/" + rackId);
    }
}

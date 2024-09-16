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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/hardware")
@AllArgsConstructor
public class HardwareController {

    private final HardwareService hardwareService;
    private final TypeCurrentService typeCurrentService;
    private final TypeHardwareService typeHardwareService;

    // Страница отображения всего списка оборудования
    @GetMapping
    public String hardware(Model model) {
        List<Hardware> hardware = hardwareService.findAll();
        model.addAttribute("hardware", hardware);
        return "hardware";
    }

    // Страница отображения характеристик оборудования
    @GetMapping("{hardwareId}")
    public String getHardware(
            @PathVariable("hardwareId") Long hardwareId,
            Model model
    ) {
        Hardware hardware = hardwareService.findById(hardwareId);
        System.out.println(hardware.toString());
        model.addAttribute("hardware", hardware);
        return "hardware-info";
    }

    // Добавление оборудование с переходм из стойки
    @RequestMapping("/add/{rackId}")
    public String addHardware(
            @PathVariable("rackId") int rackId,
            Model model
    ) {
        List<TypeCurrent> typeCurrents = typeCurrentService.findAll();
        List<TypeHardware> typeHardware = typeHardwareService.findAll();
        model.addAttribute("hardware", new Hardware());
        model.addAttribute("typeCurrents", typeCurrents);
        model.addAttribute("typeHardware", typeHardware);
        model.addAttribute("rackId", rackId);
        return "add-hardware-from-rack";
    }

    @PostMapping("/add/{rackId}")
    public ModelAndView addHardware(
            @PathVariable("rackId") int rackId,
            @ModelAttribute Hardware hardware) {
        hardwareService.save(hardware);
        return new ModelAndView("redirect:/api/v1/rack/" + rackId);
    }

    // Добавление оборудования с переходом со страницы со списком оборудования
    @RequestMapping("/add")
    public String addHardware(
            Model model
    ) {
        List<TypeCurrent> typeCurrents = typeCurrentService.findAll();
        List<TypeHardware> typeHardware = typeHardwareService.findAll();
        model.addAttribute("hardware", new Hardware());
        model.addAttribute("typeCurrents", typeCurrents);
        model.addAttribute("typeHardware", typeHardware);
        return "add-hardware-from-hardware";
    }

    @PostMapping("/add")
    public ModelAndView addHardware(@ModelAttribute Hardware hardware) {
        hardwareService.save(hardware);
        return new ModelAndView("redirect:/api/v1/hardware/");
    }

    // Страница обновления характеристик оборудования
    @RequestMapping("/update/{hardwareId}")
    public String updateHardware(
            @PathVariable("hardwareId") Long hardwareId,
            Model model
    ) {
        List<TypeCurrent> typeCurrents = typeCurrentService.findAll();
        List<TypeHardware> typeHardware = typeHardwareService.findAll();
        model.addAttribute("hardware", hardwareService.findById(hardwareId));
        model.addAttribute("typeCurrents", typeCurrents);
        model.addAttribute("typeHardware", typeHardware);
        return "update-hardware-from-hardware";
    }

    @PostMapping("/update/{hardwareId}")
    public ModelAndView updateHardware(
            @PathVariable("hardwareId") Long hardwareId,
            @ModelAttribute Hardware hardware) {
        hardware.setId(hardwareId);
        hardwareService.update(hardware);
        return new ModelAndView("redirect:/api/v1/hardware/");
    }

    // Страница удаления оборудования
    @GetMapping("/delete/{hardwareId}")
    public String deleteHardware(
            @PathVariable("hardwareId") Long hardwareId,
            Model model
    ) {
        Hardware hardware = hardwareService.findById(hardwareId);
        model.addAttribute("hardware", hardware);
        return "delete-hardware";
    }

    @PostMapping("/delete/{hardwareId}")
    public ModelAndView deleteHardware(
            @PathVariable("hardwareId") Long hardwareId
    ) {
        hardwareService.deleteHardware(hardwareId);
        return new ModelAndView("redirect:/api/v1/hardware/");
    }
}

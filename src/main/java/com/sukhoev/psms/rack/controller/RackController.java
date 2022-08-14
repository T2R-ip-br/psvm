package com.sukhoev.psms.rack.controller;

import com.sukhoev.psms.premises.entity.Premises;
import com.sukhoev.psms.premises.service.PremisesService;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.entity.RackModel;
import com.sukhoev.psms.rack.service.RackConfigurationService;
import com.sukhoev.psms.rack.service.RackModelService;
import com.sukhoev.psms.rack.service.RackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/rack")
@AllArgsConstructor
public class RackController {

    private final RackService rackService;
    private final PremisesService premisesService;
    private final RackModelService rackModelService;
    private final RackConfigurationService rackConfigurationService;

    @GetMapping("/{rackId}")
    public String premises(
            @PathVariable("rackId") Long rackId,
            Model model
    ) {
        Rack rack = rackService.findById(rackId);
        Premises premises = rack.getPremises();
        RackModel rackModel = rack.getRackModel();
        List<RackConfiguration> rackConfigurations = rackConfigurationService.findAllByRackId(rackId, rackModel.getUnitHeight());
        model.addAttribute("rack", rack);
        model.addAttribute("premises", premises);
        model.addAttribute("rackModel", rackModel);
        model.addAttribute("rackConfigurations", rackConfigurations);

        return "rack";
    }

    @RequestMapping("/add/{premisesId}")
    public String addRack(
            @PathVariable("premisesId") Long premisesId,
            Model model
    ) {
        List<RackModel> rackModels = rackModelService.findAll();
        List<Premises> premises = premisesService.findAll();
        model.addAttribute("rack", new Rack());
        model.addAttribute("premises", premisesService.findById(premisesId));
        model.addAttribute("rackModels", rackModels);
        model.addAttribute("allPremises", premises);
        return "add-rack";
    }

    @PostMapping("/add/{premisesId}")
    public ModelAndView addRack(
            @PathVariable("premisesId") int premisesId,
            @ModelAttribute Rack rack) {
        rackService.addRack(rack);
        return new ModelAndView("redirect:/api/v1/premises/" + premisesId);
    }

    @GetMapping("/delete/{rackId}")
    public String deleteRack(
            @PathVariable("rackId") Long rackId,
            Model model
    ) {
        Rack rack = rackService.findById(rackId);
        model.addAttribute("rack", rack);
        return "delete-rack";
    }

    @PostMapping("/delete/{rackId}")
    public ModelAndView deleteRack(
            @PathVariable("rackId") Long rackId
    ) {
        Long premisesId = rackService.findById(rackId).getId();
        rackService.deleteRack(rackId);
        return new ModelAndView("redirect:/api/v1/premises/" + premisesId);
    }
}

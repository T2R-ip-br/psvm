package com.sukhoev.psms.rack.controller;

import com.sukhoev.psms.premises.entity.Premises;
import com.sukhoev.psms.premises.service.PremisesService;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.entity.RackModel;
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

/*    @RequestMapping
    public String choosingPremises(Model model) {
        List<Premises> premises = premisesService.findAll();
        model.addAttribute("premises", premises);
        return "choosing-premises";
    }*/

    @GetMapping("/{rackId}")
    public String premises(
            @PathVariable("rackId") Long rackId,
            Model model) {

        Rack rack = rackService.findById(rackId);
        Premises premises = rack.getPremises();
        RackModel rackModel = rack.getRackModel();
        model.addAttribute("rack", rack);
        model.addAttribute("premises", premises);
        model.addAttribute("rackModel", rackModel);

        return "rack";
    }

/*    @RequestMapping("/add")
    public String addPremises(Model model) {
        model.addAttribute("premises", new Premises());
        return "add-premises";
    }

    @PostMapping("/add")
    public ModelAndView addPremises(@ModelAttribute Premises premises) {
        System.out.println(premises.toString());
        premisesService.addPremises(premises);
        return new ModelAndView("redirect:/api/v1/premises");
    }*/
}

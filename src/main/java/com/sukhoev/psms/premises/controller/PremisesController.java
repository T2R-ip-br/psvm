package com.sukhoev.psms.premises.controller;

import com.sukhoev.psms.premises.entity.Premises;
import com.sukhoev.psms.premises.service.PremisesService;
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
@RequestMapping(path = "/api/v1/premises")
@AllArgsConstructor
public class PremisesController {

    private final PremisesService premisesService;

    @RequestMapping
    public String choosingPremises(Model model) {
        List<Premises> premises = premisesService.findAll();
        model.addAttribute("premises", premises);
        return "choosing-premises";
    }

    @RequestMapping("/{premisesId}")
    public String premises(
            @PathVariable("premisesId") Long premisesId,
            Model model) {

        List<Rack> racks = premisesService.findRacksByPremisesId(premisesId);
        Premises premises = premisesService.findById(premisesId);
        model.addAttribute("racks", racks);
        model.addAttribute("premises", premises);

        return "premises";
    }

    @RequestMapping("/add")
    public String addPremises(Model model) {
        model.addAttribute("premises", new Premises());
        return "add-premises";
    }

    @PostMapping("/add")
    public ModelAndView addPremises(@ModelAttribute Premises premises) {
        premisesService.addPremises(premises);
        return new ModelAndView("redirect:/api/v1/premises");
    }
}

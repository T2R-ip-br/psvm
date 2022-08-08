package com.sukhoev.psms.rack.controller;

import com.sukhoev.psms.premises.entity.Premises;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackModel;
import com.sukhoev.psms.rack.service.RackModelService;
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
@RequestMapping(path = "/api/v1/rack-model")
@AllArgsConstructor
public class RackModelController {

    private final RackModelService rackModelService;

    @RequestMapping("/add/{premisesId}")
    public String addRack(
            @PathVariable("premisesId") int premisesId,
            Model model) {
        model.addAttribute("rackModel", new RackModel());
        return "add-rack-model";
    }

    @PostMapping("/add/{premisesId}")
    public ModelAndView saveRack(
            @PathVariable("premisesId") int premisesId,
            @ModelAttribute RackModel rackModel
    ) {
        rackModelService.addRackModel(rackModel);
        return new ModelAndView("redirect:/api/v1/rack/add/" + premisesId);
    }
}

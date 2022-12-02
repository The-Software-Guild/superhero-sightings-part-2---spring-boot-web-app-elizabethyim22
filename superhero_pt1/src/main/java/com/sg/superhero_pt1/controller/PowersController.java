package com.sg.superhero_pt1.controller;


import com.sg.superhero_pt1.dao.PowersDao;
import com.sg.superhero_pt1.model.Address;
import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.Powers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PowersController {
    @Autowired
    PowersDao powersDao;

    @GetMapping("powers")
    public String getAllPowers(Model model) {
        List<Powers> powers = powersDao.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @PostMapping("addPowers")
    public String addPowers(String powers_name) {
        Powers powers = new Powers();
        powers.setPowers_name(powers_name);
        powersDao.addPowers(powers);
        return "redirect:/powers";
    }

    @GetMapping("powers/{powers_id}")
    public Powers getPowersById(@PathVariable int powers_id) {
        return powersDao.getPowersById(powers_id);
    }

    @GetMapping("deletePowers")
    public String deletePowers(Integer powers_id) {
        powersDao.deletePowersById(powers_id);
        return "redirect:/powers";
    }

//    @GetMapping("editPowers")
//    public String editPowers(Integer powers_id, Model model){
//        Powers power = powersDao.getPowersById(powers_id);
//        model.addAttribute("power", power);
//        return "editPowers";
//    }
//
//    @PostMapping("editPowers")
//    public String performUpdateMember(@Valid Powers power, BindingResult result) {
//        if(result.hasErrors()){
//            return "editPowers";
//        }
//        powersDao.editPowers(power);
//        return "redirect:/powers";
//    }

    //this edit is different from members
    @GetMapping("editPowers")
    public String editPowers(HttpServletRequest request, Model model) {
        int powers_id = Integer.parseInt(request.getParameter("powers_id"));
        Powers powers = powersDao.getPowersById(powers_id);
        model.addAttribute("powers", powers);
        return "editPowers";
    }

    @PostMapping("editPowers")
    public String performEditPowers(HttpServletRequest request) {
        int powers_id = Integer.parseInt(request.getParameter("powers_id"));
        Powers powers = powersDao.getPowersById(powers_id);
        powers.setPowers_name(request.getParameter("powers_name"));
        powersDao.editPowers(powers);
        return "redirect:/powers";
    }

}

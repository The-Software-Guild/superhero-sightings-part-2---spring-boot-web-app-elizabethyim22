package com.sg.superhero_pt1.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.*;

import com.sg.superhero_pt1.dao.AddressDao;
import com.sg.superhero_pt1.dao.MemberDao;
import com.sg.superhero_pt1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.sg.superhero_pt1.dao.SightingDao;

@Controller
public class SightingController {

    @Autowired
    SightingDao sightingDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    AddressDao addressDao;

    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();

    @GetMapping("homepage")
    public String getFirstTen(Model model) {
        model.addAttribute("errors", violations);
        List<Sighting> sightings = sightingDao.getLastTenSightings();
        model.addAttribute("sightings", sightings);
        return "homepage";
    }

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        model.addAttribute("errors", violations);
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<MemberViewDetail> members = memberDao.getAllMembers();
        List<SightingViewDetail> sightingDetails = sightingDao.getSightingDetail();
        List<Address> addresses = addressDao.getAllAddresses();
        model.addAttribute("sightings", sightings);
        model.addAttribute("members", members);
        model.addAttribute("sightingDetails", sightingDetails);
        model.addAttribute("addresses", addresses);
        return "sightings";
    }


    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) throws ParseException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String add_id = request.getParameter("add_id");
        int member_id = Integer.parseInt(request.getParameter("member_id"));
        String date = request.getParameter("date");
//        String date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);

        Sighting sighting = new Sighting();
        sighting.setName(name);
        sighting.setDescription(description);
        sighting.setLatitude(Double.parseDouble(latitude));
        sighting.setLongitude(Double.parseDouble(longitude));
        sighting.setAdd_id(Integer.parseInt(add_id));
        sightingDao.addSighting(sighting);

        Member member = new Member();
        member.setMember_id(member_id);

        SightingViewDetail svd = new SightingViewDetail();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        svd.setDate(formatter.parse(request.getParameter("date")));
        svd.setDate(formatter.parse(date));
        sightingDao.addMemberToSighting(sighting,member,svd);
        return "redirect:/sightings";
    }


//    @PostMapping("addSighting")
//    public String addSighting(String sighting_name, String description, , int org_id){
//        Member member = new Member();
//        member.setMember_name(member_name);
//        member.setDescription(member_description);
//        member.setPowers_id(powers_id);
//        memberDao.addMember(member);
//        //this is trying to add to memberorg
//        Organization organization = new Organization();
//        organization.setOrg_id(org_id);
//        memberDao.addMemberToOrg(member,organization);
//        return "redirect:/members";
//    }

    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightingDao.getSightingById(id);
        List<MemberViewDetail> members = memberDao.getAllMembers();
        model.addAttribute("members", members);
        List<Address> addresses = addressDao.getAllAddresses();
        model.addAttribute("addresses", addresses);
        model.addAttribute("sighting", sighting);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request) throws ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        int member_id = Integer.parseInt(request.getParameter("member_id"));
        Sighting sighting = sightingDao.getSightingById(id);
        Member member = memberDao.getMemberById(member_id);
        sighting.setName(request.getParameter("name"));
        sighting.setDescription(request.getParameter("description"));
        sighting.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        sighting.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        sighting.setAdd_id(Integer.parseInt(request.getParameter("add_id")));
        member.setMember_id(Integer.parseInt(request.getParameter("member_id")));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SightingViewDetail svd = new SightingViewDetail();
        svd.setDate(formatter.parse(request.getParameter("date")));

        sightingDao.updateSighting(sighting);
        sightingDao.updateMemberSighting(sighting,member,svd);

        return "redirect:/sightings";
    }
}

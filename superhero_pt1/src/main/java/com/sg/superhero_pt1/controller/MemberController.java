package com.sg.superhero_pt1.controller;

import com.sg.superhero_pt1.dao.MemberDao;
import com.sg.superhero_pt1.dao.OrganizationDao;
import com.sg.superhero_pt1.dao.PowersDao;
import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.MemberViewDetail;
import com.sg.superhero_pt1.model.Organization;
import com.sg.superhero_pt1.model.Powers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberDao memberDao;
    @Autowired
    PowersDao powersDao;
    @Autowired
    OrganizationDao organizationDao;

    @GetMapping("members")
    public String displayMembers(Model model){
//        List<Member> members = memberDao.getAllMembers();
        List<MemberViewDetail> members = memberDao.getAllMemberViewDetails();
        model.addAttribute("members", members);
        List<Powers> powers = powersDao.getAllPowers();
        model.addAttribute("powers", powers);
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "members";
    }

//    @PostMapping("addMember")
//    public String addMember(String member_name, String member_description, int powers_id){
//        Member member = new Member();
//        member.setMember_name(member_name);
//        member.setDescription(member_description);
//        member.setPowers_id(powers_id);
//        memberDao.addMember(member);
//        return "redirect:/members";
//    }

    @PostMapping("addMember")
    public String addMember(String member_name, String member_description, int powers_id, int org_id){
        Member member = new Member();
        member.setMember_name(member_name);
        member.setDescription(member_description);
        member.setPowers_id(powers_id);
        memberDao.addMember(member);
        //this is trying to add to memberorg
        Organization organization = new Organization();
        organization.setOrg_id(org_id);
        memberDao.addMemberToOrg(member,organization);
        return "redirect:/members";
    }

    @GetMapping("member/{member_id}")
    public Member getMember(@PathVariable int member_id){
        Member member = memberDao.getMemberById(member_id);
        return member;
    }

    @GetMapping("sightingMembers/{sighting_id}")
    public List<Member> getMembersAtSighting(@PathVariable int sighting_id){
        List<Member> members = memberDao.getAllMembersAtSighting(sighting_id);
        return members;
    }

    @GetMapping("organizationMembers/{org_id}")
    public List<Member> getMembersAtOrganization(@PathVariable int org_id){
        List<Member> members = memberDao.getAllMembersInOrganization(org_id);
        return members;
    }

    @GetMapping("deleteMember")
    public String deleteMember(Integer member_id){
        memberDao.deleteMemberById(member_id);
        return "redirect:/members";
    }

    @GetMapping("updateMember")
    public String editMember(Integer member_id, Model model){
        Member member = memberDao.getMemberById(member_id);
        List<Powers> powers = powersDao.getAllPowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("powers", powers);
        model.addAttribute("member", member);
        model.addAttribute("organizations", organizations);
        return "updateMember";
    }

//    @GetMapping("updateMember")
//    public String editMember(Integer member_id, Integer org_id, Model model){
//        Member member = memberDao.getMemberById(member_id);
//        Organization organization = organizationDao.getOrganizationById(org_id);
//        List<Powers> powers = powersDao.getAllPowers();
//        model.addAttribute("powers", powers);
//        List<Organization> organizations = organizationDao.getAllOrganizations();
//        model.addAttribute("organizations", organizations);
//        model.addAttribute("member", member);
//        return "updateMember";
//    }

//    @PostMapping("updateMember")
//    public String performUpdateMember(@Valid Member member, BindingResult result) {
//        if(result.hasErrors()){
//            return "updateMember";
//        }
//        memberDao.updateMember(member);
//        return "redirect:/members";
//    }

    @PostMapping("updateMember")
    public String performUpdateMember(HttpServletRequest request) {
        int member_id = Integer.parseInt(request.getParameter("member_id"));
        int org_id = Integer.parseInt(request.getParameter("org_id"));
        Member member = memberDao.getMemberById(member_id);
        Organization organization = organizationDao.getOrganizationById(org_id);
        member.setMember_name(request.getParameter("member_name"));
        member.setDescription(request.getParameter("description"));
        member.setPowers_id(Integer.parseInt(request.getParameter("powers_id")));
        organization.setOrg_id(Integer.parseInt(request.getParameter("org_id")));
        memberDao.updateMember(member);
        memberDao.updateMemberOrg(member,organization);
        return "redirect:/members";
    }

}

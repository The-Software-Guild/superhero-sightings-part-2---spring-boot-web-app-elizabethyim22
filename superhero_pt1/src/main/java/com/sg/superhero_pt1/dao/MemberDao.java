package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.MemberViewDetail;
import com.sg.superhero_pt1.model.Organization;
import com.sg.superhero_pt1.model.Sighting;

import java.util.List;

public interface MemberDao {
    Member addMember(Member member);
    List<Member> getAll();
    public List<MemberViewDetail> getAllMembers();
    List<MemberViewDetail> getAllMemberViewDetails();
    Member getMemberById(int id);
    List<Member> getAllMembersAtSighting(int sightingId);
    List<Member> getAllMembersInOrganization(int org_id);
    void deleteMemberById(int member_id);
    void updateMember(Member member);
    //this is trying to add to memberorg
    void addMemberToOrg(Member member, Organization organization);
    void updateMemberOrg(Member member, Organization organization);

}


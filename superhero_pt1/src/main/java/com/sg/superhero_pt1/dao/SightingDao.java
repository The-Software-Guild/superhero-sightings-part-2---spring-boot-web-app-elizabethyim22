package com.sg.superhero_pt1.dao;

import java.util.List;

import com.sg.superhero_pt1.model.*;

public interface SightingDao {
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void addMemberToSighting(Sighting sighting, Member member, SightingViewDetail svd);
//    void addMemberToSighting(Sighting sighting, Member member, SightingDate sd);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    List<SightingViewDetail> getSightingDetail();
    List<Sighting> getLastTenSightings();
    void updateMemberSighting(Sighting sighting, Member member, SightingViewDetail svd);

    // List<Sighting> getSightingsForMember(Member member);

}

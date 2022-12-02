package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Powers;

import java.util.List;

public interface PowersDao {
    Powers addPowers(Powers powers);
    List<Powers> getAllPowers();
    Powers getPowersById(int powers_id);
    void editPowers(Powers powers);
    void deletePowersById(int powers_id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;

import com.mycompany.webapp.esa.model.Leader;
import java.util.List;


/**
 *
 * @author Viktoria Bock
 */
public interface LeaderServiceInterface {
    List<Leader> getAllLeaders();
    Leader getLeaderByEmail(String email);
    void addLeader(Leader leader);
    Leader getLeaderById(int id);
}

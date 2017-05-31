/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;

import com.mycompany.webapp.esa.model.Club;
import java.util.List;

/**
 *
 * @author roml
 */
public interface ClubServiceInterface {
    List<Club> getAllClubs();
    void addClub(Club club);
    void deleteClub(Club club);
    void updateClub(Club club);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Club.PresentationModel;

import Util.AuthenticationManager;
import Club.DataModel.Club;
import AppUser.Leader.DataModel.Leader;
import AppUser.Participant.DataModel.Participant;
import Club.Service.ClubServiceInterface;
import AppUser.Leader.Service.LeaderServiceInterface;
import AppUser.Participant.Service.ParticipantServiceInterface;
import Util.Pages;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


/**
 *
 * @author Viktoria Bock
 */
@Named
@ViewScoped
public class IndexPModel implements Serializable {

    @Inject
    private ClubServiceInterface clubService;

    @Inject
    private LeaderServiceInterface leaderService;
    
     @Inject
    private ParticipantServiceInterface participantService;
     
     @Inject
     private AuthenticationManager am;

    private List<Club> ClubList;
    private List<String> images;

    @PostConstruct
    void init() {
        


        ClubList = clubService.getAllClubs();
        if(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()!=null && !am.isLoggedIn()){
            am.setCurrentUserId();
            
        }
       
    /*Participant romina=new Participant();
    romina.setEmail("romina.herbst@hs-furtwangen.de");
    romina.setFirstname("Romina");
    romina.setLastname("Herbst");
 
    romina.setClubs(new ArrayList<Club>());
    participantService.addParticipant(romina);
        System.out.println("romina: "+romina);
   
    
    Leader viktoria= new Leader();
    viktoria.setFirstname("Viktoria");
    viktoria.setLastname("Bock");
    viktoria.setEmail("viktoria.bock@hs-furtwangen.de");
        viktoria.setClubs(new ArrayList<Club>());
    leaderService.addLeader(viktoria);
        System.out.println("loggedin: "+am.isLoggedIn());
        
        //Leader ll= leaderService.getLeaderByEmail("viktoria.bock@hs-furtwangen.de");
       
        //System.out.println("Vorname:"+ll.getLastname());*/

        images = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            images.add("slider" + i + ".png");
        }
    }

    public List<Club> getClubList() {

        return ClubList;
    }

    public String updateClubList() {
        System.out.print("geupdatet");
        ClubList = clubService.getAllClubs();
        return "/" + Pages.INDEX;
    }

    public List<String> getImages() {
        return images;
    }

}

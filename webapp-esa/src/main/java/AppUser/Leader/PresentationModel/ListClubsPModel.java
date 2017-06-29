/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUser.Leader.PresentationModel;

import Util.AuthenticationManager;
import Club.DataModel.Club;
import AppUser.Leader.DataModel.Leader;
import Club.Service.ClubServiceInterface;
import AppUser.Leader.Service.LeaderServiceInterface;
import Util.Pages;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Viktoria Bock
 */
@Named
@ViewScoped
public class ListClubsPModel implements Serializable{

   
    @Inject private ClubServiceInterface clubService;
    @Inject private AuthenticationManager authenticationManager;
    @Inject private LeaderServiceInterface leaderService;
  
   
    private Leader leader;
    private Club clubToDelete; 
    private List<Club> ClubList;
        
        @PostConstruct
        void init(){
            
             
            leader = (Leader) leaderService.getLeaderById(authenticationManager.getCurrentUserId());
            System.out.println("der leader"+leader +"******************************");
            if(leader!= null){
                
                ClubList = leader.getClubs();
                for(Club c:ClubList){
                    System.out.println("Leader-Clubs: "+c.getTitle());
                }
            }else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title","Leader konnte nicht gefunden werden");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            
            
        }

        public List<Club> getClubList() {
            return ClubList;
        }

        
	public String doListClubs(Club club) {
		System.out.println("List all my Clubs "+club);
		return Pages.LIST_CLUBS;
	}
        
        public void doDeleteClub(Club club) {
		this.clubToDelete = club;
		
	}

	public void commitDeleteClub() {
                clubService.deleteClub(clubToDelete);
                    updateClubList();
                
	}
        public  void updateClubList(){
            System.out.println("$$$$$$$ClubListWasUpdatet");
            leader= leaderService.getLeaderById(authenticationManager.getCurrentUserId());
            if(leader!= null){
            ClubList= leader.getClubs();
            }
        }
}

package com.mycompany.webapp.esa.controller;


import business.ClubService;
import com.mycompany.webapp.esa.data.ClubListProducer;
import com.mycompany.webapp.esa.data.ClubProducer;
import com.mycompany.webapp.esa.model.Leader;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

@SessionScoped
@Named
public class EditClubController implements Serializable {
	private static final long serialVersionUID = 2815796004558360299L;

	@Inject
	private ClubListProducer clubListProducer;
	@Inject
	private ClubProducer clubProducer;
        @Inject 
        private ClubService clubService;
        
        @Inject 
        private ListClubsController listClubsController;
        
       
	public String doSave() {
                    //Solange noch keine Login vorhanden, wird hier Standard-Leader setzten
                    Leader leader = new Leader();
                    leader.setId(1);
                    leader.setFirstname("Hans");
                    leader.setLastname("Wurst");
                    leader.setEmail("hw@web.de");
                    
                    clubProducer.getSelectedClub().setLeader(leader);
		if (clubProducer.isAddMode()) {

                    
                    if(clubService.doAddClub(clubProducer.getSelectedClub())){
                        listClubsController.updateClubList();
                    }else{
                        System.out.println("Beim Hinzufügen ist ein Fehler aufgetreten");
                    }
                    
                    //ohne DB
                    //clubListProducer.getClubs().add(clubProducer.getSelectedClub());
		}
                clubService.doEditClub(clubProducer.getSelectedClub());
		return Pages.LIST_CLUBS;
	}
	public String doCancel() {
		return Pages.LIST_CLUBS;
	}
}
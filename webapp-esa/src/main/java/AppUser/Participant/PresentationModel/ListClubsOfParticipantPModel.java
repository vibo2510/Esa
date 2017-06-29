/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUser.Participant.PresentationModel;

import Util.AuthenticationManager;
import Club.Service.ClubService;
import Club.DataModel.Club;
import AppUser.Participant.DataModel.Participant;
import Club.Service.ClubServiceInterface;
import AppUser.Participant.Service.ParticipantServiceInterface;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Viktoria Bock
 */
@Named
@ViewScoped
public class ListClubsOfParticipantPModel implements Serializable {

    @Inject
    private ClubServiceInterface clubService;
    @Inject
    private ParticipantServiceInterface participantService;
    @Inject
    private AuthenticationManager am;
    /**
     * Creates a new instance of ListClubsOfParticipantController
     */
    private List<Club> ClubList;
    private Club clubToDischarge;
    private Participant p;
    

    @PostConstruct
    void init() {
       System.out.println("ppppppppppppp ");
        p= (Participant) participantService.getParticipantById(am.getCurrentUserId());
        System.out.println("ppppppppppppp "+p);
        if (p!= null){
            System.out.println("scjncnsajcnajck"+p.getFirstname());
        ClubList = p.getClubs();
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Teilnehmer konnte nicht gefunden werden");
           RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public List<Club> getClubList() {
        return ClubList;
    }

    public void doDischarge(Club club) {
        this.clubToDischarge = club;
    }
    
    public void commitDischarge() {
        
        participantService.doDischarge(clubToDischarge, p);
        ClubList=clubService.getAllClubsOfParticipant(p);
            
    }

}

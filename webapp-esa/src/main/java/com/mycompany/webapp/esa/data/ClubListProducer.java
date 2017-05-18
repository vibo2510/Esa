/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.data;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.model.Participant;
import java.util.Date;



/**
 *
 * @author Viktoria Bock
 */
@SessionScoped
@Named
public class ClubListProducer implements Serializable{
    private List<Club> clubs;
    
    public ClubListProducer() {
		clubs = createMockClubs();
	}
	public List<Club> getClubs() {
		return clubs;
	}
	public List<Club> createMockClubs() {
            
                //Teilnehmerliste 1
                List<Participant> participants1= new LinkedList<Participant>();
                Participant participant1= new Participant(0, "Anna", "Müller", "a.mueller@hs-furtwangen.de");
                Participant participant2= new Participant(1, "Sebastian", "Stolz", "s.stolz@hs-furtwangen.de");
                Participant participant3= new Participant(2, "Thomas", "Klein", "t.klein@hs-furtwangen.de");
                participants1.add(participant1);
                participants1.add(participant2);
                participants1.add(participant3);
                
                //Teilnehmerliste 2
                List<Participant> participants2= new LinkedList<Participant>();
                Participant participant4= new Participant(3, "Lena", "Beck", "l.beck@hs-furtwangen.de");
                Participant participant5= new Participant(4, "Pascal", "Keller", "p.keller@hs-furtwangen.de");
                Participant participant6= new Participant(5, "Marc", "Krebs", "m.krebs@hs-furtwangen.de");
                participants2.add(participant4);
                participants2.add(participant5);
                participants2.add(participant6);
                
                //Club 1
		Club club1 = new Club();
                club1.setId(0);
                club1.setTitle("Standardtanz");
                club1.setDayOfWeek(Club.DayOfWeek.MONDAY);
                Date start1= new Date();
                start1.setMinutes(0);
                start1.setHours(20);
                club1.setStart(start1);
                Date end1= new Date();
                end1.setMinutes(30);
                end1.setHours(21);
                club1.setEnd(end1);
                club1.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
                        + "Aenean commodo ligula eget dolor. Aenean massa. "
                        + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
                        + "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. ");
                club1.setParticipants(participants1);
                club1.setLeader(new Leader(0,"Max","Mustermann","m.mustermann@hs-furtwangen.de"));
                club1.setMaxParticipants(30);
                club1.setRoom("Alte Cafete");
               
                
                //CLub 2
                Club club2 = new Club();
		club2.setId(1);
                club2.setTitle("Zumba");
                club2.setDayOfWeek(Club.DayOfWeek.TUESDAY);
                Date start2= new Date();
                start2.setMinutes(0);
                start2.setHours(20);
                club2.setStart(start2);
                Date end2= new Date();
                end2.setMinutes(30);
                end2.setHours(21);
                club2.setEnd(end2);
                club2.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
                        + "Aenean commodo ligula eget dolor. Aenean massa. "
                        + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
                        + "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. ");
                club2.setParticipants(participants2);
                club2.setLeader(new Leader(1,"Lena","Schmitt","l.schmitt@hs-furtwangen.de"));
                club2.setMaxParticipants(40);
                club2.setRoom("Aula");
                
                List<Club> ret = new LinkedList<>();
		ret.add(club1);
		ret.add(club2);
		return ret;
	}
}

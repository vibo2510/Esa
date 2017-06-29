/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Club.DataModel;

import AppUser.Leader.DataModel.Leader;
import AppUser.Participant.DataModel.Participant;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

/**
 *
 * @author Viktoria Bock
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Club.findAll", query = "SELECT c FROM Club c ORDER BY c.title")
    ,
@NamedQuery(name = "Club.findById", query = "SELECT c FROM Club c WHERE c.id= :id")})
public class Club implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Version
    private Long version;
    private String title;
    private DayOfWeek dayOfWeek;
    private Date starttime;
    private Date endtime;
    private String description;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LEADER_ID")
    private Leader leader;
    private int maxParticipants;
    private String room;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "CLUB_APPUSER",
            joinColumns = @JoinColumn(name = "CLUB_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPUSER_ID")
    )
    private List<Participant> participants;

    public static final String findAll = "Club.findAll";
    public static final String findById = "Club.findById";

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getVersion() {
        return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

}

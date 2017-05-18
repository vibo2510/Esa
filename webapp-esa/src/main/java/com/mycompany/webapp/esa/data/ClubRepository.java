/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.data;

/**
 *
 * @author Viktoria Bock
 */
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.model.Participant;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class ClubRepository implements Serializable{
   @Inject private  LeaderRepository leaderRepository;
    
    Connection conn;
    PreparedStatement prepstAdd;
    PreparedStatement prepstDelete;
    PreparedStatement prepstUpdate;
    Statement stAllClubs;
    PreparedStatement prepstLeaderClubs;
    PreparedStatement prepstParticiClubs;
    PreparedStatement prepstParticiOfClub;
    


    public ClubRepository() throws SQLException, ClassNotFoundException {
        getConnection();
        this.prepstAdd = conn.prepareStatement("insert into club(title,dayofweek,starttime,endtime,description,leader_id,maxParticipants,room) values(?,?,?,?,?,?,?,?)");
        this.prepstDelete= conn.prepareStatement("delete from club where id=?");
        this.prepstUpdate = conn.prepareStatement("update club set title = ?,dayofweek = ?,starttime=?,endtime=?, description= ?, leader_id=?, maxparticipants=?,room=? where id = ?");
        this. stAllClubs = conn.createStatement();
        this.prepstLeaderClubs = conn.prepareStatement("select * from club where LEADER_ID = ?");
        this.prepstParticiClubs = conn.prepareStatement("select club.id, club.title, club.dayofweek, club.starttime, club.endtime, club.description, club.leader_id,club.maxparticipants,club.room \n" +
                                                        "from club inner join participant_club on club.id=participant_club.club_id\n" +
                                                        "where participant_club.participant_id=?");
        this.prepstParticiOfClub = conn.prepareStatement("select participant.id, participant.first_name, participant.last_name, participant.email from participant \n" +
                                                        "inner join participant_club on participant.id=participant_club.participant_id\n" +
                                                        "where PARTICIPANT_CLUB.CLUB_ID= ?");
    }
    

    
    
    public void getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String dbUrl = "jdbc:derby://localhost:1527/ClubOrgaDB";
        conn=DriverManager.getConnection(dbUrl,"rovi", "123");
    }
    public boolean addClub(Club club){
        
        try {
            
            
            prepstAdd.setString(1, club.getTitle());
            prepstAdd.setString(2, club.getDayOfWeek().toString());
            prepstAdd.setTimestamp(3, new Timestamp(club.getStart().getTime()));
            prepstAdd.setTimestamp(4, new Timestamp (club.getEnd().getTime()));
            prepstAdd.setString(5, club.getDescription());
            prepstAdd.setInt(6, club.getLeader().getId());
            prepstAdd.setInt(7, club.getMaxParticipants());
            prepstAdd.setString(8, club.getRoom());
            prepstAdd.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClubRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean deleteClub(Club club){
        try {
            
            prepstDelete.setInt(1, club.getId());
            prepstDelete.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClubRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
    
     public boolean updateClub(Club club){
        
        try {
            //getConnection();
            prepstUpdate.setString(1, club.getTitle());
            prepstUpdate.setString(2, club.getDayOfWeek().toString());
            prepstUpdate.setTimestamp(3, new Timestamp(club.getStart().getTime()));
            prepstUpdate.setTimestamp(4, new Timestamp (club.getEnd().getTime()));
            prepstUpdate.setString(5, club.getDescription());
            prepstUpdate.setInt(6, club.getLeader().getId());
            prepstUpdate.setInt(7, club.getMaxParticipants());
            prepstUpdate.setString(8, club.getRoom());
            prepstUpdate.setInt(9, club.getId());
            prepstUpdate.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClubRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
     
    public ArrayList<Club> getAllClubs(){
         ArrayList<Club> clubList = new ArrayList<Club>();
        try {
            LeaderRepository lr= new LeaderRepository();
            ResultSet rs = stAllClubs.executeQuery("select * from club");
            while(rs.next()){
                Club club= new Club();
                club.setId(rs.getInt("ID"));
                club.setTitle(rs.getString("TITLE"));
                club.setDayOfWeek(Club.DayOfWeek.valueOf(rs.getString("DAYOFWEEK")));
                club.setStart(rs.getTimestamp("STARTTIME"));
                club.setEnd(rs.getTimestamp("ENDTIME"));
                club.setDescription(rs.getString("Description"));
                club.setMaxParticipants(rs.getInt("MAXPARTICIPANTS"));
                club.setRoom(rs.getString("ROOM"));                
                club.setLeader(lr.getLeaderById(rs.getInt("ID")));
                clubList.add(club);
            }
            return clubList;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
     
     public ArrayList<Club> getLeaderClubs(Leader leader){
         ArrayList<Club> listClubs = new ArrayList<Club>();
        try {
      
            prepstLeaderClubs.setInt(1, leader.getId());
            ResultSet rs = prepstLeaderClubs.executeQuery();
            while(rs.next()){
                Club club= new Club();
                club.setId(rs.getInt("ID"));
                club.setTitle(rs.getString("TITLE"));
                club.setDayOfWeek(Club.DayOfWeek.valueOf(rs.getString("DAYOFWEEK")));
                club.setStart(rs.getTimestamp("STARTTIME"));
                club.setEnd(rs.getTimestamp("ENDTIME"));
                club.setDescription(rs.getString("Description"));
                club.setMaxParticipants(rs.getInt("MAXPARTICIPANTS"));
                club.setRoom(rs.getString("ROOM"));
                club.setLeader(leader);
                listClubs.add(club);
            }
            return listClubs;
        } catch (SQLException ex) {
            Logger.getLogger(ClubRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
     
   public ArrayList<Club> getParticipantClubs(Participant participant){
         ArrayList<Club> listClubs = new ArrayList<Club>();
        try {
           LeaderRepository lr= new LeaderRepository();
            prepstParticiClubs.setInt(1, participant.getId());
            ResultSet rs = prepstParticiClubs.executeQuery();
            while(rs.next()){
                Club club= new Club();
                club.setId(rs.getInt("ID"));
                club.setTitle(rs.getString("TITLE"));
                club.setDayOfWeek(Club.DayOfWeek.valueOf(rs.getString("DAYOFWEEK")));
                club.setStart(rs.getTimestamp("STARTTIME"));
                club.setEnd(rs.getTimestamp("ENDTIME"));
                club.setDescription(rs.getString("Description"));
                club.setMaxParticipants(rs.getInt("MAXPARTICIPANTS"));
                club.setRoom(rs.getString("ROOM"));
                club.setLeader(lr.getLeaderById(rs.getInt("ID")));
                listClubs.add(club);
            }
            return listClubs;
        } catch (SQLException ex) {
            Logger.getLogger(ClubRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
     
     
     public ArrayList<Participant> getParticiOfClub(Club club){
         ArrayList<Participant> listPatici= new ArrayList<Participant>();
        try {
      
                     prepstParticiOfClub.setInt(1, club.getId());
         ResultSet rs= prepstParticiOfClub.executeQuery();
         
         while(rs.next()){
             Participant participant= new Participant();
             participant.setId(rs.getInt("ID"));
             participant.setFirstname(rs.getString("FIRST_NAME"));
             participant.setLastname(rs.getString("LAST_NAME"));
             participant.setEmail(rs.getString("EMAIL"));
             listPatici.add(participant);
         }
         return listPatici;
        } catch ( SQLException ex) {
            Logger.getLogger(ClubRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
     
     
     
     public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ClubRepository cr= new ClubRepository();
        
        Club club = new Club();
        club.setId(20);
        club.setTitle("Bowling");
        java.util.Date start1= new java.util.Date();
        start1.setMinutes(30);
        start1.setHours(21);
        club.setStart(start1);
        club.setEnd(start1);
        club.setDayOfWeek(Club.DayOfWeek.MONDAY);
        club.setDescription("svndjnsdvjjsdvn");
        club.setMaxParticipants(10);
        Leader l= new Leader();
        l.setId(1);
        l.setFirstname("Dieter");
        l.setLastname("Kramer");
        l.setEmail("ab@web.de");
        club.setLeader(l);
        club.setRoom("Aula");
        cr.addClub(club);
         
        System.out.println("DB ausgeführt");
        
           
    }

    }

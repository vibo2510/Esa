/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.model;


import java.util.Date;
import java.util.List;

/**
 *
 * @author Viktoria Bock
 */
public class Club {
	private int id;
	private String title;
	private DayOfWeek dayOfWeek;
	private Date start;
	private Date end;
	private String description;
	private List<Participant> participants;
	private Leader leader;
	private int maxParticipants;
	private String room;
        

	
	
	public enum DayOfWeek{
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
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
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


	
	
	
	
}

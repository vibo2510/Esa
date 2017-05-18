package com.mycompany.webapp.esa.data;

import com.mycompany.webapp.esa.model.Club;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class ClubProducer implements Serializable {
	private static final long serialVersionUID = -1828660647917534556L;

	private enum Mode {
		EDIT, ADD
	}
	private Club club= new Club();
	private Mode mode;

	public Club getSelectedClub() {
		return club;
	}
	public void setSelectedClub(Club club) {
		this.club = club;
	}
	public boolean isAddMode() {
		return mode == Mode.ADD;
	}
	public void prepareAddClub() {
		this.club = new Club();
		this.mode = Mode.ADD;
	}
	public void prepareEditClub(Club club) {
		this.club = club;
		this.mode = Mode.EDIT;
	}
}
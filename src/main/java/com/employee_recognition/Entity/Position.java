package com.employee_recognition.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="position")
public class Position {
	@Id
	@Column(name = "position_id")
	private int position_id;
	@Column(name = "position")
	private String position;
	public Position(String p, int i) {
		position_id = i;
		position = p;
	}
	public int getPosition_id() {
		return position_id;
	}
	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}

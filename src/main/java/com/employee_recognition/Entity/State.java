package com.employee_recognition.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="state")
public class State {
	@Id
	@Column(name = "state_id")
	private int state_id;
	@Column(name = "state")
	private String state;
	public State(String s, int i) {
		state_id = i;
		state = s;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}

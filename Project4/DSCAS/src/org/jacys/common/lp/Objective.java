package org.jacys.common.lp;

import java.util.ArrayList;
import java.util.List;

public class Objective {

	List<Incident> incidents;
	ObjectiveDirection direction;
	
	public Objective() {
		incidents = new ArrayList<Incident>();
		this.direction = ObjectiveDirection.NONE;
	}
	
	public Objective(ObjectiveDirection direction) {
		incidents = new ArrayList<Incident>();
		this.direction = direction;
	}
		
	public Objective(Incident incident, ObjectiveDirection direction) {
		this.incidents = new ArrayList<Incident>();
		this.incidents.add(incident);
		this.direction = direction;
	}

	public Objective(List<Incident> incidents, ObjectiveDirection direction) {
		this.incidents = incidents;
		this.direction = direction;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public ObjectiveDirection getDirection() {
		return direction;
	}

	public void setDirection(ObjectiveDirection direction) {
		this.direction = direction;
	}
	
	public void addIncident(Incident incident) {
		incidents.add(incident);
	}
}

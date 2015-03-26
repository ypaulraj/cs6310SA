package org.jacys.common.lp;

import java.util.ArrayList;
import java.util.List;

public class Constraint {

	String name;
	double rhs;
	ConstraintType type;
	List<Incident> incidents;
	
	public Constraint(String name, double rhs, ConstraintType type) {
		this.name = name;
		this.rhs = rhs;
		this.type = type;
		incidents = new ArrayList<Incident>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRhs() {
		return rhs;
	}

	public void setRhs(double rhs) {
		this.rhs = rhs;
	}

	public ConstraintType getType() {
		return type;
	}

	public void setType(ConstraintType type) {
		this.type = type;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}
	
	public void addIncident(Incident incident) {
		incidents.add(incident);
	}
}

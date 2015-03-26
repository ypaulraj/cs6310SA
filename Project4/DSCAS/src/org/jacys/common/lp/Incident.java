package org.jacys.common.lp;

public class Incident {

	double coefficent;
	Variable variable;
	
	public Incident(double coefficent, Variable variable) {
		this.coefficent = coefficent;
		this.variable = variable;
	}

	public double getCoefficent() {
		return coefficent;
	}

	public void setCoefficent(double coefficent) {
		this.coefficent = coefficent;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	
	
}

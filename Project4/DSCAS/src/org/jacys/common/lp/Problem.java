package org.jacys.common.lp;

import java.util.ArrayList;
import java.util.List;

public class Problem {

	List<String> varNames = new ArrayList<String>();
	List<Variable> variables = new ArrayList<Variable>();
	List<Constraint> constraints = new ArrayList<Constraint>();
	Objective objective;
	
	public Problem(Objective objective) {
		this.objective = objective;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}
	
	public void addVariable(Variable variable) {
		varNames.add(variable.name);
		variables.add(variable);
	}
	
	public Variable getVariable(String name) {
		int ndx = varNames.indexOf(name);
		if (ndx != -1) {
			return variables.get(ndx);
		} else {
			return null;
		}
	}
	
	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);
	}
}

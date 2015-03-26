package org.jacys.common.lp;

public class Variable {

	public String name;
	double lowerBound;
	double upperBound;
	VariableType type;
	double solution;
	
	public Variable(String name, double lowerBound, double upperBound, VariableType type) {
		this.name = name;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public VariableType getType() {
		return type;
	}

	public void setType(VariableType type) {
		this.type = type;
	}

	public double getSolution() {
		return solution;
	}

	public void setSolution(double solution) {
		this.solution = solution;
	}

}

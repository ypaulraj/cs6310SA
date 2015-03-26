package org.jacys.common.lp;

import java.util.HashMap;
import java.util.Map;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;

public class GurobiSolver implements Solver {

	@Override
	public void solve(Problem problem) {
		try {
			System.out.println("[Begin solving problem]");

			GRBEnv env = new GRBEnv("problem1.log");
			GRBModel model = new GRBModel(env);
			GRBVar grbVar;
			GRBLinExpr expr;
			char grbType;

			Map<String, GRBVar> grbVars = new HashMap<String, GRBVar>();

			// Create variables
			System.out.print("Creating variables...");
			for (Variable variable : problem.getVariables()) {
				switch (variable.getType()) {
				case Binary:
					grbType = GRB.BINARY;
					break;
				case Continuous:
					grbType = GRB.CONTINUOUS;
					break;
				case Integer:
				default:
					grbType = GRB.INTEGER;
				}
				grbVar = model.addVar(variable.getLowerBound(),
						variable.getUpperBound(), 0.0, grbType, variable.name);
				grbVars.put(variable.name, grbVar);
			}
			// Integrate new variables
			model.update();
			System.out.println("done");

			// Objective
			System.out.print("Creating objective...");
			expr = new GRBLinExpr();
			for (Incident incident : problem.getObjective().getIncidents()) {
				grbVar = grbVars.get(incident.getVariable().name);
				expr.addTerm(incident.getCoefficent(), grbVar);
			}
			int grbDir;
			switch (problem.getObjective().getDirection()) {
			case MAXIMIZE:
				grbDir = GRB.MAXIMIZE;
				break;
			case MINIMIZE:
			default:
				grbDir = GRB.MINIMIZE;
				break;
			}
			model.setObjective(expr, grbDir);
			System.out.println("done");

			// Create constraints
			System.out.print("Creating constraints...");
			for (Constraint constraint : problem.getConstraints()) {
				expr = new GRBLinExpr();
				for (Incident incident : constraint.getIncidents()) {
					grbVar = grbVars.get(incident.getVariable().name);
					expr.addTerm(incident.getCoefficent(), grbVar);
				}
				switch (constraint.getType()) {
				case GTEQ:
					grbType = GRB.GREATER_EQUAL;
					break;
				case LTEQ:
					grbType = GRB.LESS_EQUAL;
					break;
				case EQ:
				default:
					grbType = GRB.EQUAL;
					break;
				}
				model.addConstr(expr, grbType, constraint.getRhs(),
						constraint.getName());
			}
			System.out.println("done");

			// Optimize model
			System.out.print("Optimizing...");
			model.optimize();
			System.out.println("done");

			GRBVar x = grbVars.get("X");
			System.out.println(x.get(GRB.StringAttr.VarName) + " "
					+ x.get(GRB.DoubleAttr.X));
			System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));

			updateProblem(problem, model);

			// Dispose of model and environment
			model.dispose();
			env.dispose();

		} catch (GRBException e) {
			System.out.println("Error code: " + e.getErrorCode() + ". "
					+ e.getMessage());

		}

	}

	/**
	 * Update variables in problem with variable solution value in model.
	 * 
	 * @param problem
	 * @param model
	 */
	private void updateProblem(Problem problem, GRBModel model) {
		System.out.print("Updating problem with solution...");
		int cnt=0;
		GRBVar[] grbVars = model.getVars();
		for (GRBVar grbVar : grbVars) {
			try {
				cnt++;
				Variable variable = problem.getVariable(grbVar
						.get(GRB.StringAttr.VarName));
				variable.setSolution(grbVar.get(GRB.DoubleAttr.X));
			} catch (GRBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("["+cnt+"] done");
	}

	public void solveExample() {
		try {
			GRBEnv env = new GRBEnv("mip1.log");
			GRBModel model = new GRBModel(env);
			// Create variables
			GRBVar x = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "x");
			GRBVar y = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "y");
			GRBVar z = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "z");
			// Integrate new variables
			model.update();
			// Set objective: maximize x + y + 2 z
			GRBLinExpr expr = new GRBLinExpr();
			expr.addTerm(1.0, x);
			expr.addTerm(1.0, y);
			expr.addTerm(2.0, z);
			model.setObjective(expr, GRB.MAXIMIZE);
			// Add constraint: x + 2 y + 3 z <= 4
			expr = new GRBLinExpr();
			expr.addTerm(1.0, x);
			expr.addTerm(2.0, y);
			expr.addTerm(3.0, z);
			model.addConstr(expr, GRB.LESS_EQUAL, 4.0, "c0");
			// Add constraint: x + y >= 1
			expr = new GRBLinExpr();
			expr.addTerm(1.0, x);
			expr.addTerm(1.0, y);
			model.addConstr(expr, GRB.GREATER_EQUAL, 1.0, "c1");
			// Optimize model
			model.optimize();
			System.out.println(x.get(GRB.StringAttr.VarName) + " "
					+ x.get(GRB.DoubleAttr.X));
			System.out.println(y.get(GRB.StringAttr.VarName) + " "
					+ y.get(GRB.DoubleAttr.X));
			System.out.println(z.get(GRB.StringAttr.VarName) + " "
					+ z.get(GRB.DoubleAttr.X));
			System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
			// Dispose of model and environment
			model.dispose();
			env.dispose();
		} catch (GRBException e) {
			System.out.println("Error code: " + e.getErrorCode() + ". "
					+ e.getMessage());

		}
	}
}

package edu.gatech.lp;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_iocp;
import org.gnu.glpk.glp_prob;

public class StudentClassOptimizer {

	public static void main(String[] args) throws FileNotFoundException {

		University university = buildStudents();
		
		// Now build the GLPK objective
		
		//  Create problem    
	    glp_prob lp = GLPK.glp_create_prob();
	    System.out.println("Problem created");
	    GLPK.glp_set_prob_name(lp, "myProblem");
	    
	   // Build constraints
		
	    List<Constraint> constraints = new ArrayList<Constraint>();
	    
	    List<SWIGTYPE_p_int> intSWs = new ArrayList<SWIGTYPE_p_int>();
	    List<SWIGTYPE_p_double> douSWs = new ArrayList<SWIGTYPE_p_double>();
	    int firstConstraintIndex = GLPK.glp_add_rows(lp, constraints.size());

	    for(int i = 0; i < constraints.size(); i++)
	    {
	    	int ci = i + firstConstraintIndex ;
	    	Constraint c = constraints.get(i);
	    	GLPK.glp_set_row_name(lp, ci, c.getName());
	    	//todo GLPK.glp_set_row_bnds(lp, ci, GetGLPKKind(c.constraintType), c.RHS, c.RHS);   
	    	List<Incident> incidents = c.getIncidents();
	    	int incidentCount = incidents.size();
	    	SWIGTYPE_p_int ind = GLPK.new_intArray(incidentCount + 1);
	    	SWIGTYPE_p_double val = GLPK.new_doubleArray(incidentCount + 1); 
	    	int currentSWIGCounter = 1;
	    	for(Incident inc : incidents)
	    	{
	    		//int iIndex = variableToSolverIndex.get(inc.getVariable());
	    		GLPK.intArray_setitem(ind,currentSWIGCounter, i);
	    		GLPK.doubleArray_setitem(val, currentSWIGCounter, inc.coefficient);
	    		currentSWIGCounter++;
	    	}
	    	GLPK.glp_set_mat_row(lp, ci, incidentCount, ind, val);
	    }
	    
		// Set the Objective ( do I need it set up again)
	    
	    GLPK.glp_set_obj_name(lp, "objective");
	    GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MAX);

	    for(Incident inc : objectives)
	    {
	    	int vi = variableToSolverIndex.get(inc.variable);
	    	GLPK.glp_set_obj_coef(lp, vi, inc.coefficient);
	    }
	    
		// Solve the problem
	    solveProblem(lp);
	    
		// Print the results as needed.
		

	}

	private static void solveProblem(glp_prob lp) {
		glp_iocp mip_parm = new glp_iocp();
	    GLPK.glp_init_iocp(mip_parm);
	    mip_parm.setPresolve(GLPK.GLP_ON);            
	    int ret = GLPK.glp_intopt(lp, mip_parm);
	    if (ret == 0)
	    {
	        int status = GLPK.glp_mip_status(lp);
	        if(status != GLPK.GLP_UNDEF && status != GLPK.GLP_NOFEAS)
	               {
	                   pullSolution(lp, variableToSolverIndex);
	                   write_mip_solution(lp, variableToSolverIndex);
	               }
	               else
	               {
	                   System.out.println("The problem infeasible or undefined");
	               }
	    }
	    else
	    {
	         System.out.println("The problem could not be solved");
	    }
	}

	private static University buildStudents() throws FileNotFoundException {
		List<List<Integer>> studentSchedules = InputParser.readFile();

		University university = new University();

		Student student = null;
		Course course = null;

		for (List<Integer> studentSchedule : studentSchedules) {
			student = new Student();

			for (Integer courseNum : studentSchedule) {
				course = new Course(courseNum);
				student.addCourse(course);
			}
		}

		university.addStudent(student);
		return university;
	}

}

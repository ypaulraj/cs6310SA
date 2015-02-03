package edu.gatech.lp;

import java.util.List;

public interface  Solver
{
    void Solve(List<Variable> variables, List<Constraint> constraints, Objective objective);
}
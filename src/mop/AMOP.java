package mop;

import java.util.List;

import problems.AProblem;


public abstract class AMOP {
	
	protected int popSize;
	protected int neighbourSize;
	protected int objectiveDimesion ;
	protected AProblem problem;
	protected List<CMoChromosome> chromosomes;
	
	abstract void initial();
	abstract void generateInitialPop();
	abstract void reproduction();
	abstract void update();
	void setProblem(AProblem problem){
		this.problem = problem;
	}
	
	public int getNeighbourSize() {
		return neighbourSize;
	}
	public void setNeighbourSize(int neighbourSize) {
		this.neighbourSize = neighbourSize;
	}
	public int getPopSize() {
		return popSize;
	}
	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}
	
	
}

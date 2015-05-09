package mop;

import problems.AProblem;

public class MoChromosome {
	private static int objectiveDimesion;
	private static int genesDimesion;
	private double[] genes;
	private double objectiveValue;
	private double fitnessValue;
	
	public MoChromosome() {
		genesDimesion = AProblem.genesDimesion;
		objectiveDimesion = AProblem.objectiveDimesion;
	}
	
	
	public int getObjectiveDimesion() {
		return objectiveDimesion;
	}
	public void setObjectiveDimesion(int objectiveDimesion) {
		this.objectiveDimesion = objectiveDimesion;
	}
	public int getGenesDimesion() {
		return genesDimesion;
	}
	public void setGenesDimesion(int genesDimesion) {
		this.genesDimesion = genesDimesion;
	}
	public double[] getGenes() {
		return genes;
	}
	public void setGenes(double[] genes) {
		this.genes = genes;
	}
	public double getObjectiveValue() {
		return objectiveValue;
	}
	public void setObjectiveValue(double objectiveValue) {
		this.objectiveValue = objectiveValue;
	}
	public double getFitnessValue() {
		return fitnessValue;
	}
	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
	
	
	
	
	
}

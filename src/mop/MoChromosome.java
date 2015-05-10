package mop;

import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;
import org.apache.commons.math.random.RandomGenerator;

import problems.AProblem;

public class MoChromosome {
	private static int objectiveDimesion;
	private static int genesDimesion;
	private double[] genes;
	private double[] objectiveValue;
	private double fitnessValue;
	private static int[][] range;
	
	
	
	protected RandomDataImpl randomData;
	protected RandomGenerator randomGenerator;
	
	public void randomize(RandomData randomData) {
		randomizeParameter(randomData);
		fitnessValue = 0;
	}
	
	private void randomize() {
		randomizeParameter(randomData);
		fitnessValue = 0;
	}

	protected void randomizeParameter(RandomData randomData) {
		for (int i = 0; i < genes.length; i++) {
			genes[i] = randomData.nextUniform(range[i][0],
					range[i][1]);
		}
	}
	
	public MoChromosome() {
		genesDimesion = AProblem.genesDimesion;
		objectiveDimesion = AProblem.objectiveDimesion;
		range = AProblem.range;
		objectiveValue = new double[objectiveDimesion];
		randomGenerator = new RanMT();
		randomData = new RandomDataImpl(randomGenerator);
	}
	
	public static MoChromosome createChromosome() {
		MoChromosome mc = new MoChromosome();
		mc.randomize();
		return mc;
	}
	
	public void evaluate(AProblem problem){
		problem.evaluate(genes,objectiveValue);
	}
	
	
	public int getObjectiveDimesion() {
		return objectiveDimesion;
	}
	public int getGenesDimesion() {
		return genesDimesion;
	}
	public double[] getGenes() {
		return genes;
	}
	public void setGenes(double[] genes) {
		this.genes = genes;
	}
	public double getFitnessValue() {
		return fitnessValue;
	}
	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
	
	
}

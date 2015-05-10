package mop;

import java.util.ArrayList;
import java.util.List;

import problems.AProblem;

public class CMOP extends AMOP{

	protected double[] idealPoint;
	protected List<double[]> weights;
	protected List<int[]> neighbourTable;
	
	
	public CMOP(int popSize,int neighbourSize){
		this.popSize = popSize;
		this.neighbourSize = neighbourSize;
		this.objectiveDimesion = AProblem.objectiveDimesion;
		initial();
	}
	
	
	@Override
	void initial() {
		idealPoint = new double[objectiveDimesion];
		for(int i = 0; i < objectiveDimesion; i ++)
			idealPoint[i] = 1.0e+30;
		
		initWeight();
		initNeighbour();
		generateInitialPop();
		evaluateAfterInitial();
	}


	private void evaluateAfterInitial() {
		for(int i = 0 ;i < chromosomes.size(); i ++){
			chromosomes.get(i).evaluate(problem);
		}
	}


	private void initNeighbour() {
		neighbourTable = new ArrayList<int[]>(popSize);

		double[][] distancematrix = new double[popSize][popSize];
		for (int i = 0; i < popSize; i++) {
			distancematrix[i][i] = 0;
			for (int j = i + 1; j < popSize; j++) {
				distancematrix[i][j] = distance(weights.get(i), weights.get(j));
				distancematrix[j][i] = distancematrix[i][j];
			}
		}

		for (int i = 0; i < popSize; i++) {
			int[] index = Sorting.sorting(distancematrix[i]);
			int[] array = new int[neighbourSize];
			System.arraycopy(index, 0, array, 0, neighbourSize);
			neighbourTable.add(array);
		}
	}

	private static double distance(double[] weight1, double[] weight2) {
		double sum = 0;
		for (int i = 0; i < weight1.length; i++) {
			sum += Math.pow((weight1[i] - weight2[i]), 2);
		}
		return Math.sqrt(sum);
	}
	

	private void initWeight() {
		weights = new ArrayList<double[]>();
		for (int i = 0; i <= popSize; i++) {
			if (objectiveDimesion == 2) {
				double[] weight = new double[2];
				weight[0] = i / (double) popSize;
				weight[1] = (popSize - i) / (double) popSize;
				weights.add(weight);
			} else if (objectiveDimesion == 3) {
				for (int j = 0; j <= popSize; j++) {
					if (i + j <= popSize) {
						int k = popSize - i - j;
						double[] weight = new double[3];
						weight[0] = i / (double) popSize;
						weight[1] = j / (double) popSize;
						weight[2] = k / (double) popSize;
						weights.add(weight);
					}
				}
			}
		}
		
	}


	@Override
	void generateInitialPop() {
		chromosomes = new ArrayList<MoChromosome>(popSize);
		for(int i = 0; i < popSize; i ++)
		{
			chromosomes.add(MoChromosome.createChromosome());
		}
	}

	@Override
	void reproduction() {
		
	}

	@Override
	void update() {
		
	}

}

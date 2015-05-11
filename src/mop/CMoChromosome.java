package mop;

import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;
import org.apache.commons.math.random.RandomGenerator;

import problems.AProblem;

public class CMoChromosome extends MoChromosome {
	
	public static final int id_cx = 20;
	public static final int id_mu = 20;
	
	public static RandomDataImpl randomData;
	public static RandomGenerator randomGenerator;
	
	public void randomize(RandomData randomData) {
		randomizeParameter(randomData);
		fitnessValue = 0;
	}
	
	private void randomize() {
		randomizeParameter(randomData);
		fitnessValue = 0;
	}

	protected void randomizeParameter(RandomData randomData) {
		for (int i = 0; i < genesDimesion; i++) {
			genes[i] = randomData.nextUniform(range[i][0],
					range[i][1]);
		}
	}
	
	public CMoChromosome() {
		genesDimesion = AProblem.genesDimesion;
		objectiveDimesion = AProblem.objectiveDimesion;
		range = AProblem.range;
		objectiveValue = new double[objectiveDimesion];
		genes = new double[genesDimesion];
		randomGenerator = new RanMT();
		randomData = new RandomDataImpl(randomGenerator);
	}
	
	public static CMoChromosome createChromosome() {
		CMoChromosome mc = new CMoChromosome();
		mc.randomize();
		return mc;
	}
	
	public void evaluate(AProblem problem){
		problem.evaluate(genes,objectiveValue);
	}
	
	public void mutate(RandomGenerator rg, double rate) {
		double rnd, delta1, delta2, mut_pow, deltaq;
		double y, yl, yu, val, xy;
		double eta_m = 20;

		for (int j = 0; j < genesDimesion; j++) {
			if (rg.nextDouble() <= rate) {
				y = genes[j];
				yl = range[j][0];
				yu = range[j][1];

				delta1 = (y - yl) / (yu - yl);
				delta2 = (yu - y) / (yu - yl);

				rnd = rg.nextDouble();
				mut_pow = 1.0 / (eta_m + 1.0);
				if (rnd <= 0.5) {
					xy = 1.0 - delta1;
					val = 2.0 * rnd + (1.0 - 2.0 * rnd)
							* (Math.pow(xy, (eta_m + 1.0)));
					deltaq = Math.pow(val, mut_pow) - 1.0;
				} else {
					xy = 1.0 - delta2;
					val = 2.0 * (1.0 - rnd) + 2.0 * (rnd - 0.5)
							* (Math.pow(xy, (eta_m + 1.0)));
					deltaq = 1.0 - (Math.pow(val, mut_pow));
				}
				y = y + deltaq * (yu - yl);
				if (y < yl)
					y = yl;
				if (y > yu)
					y = yu;
				genes[j] = y;
			}
		}
		return;
	}

	public void diff_xover(MoChromosome ind0, MoChromosome ind1,
			MoChromosome ind2, RandomData randomData) {
		int nvar = ind0.range.length;
		// int idx_rnd = this.randomGenerator.nextInt(nvar);
		double rate = 0.5;
		for (int n = 0; n < nvar; n++) {
			/* Selected Two Parents */
			double lowBound = ind0.range[n][0];
			double upBound = ind0.range[n][1];
			genes[n] = ((CMoChromosome) ind0).genes[n]
					+ rate
					* (((CMoChromosome) ind2).genes[n] - ((CMoChromosome) ind1).genes[n]);

			if (genes[n] < lowBound) {
				genes[n] = randomData.nextUniform(lowBound, upBound);
			}
			if (genes[n] > upBound) {
				genes[n] = randomData.nextUniform(lowBound, upBound);
			}
		}
	}

	public void crossover(MoChromosome p1, MoChromosome p2){
		this.crossover(p1,p2,randomGenerator);
	}
	
	@Override
	public void crossover(MoChromosome p1, MoChromosome p2, RandomGenerator rg) {
		double rand;
		double y1, y2, yl, yu;
		double c1, c2;
		double alpha, beta, betaq;
		double eta_c = id_cx;

		CMoChromosome parent1 = (CMoChromosome) p1;
		CMoChromosome parent2 = (CMoChromosome) p2;
		int numVariables = p1.range.length;
		if (rg.nextDouble() <= 1.0) {
			for (int i = 0; i < numVariables; i++) {
				if (rg.nextDouble() <= 0.5) {
					if (Math.abs(parent1.genes[i] - parent2.genes[i]) > EPS) {
						if (parent1.genes[i] < parent2.genes[i]) {
							y1 = parent1.genes[i];
							y2 = parent2.genes[i];
						} else {
							y1 = parent2.genes[i];
							y2 = parent1.genes[i];
						}
						yl = p1.range[i][0];
						yu = p1.range[i][1];
						rand = rg.nextDouble();
						beta = 1.0 + (2.0 * (y1 - yl) / (y2 - y1));
						alpha = 2.0 - Math.pow(beta, -(eta_c + 1.0));
						if (rand <= (1.0 / alpha)) {
							betaq = Math.pow((rand * alpha),
									(1.0 / (eta_c + 1.0)));
						} else {
							betaq = Math.pow((1.0 / (2.0 - rand * alpha)),
									(1.0 / (eta_c + 1.0)));
						}
						c1 = 0.5 * ((y1 + y2) - betaq * (y2 - y1));
						beta = 1.0 + (2.0 * (yu - y2) / (y2 - y1));
						alpha = 2.0 - Math.pow(beta, -(eta_c + 1.0));
						if (rand <= (1.0 / alpha)) {
							betaq = Math.pow((rand * alpha),
									(1.0 / (eta_c + 1.0)));
						} else {
							betaq = Math.pow((1.0 / (2.0 - rand * alpha)),
									(1.0 / (eta_c + 1.0)));
						}
						c2 = 0.5 * ((y1 + y2) + betaq * (y2 - y1));
						if (c1 < yl)
							c1 = yl;
						if (c2 < yl)
							c2 = yl;
						if (c1 > yu)
							c1 = yu;
						if (c2 > yu)
							c2 = yu;
						if (rg.nextDouble() <= 0.5) {
							genes[i] = c2;
						} else {
							genes[i] = c1;
						}
					} else {
						genes[i] = parent1.genes[i];
					}
				} else {
					genes[i] = parent1.genes[i];
				}
			}
		} else {
			for (int i = 0; i < numVariables; i++) {
				genes[i] = parent1.genes[i];
			}
		}
	}

	@Override
	public double parameterDistance(MoChromosome another) {
		return 0;
	}

	@Override
	public String vectorString() {
		return null;
	}

	@Override
	public String getParameterString() {
		return null;
	}

	@Override
	public void copyTo(MoChromosome copyto) {
		copyto.range = this.range;
		copyto.fitnessValue = this.fitnessValue;
		System.arraycopy(objectiveValue, 0, copyto.objectiveValue, 0,
				objectiveValue.length);
	}

	@Override
	public void mutate(double mutationrate) {
		// TODO Auto-generated method stub
		this.mutate(randomGenerator,mutationrate);
	}

	@Override
	public void diff_xover(MoChromosome ind0, MoChromosome ind1) {
		// TODO Auto-generated method stub
//		this.diff_xover(ind0,ind1,(RandomData)randomData);
	}
	
	
	
	
}

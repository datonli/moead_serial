package mop;

public abstract class AMOP {
	
	private int popSize;
	private int neighbourSize;
	private int iterations;
	
	abstract void initial();
	abstract AMOP generateInitialPop();
	abstract void reproduction();
	abstract void update();
	
	
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
	public int getIterations() {
		return iterations;
	}
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	
	
	
	
}

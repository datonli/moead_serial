package problems;

public abstract class  AProblem {
	
	public static int genesDimesion;
	public static int objectiveDimesion;
	abstract void evaluate(double[] genes,double[] objValue);
}

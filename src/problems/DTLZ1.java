package problems;

public class DTLZ1 extends AProblem{

	
	private static DTLZ1 instance;
	
	private DTLZ1(){
		genesDimesion = 10;
		objectiveDimesion = 3;
		range = new int[genesDimesion][objectiveDimesion];
		for(int i = 0; i < genesDimesion; i ++){
			range[i][0] = 0;
			range[i][1] = 1;
		}
	} 
	
	public void evaluate(double[] genes, double[] objValue) {
		double g = g(genes);
		objValue[0] = (1+g)*genes[0]*genes[1];
		objValue[1] = (1+g)*genes[0]*(1-genes[1]);
		objValue[2] = (1+g)*(1-genes[0]);
	}

	private double g(double[] genes) {
		double s = 0;
		if(genesDimesion != genes.length)
			throw new IllegalArgumentException("genes dimesion wrong! check genesDimesion!!!");
		int n = genesDimesion;
		for(int i = 2; i < n; i ++)
			s += Math.pow(genes[i],2)-Math.cos(20*Math.PI*(genes[i]-0.5));
		s += 100*(n-2)+100*s;
		return s;
	}

	public static DTLZ1 getInstance() {
		if(instance == null)
			instance = new DTLZ1();
		return instance;
	}
	
}

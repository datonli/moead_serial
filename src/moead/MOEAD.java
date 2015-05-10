package moead;

import mop.AMOP;
import mop.CMOP;
import problems.AProblem;
import problems.ZDT1;

public class MOEAD {
	
	
	public static void main(String[] args){
		
		int popSize = 400;
		int neighbourSize = 20;
		int iterations = 200;
		
		AProblem problem = ZDT1.getInstance();
		AMOP mop = new CMOP(popSize,neighbourSize);
		
		
		
	}
	
}

package me.retrooper.mathapi;

/**
 * 
 * @author Retrooper
 * 
 * Example, teaching how to use the API
 *
 */
public class ExampleMathAPI {
	
	public static void main(String[] args) {
		//Debugs are not printed in silence mode, like the Calculating... message
		//Logs bypass this feature
		MathHelper.silentMode = true;
		
		//Send your custom message
		MathHelper.log("MathAPI has been enabled!"); 
		
		
		//EXAMPLE EVALUATIONS:
		String ansA = MathHelper.eval("1 + 1");
		MathHelper.log("Answer A: " + ansA); // 2
		
		
		String ansB = MathHelper.eval("(3 - 17) * (8 + 12)"); // -280
		MathHelper.log("Answer B: " + ansB);
	}

}

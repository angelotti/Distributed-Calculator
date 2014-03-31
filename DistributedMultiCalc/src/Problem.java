
public class Problem {
	private double a,b;
	private String operator;
	private double result;
	
	public Problem (double a, double b, String operator) {
		this.a = a;
		this.b = b;
		this.operator = operator;
		result = 0;
	}
	
	//returns the "problem" in string format, ready to send to client
	public String getExpression() {
		return operator+" "+a+" "+b;
	}
	
	//stores the result returned from the client
	public void setResult(double r) {
		result = r;
	}

}

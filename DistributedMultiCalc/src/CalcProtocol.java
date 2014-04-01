import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class CalcProtocol {
	
	private String error = null;
	private BufferedReader stdIn =
            new BufferedReader(new InputStreamReader(System.in));
	private double a,b,result;
	private String[] expression;
	
	public String getInput(){
		String fromUser=null;
		try {
			fromUser = stdIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return fromUser;
		}
	}
	public String processInput(String inputLine){
		expression = inputLine.split(" ");
		error = null;
		/*if(expression.length != 3){
			error = "Wrong number of arguments. We need 1 operator and 2 values!" ;
		}
		else if(!(expression[0].equals("+") ||  expression[0].equals("-") || 
				expression[0].equals("*") ||  expression[0].equals("/"))) {
			error = "Expected an operator in position 0" ;
		}
		else{*/
			try{
				 System.out.println(expression[1]+expression[0]+expression[2]);
				 a = Double.parseDouble(expression[1]);
				 b = Double.parseDouble(expression[2]);
				 result = calculate();				 
			}
			catch(Exception e){
				error = "You didnt passed any numbers";				
			}
			finally{
				return error;
			}
			
		//}
		//return error;
	}
	
	
	public int terminate(int counter){
		boolean t;
		int i = 0;
		System.out.println("You solved "+counter+" problems so far\n"
						+  "Continue? 'y' or 'n' :");
		while(true){
			String in = getInput();
			if(in.equals("y")){
				Scanner scan = new Scanner(System.in);
				System.out.println("How many problems this time:");
				i = scan.nextInt();
				t = false;
				break;
			}
			else if(in.equals("n")){
				t = true;
				break;
			}
			else{
				System.out.println("Type 'y' or 'n'");
			}
		}
		return i;
	}
	
	public double calculate() {
					
			//the parsing is done inside every if so that the server don't crashes to incorrect input				
			if( expression[0].equals("+")){
				return a = a+b;
			}
			else if( expression[0].equals("-")){
				return a = a-b;
			}
			else if( expression[0].equals("*")){
				return a = a*b;
			}
			else {
				return a = a/b;
			}
	}
	

	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}

}

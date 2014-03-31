import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class CalcMultiServerThread extends Thread{
	private Socket socket = null;
	private Problem p;
	
	public CalcMultiServerThread(Socket socket, int n, Problem p){
		super("CalcMultiServerThread, Thread "+n);
		this.socket = socket;
		this.p = p;
	}
	
    public void run() {
    	 
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        )  {
        	double a,b;
			String msg = in.readLine();
			while(!msg.equals("CLOSE")){
				String[] expression = msg.split(" ");
				
				//the parsing is done inside every if so that the server don't crashes to incorrect input				
				if( expression[0].equals("+")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a+b;
					out.println("Result = "+a);
				}
				else if( expression[0].equals("-")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a-b;
					out.println("Result = "+a);
				}
				else if( expression[0].equals("*")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a*b;
					out.println("Result = "+a);
				}
				else if( expression[0].equals("/")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a/b;
					out.println("Result = "+a);
				}
				else{
					out.println("Wrong format! Try again");
				}
				
				msg = in.readLine();
			}
			socket.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

}

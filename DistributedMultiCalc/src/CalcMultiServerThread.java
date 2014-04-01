import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class CalcMultiServerThread extends Thread{
	private Socket socket = null;
	private Problem p;
	private CalcMultiServer cms;
	private int index = -1;
	private String[] expression;
	
	public CalcMultiServerThread(Socket socket, int n, CalcMultiServer cms){
		super("CalcMultiServerThread, Thread "+n);
		this.socket = socket;
		this.cms = cms;
		index = cms.getC();
	}
	
    public void run() {
    	 
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    								socket.getInputStream()));
        )  {
        	String fromUser = "";
        	int t = 1;
        	while(t > 0) {
        		if(cms.getC()== cms.SIZE) {
        			System.out.println("\nTHE END");
        			cms.testResult();
        			break;
        		} else {
        			assignProblem();
        			out.println(p.getExpression());
        			System.out.println("i'm in "+p.getExpression());
        			fromUser = in.readLine();
        			System.out.println(fromUser);
        			
        			expression = fromUser.split(" ");
        			if(expression.length == 1) {
        				p.setResult(Double.parseDouble(expression[0]));
        				System.out.println(""+p.getResult());
        				//t-- ;
        			} else {
        				p.setResult(Double.parseDouble(expression[0]));
        				t = Integer.parseInt(expression[1]);
        			}
        		}
        	}
        	
			socket.close();
        } catch (IOException e) {
        	e.printStackTrace();
        } 
    }
    
    
    public void assignProblem(){
    	index = cms.increment();
    	System.out.println("************"+index);
    	p = cms.getProblem(index);
    }

}

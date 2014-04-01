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
        	if(cms.getC()==cms.SIZE){
        		System.out.println("There no problems");
        		
        	} else {
        		String fromUser = "";
        		while(cms.getC()+1 < cms.SIZE) {
        			assignProblem();
        			out.println(p.getExpression());
        			System.out.println("i'm in "+p.getExpression());
        			fromUser = in.readLine();
        			System.out.println(fromUser);
        			p.setResult(Double.parseDouble(fromUser));
        			
        			sleep(0);
        		}
        		cms.testResult();

        	}
			//socket.close();
        } catch (IOException | InterruptedException e) {
        	e.printStackTrace();
        } 
    }
    
    
    public void assignProblem(){
    	index = cms.increment();
    	System.out.println("************"+index);
    	p = cms.getProblem(index);
    }

}

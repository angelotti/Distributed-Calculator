import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class CalcClient {
	
	public static void main(String[] args)  throws IOException {

		boolean terminate = false;
		double reuslt = 0;
		int counter = 0;
		int t = 1;

		if (args.length != 1) {
			System.err.println(
					"Usage: java CalcClient  <port number>");
			System.exit(1);
		}

		InetAddress host = InetAddress.getLocalHost();
		int portNumber = Integer.parseInt(args[0]);


		String error,fromUser, fromServer;
		CalcProtocol cp = new CalcProtocol();

		try(	Socket cSocket = new Socket(host, portNumber);
				PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
		) {
			System.out.println("\n***Welcome to Distributed Calculator!***\n"
					+ " Ready to do some math?\n\n"
					+ "Press any key to continue..\n ");
			
			
			fromServer = "";
			System.out.println(""+fromServer);
			while (fromServer != null && t > 0) {
				fromServer = in.readLine();
				System.out.println(counter+" : "+fromServer);
				cp.processInput(fromServer);
				//out.println(""+cp.getResult()); //send result to server
				//counter++;
				t--;
				
				if(t == 0 ) { 	
					counter++;
					t = cp.terminate(counter); 
						//an t==0 o xrhsths den thelei na sunexisei
					out.println(cp.getResult()+" "+t);
					
				} else {
					counter++;
					out.println(""+cp.getResult()); 
				}
			}
			System.out.println(""+fromServer);
			if(fromServer == null){
				System.out.println("Congrats there are no more problems to solve");
			}
			else {
				out.println("end");
				System.out.println("That's all??? Anyway Thanks for the help!");
			}
			
		} catch (UnknownHostException e) {
	        System.err.println("Don't know about host " + host);
	        System.exit(1);
	    } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to " +
	            host);
	        System.exit(1);
	    }

	}

}

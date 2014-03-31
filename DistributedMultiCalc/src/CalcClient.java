import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class CalcClient {
	
	public static void main(String[] args)  throws IOException {

		boolean terminate = false;

		if (args.length != 1) {
			System.err.println(
					"Usage: java CalcClient  <port number>");
			System.exit(1);
		}

		InetAddress host = InetAddress.getLocalHost();
		int portNumber = Integer.parseInt(args[0]);


		String error,fromUser;
		//CalcProtocol cp = new CalcProtocol();

		try(	Socket cSocket = new Socket(host, portNumber);
				PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
		) {
			
		}

	}

}

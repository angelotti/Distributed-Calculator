import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Random;


public class CalcMultiServer {
	private ArrayList<Problem> ProblemList = new ArrayList<Problem>();
	private double testResult = 0;
	private int c = 0;

	public static void main(String[] args) throws IOException{
		
		
		if (args.length != 1) {
			System.err.println("Usage: java CalcMultiServer <port number>");
			System.exit(1);
		}
		
		CalcMultiServer cms = new CalcMultiServer();
		cms.initializeProblemList();
		
		int threadNumber = 0;
		int portNumber = Integer.parseInt(args[0]);
		boolean listening = true;
		
		try(ServerSocket serverSocket = new ServerSocket(portNumber)) {
			System.out.println("Server started");
			while(listening){
				cms.assignProblem(serverSocket, threadNumber);
				threadNumber++;
				System.out.println("Thread number: " + threadNumber);
			}
		} catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
	}
	
	//initializes ProblemList with random Problems
	public void initializeProblemList(){
		Random randomGenerator = new Random();
	    for (int idx = 0; idx < 2000; ++idx){
	      double randomA = randomGenerator.nextInt(100);
	      double randomB = randomGenerator.nextInt(100);
	      int randomOperator = randomGenerator.nextInt(4);
	      
	      if(randomOperator == 0){
	    	  ProblemList.add(new Problem(randomA, randomB, "+"));
	    	  testResult = testResult + (randomA+randomB);
	      }
	      else if(randomOperator == 1) {
	    	  ProblemList.add(new Problem(randomA, randomB, "-"));
	    	  testResult = testResult + (randomA-randomB);
	      }
	      else if(randomOperator == 2) {
	    	  ProblemList.add(new Problem(randomA, randomB, "*"));
	    	  testResult = testResult + (randomA*randomB);
	      }
	      else {
	    	  ProblemList.add(new Problem(randomA, randomB, "/"));
	    	  testResult = testResult + (randomA/randomB);
	      }
	    }   
	}
	
	public void assignProblem(ServerSocket serverSocket, int threadNumber) throws IOException {
		new CalcMultiServerThread(serverSocket.accept(), threadNumber,  ProblemList.get(c)).start();
		threadNumber++;
		increment();		
	}
	
	public synchronized void increment() {
        c++;
    }

}

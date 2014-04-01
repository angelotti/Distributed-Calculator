import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Random;


public class CalcMultiServer {
	private ArrayList<Problem> ProblemList = new ArrayList<Problem>();
	private double testResult = 0;
	private int c = 0;
	
	final int SIZE = 1000;
	private boolean listening = true;

	public static void main(String[] args) throws IOException{
		
		
		if (args.length != 1) {
			System.err.println("Usage: java CalcMultiServer <port number>");
			System.exit(1);
		}
		
		CalcMultiServer cms = new CalcMultiServer();
		cms.initializeProblemList();
		
		int threadNumber = 0;
		int portNumber = Integer.parseInt(args[0]);
		
		
		try(ServerSocket serverSocket = new ServerSocket(portNumber)) {
			System.out.println("Server started");
			while(cms.listening){
				new CalcMultiServerThread(serverSocket.accept(), threadNumber, cms).start();
				threadNumber++;
				System.out.println("Active Threads: " + Thread.activeCount());
			}
			
		} catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
		
		
	}
	
	//initializes ProblemList with random Problems
	public void initializeProblemList(){
		Random randomGenerator = new Random();
	    for (int idx = 0; idx < SIZE; ++idx){
	      double randomA = randomGenerator.nextInt(10);
	      double randomB = randomGenerator.nextInt(10);
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
	    System.out.println("Problem Initialized\n");
	}
	
	
	
	public synchronized int increment() {
			return c++;
    }
	public int getC() {
		return c;
	}
	
	public Problem getProblem(int index) {
		return ProblemList.get(index);
	}
	
	public void testResult() {
		double sum = 0;
		for(Problem p : ProblemList){
			sum = sum + p.getResult();
		}
		
		if(testResult == sum){
			System.out.println(testResult+" = "+sum+"\nAll calculations done correctly!");
		}
	}
	public void setListening(boolean l){
		listening = l;
	}

}

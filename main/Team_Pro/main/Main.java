package Team_Pro.main;

import java.util.Scanner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;


public class Main {
	private static Scanner keyboard;
	private static Server server;
	private static int looper;
	public static void main(String[] args) throws Exception {
		startUp();
		
		// Wait for the user to type "quit"
		System.out.println("Web server started, type help for more options");
		keyboard = new Scanner(System.in);
		new java.util.Timer().schedule(

			    new java.util.TimerTask() {
			        @Override
			        public void run() {
			        	System.out.println("Saved: " + new java.util.Date().toString());
			        }
			    }, 
			    0, 300000
			);
		while (looper == 0) {
			loopme();
		}
		
		System.out.println("Shutting down...");
		shutDown();
		System.out.println("Server has shut down, exiting");
		return;
	}
	
	private static void startUp() throws Exception{
		server = new Server(8081);
		WebAppContext handler = new WebAppContext();
		handler.setContextPath("/anonymouscomments");
		handler.setWar("./war"); // web app is in the war directory of the project
		server.setHandler(handler);
		
		// Use 20 threads to handle requests
		server.setThreadPool(new QueuedThreadPool(20));
		
		// Start the server
		server.start();
	}
	
	private static void shutDown() throws Exception{
		server.stop();
		server.join();
	}
	
	private static void loopme() throws Exception{
		while (keyboard.hasNextLine() && looper == 0) {
			String line = keyboard.nextLine();
			if (line.trim().toLowerCase().equals("quit")) {
				looper = 1;
				break;
			}
			else if (line.trim().toLowerCase().equals("restart")) {
				System.out.println("Restarting server...");
				shutDown();
				startUp();
			}
			else if (line.trim().toLowerCase().equals("help")) {
				System.out.println("Type quit to exit");
				System.out.println("     backup to back up the database data");
				System.out.println("     restore to restore database data");
				System.out.println("     restart to restart the server");
				System.out.println("     stop to stop server");
				System.out.println("     start to start server");
			}
		}
	}
}

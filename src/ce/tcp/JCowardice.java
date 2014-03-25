package ce.tcp;

import java.io.*;
import java.net.*;

import JHeroic.JHeroicInterface;

/** Class containing a single thread, for a single connection
 * Not two threads for a full-duplex communication, because oh come on, what for */
public class JCowardice extends Thread {
	//int timeout; //time of idleness in ms before the server closes the connection
	JHeroicInterface executor;
	PrintWriter out;
	BufferedReader in;
	boolean working = true;
	
	//is it okay to concurrently share the same instance of JHeroicInterface?
	public JCowardice (Socket s, JHeroicInterface executor) throws IOException {
		this.out = new PrintWriter(s.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.executor = executor;
		//timeout = 60000;
	}
	
	@Override
	public void run(){
		StringBuilder bigBuff = new StringBuilder();
		String smallBuff;
		while (working) { //loop or a single request?
			try {
				do {
					smallBuff = in.readLine();
					bigBuff.append(smallBuff);
				} while (!smallBuff.contains("."));
			} catch (IOException e) {
				working = false;
			} /* noone really cares (atm) *//**(@ToDo)*/
			//do some stuff with the request
		}
	}
	
	public void shutDown() {
		working = false; /*this actually doesnt mean anything atm and in no way guarantees the termination of this thread*/
	}
}

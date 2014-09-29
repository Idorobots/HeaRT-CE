package ce.tcp;

import java.io.*;
import java.net.*;

import JHeroic.JHeroicInterface;

/** Class containing a single thread, for a single connection
 * Not two threads for a full-duplex communication, because oh come on, what for */
class JCowardice extends Thread {
	int timeout;
	JHeroicInterface executor;
    JCowardlyCallbackOnClose serverCallback;
	PrintWriter out;
	BufferedReader in;
	boolean working = true;
	
	//is it okay to concurrently share the same instance of JHeroicInterface?
	public JCowardice (Socket s, JHeroicInterface executor, JCowardlyCallbackOnClose serverCallback) throws IOException {
		this.out = new PrintWriter(s.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.executor = executor;
        this.serverCallback = serverCallback;
		this.timeout = 60000;
	}
	
	@Override
	public void run(){
        try {
            StringBuilder bigBuff = new StringBuilder();
            String smallBuff;
            while (working) {
                try {
                    do {
                        smallBuff = in.readLine();
                        bigBuff.append(smallBuff);
                        if ("".equals(smallBuff)) {
                            System.out.println("smallBuff is empty");
                            Thread.sleep(500);
                        }
                    } while (!smallBuff.contains("."));
                } catch (InterruptedException | IOException e) {
                    break;
                }

                //do some stuff with the request
                String response = executor.getProtocolVersion();
                bigBuff.append(" ");
                bigBuff.append(response);

                //send the response
                out.print(bigBuff.toString());
            }
        } finally {
            serverCallback.unregister(this);
        }
	}
	
	public void shutDown() {
		working = false;
        this.interrupt();
	}
}

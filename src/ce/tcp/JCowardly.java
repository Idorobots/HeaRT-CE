package ce.tcp;

import JHeroic.JHeroicInterface;

import java.io.IOException;
import java.net.*;
import java.util.*;

/** throws exceptions in various places because I like to live dangerously */
public class JCowardly implements JCowardlyInterface {
	List<JCowardice> connections;
	ServerSocket listener;
	JHeroicInterface executor;
	
	public JCowardly(int port, JHeroicInterface executor) throws IOException {
		this.connections = new LinkedList<>();
		this.listener = new ServerSocket(port);
		this.executor = executor;
		//mainLoop(); //call mainLoop in ctor or separately?
	}
	
	void mainLoop() throws IOException {
		while (true) //while what?
			connections.add(new JCowardice(this.listener.accept(), executor));
	}
}

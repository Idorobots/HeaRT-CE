package ce.tcp;

import JHeroic.JHeroicInterface;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class JCowardly implements JCowardlyInterface {
	List<Thread> connections;
	ServerSocket listener;
	JHeroicInterface executor;
	
	public JCowardly(int port, JHeroicInterface executor) throws IOException {
		this.connections = new LinkedList<>();
		this.listener = new ServerSocket(port);
		this.executor = executor;
	}
}

package ce.tcp;

import JHeroic.JHeroicInterface;

import java.io.IOException;
import java.net.*;
import java.util.*;

/** An implementation of JCowardlyInterface that should be used from only one thread
 * */
public class ThreadUnsafeJCowardly implements JCowardlyInterface {
    private volatile ServerState serverState;
	private List<JCowardice> connections;
	private ServerSocket listener;
	private JHeroicInterface worker;
    private Thread serverThread;
	
	public ThreadUnsafeJCowardly(JHeroicInterface worker) throws IOException {
        LoggingPlaceholder.info("Starting the JCowardly constructor");
		this.connections = new ArrayList<>();
		this.worker = worker;
        this.serverState = ServerState.FRESH;
        this.serverThread = new Thread(new Runnable(){
            @Override
            public void run() {
                acceptingLoop();
            }
        });
        LoggingPlaceholder.info("Finishing the JCowardly constructor");
	}

    @Override
    public void start(int port) throws IOException, IllegalStateException {
        if (this.serverState != ServerState.FRESH) {
            throw new IllegalStateException("This server seems to have already been successfully started");
        }
        this.listener = new ServerSocket(port);
        this.serverState = ServerState.STARTED;
        serverThread.start();
    }

    @Override
    public void softShutdown() throws IllegalStateException {
        if (this.serverState == ServerState.STOPPED) {
            throw new IllegalStateException("This server has already been stopped");
        }
        //This order (soft and then potentially stopped) is actually important, so that a server with no more threads doesn't linger as softshutdown due to race conditions
        this.serverState = ServerState.SOFTSHUTDOWN;
        if (this.connections.isEmpty())
            this.serverState = ServerState.STOPPED;
        try {
            this.listener.close();
        } catch (IOException e) {//todo
            e.printStackTrace();
        }
    }

    @Override
    public void forcedShutdown() {
        for (JCowardice con : connections) {
            con.shutDown();
        }
        this.serverState = ServerState.STOPPED;
        this.serverThread.interrupt();
    }

    @Override
    public boolean isFresh() {
        return this.serverState == ServerState.FRESH;
    }

    @Override
    public boolean isSoftlyShuttingDown() {
        return this.serverState == ServerState.SOFTSHUTDOWN;
    }

    @Override
    public boolean isStopped() {
        return this.serverState == ServerState.STOPPED;
    }

    @Override
    public void join() throws InterruptedException {
        serverThread.join();
    }

    @Override
    public void join(int milis) throws InterruptedException {
        serverThread.join(milis);
    }

    private void acceptingLoop() {
        while (this.serverState == ServerState.STARTED) {
            try {
                Socket s = this.listener.accept();
                JCowardice newCon = new JCowardice(s, worker, new JCowardlyCallbackOnClose() {
                    @Override
                    public void unregister(JCowardice connection) {
                        connections.remove(connection);
                        if (connections.isEmpty() && serverState == ServerState.SOFTSHUTDOWN)
                            serverState = ServerState.STOPPED;
                    }
                });
                connections.add(newCon);
                newCon.start();
            } catch (IOException e) {
                break;
            }
        }
    }
}

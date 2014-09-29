package ce.tcp;

import java.io.IOException;


public interface JCowardlyInterface {

    /** start() actually starts the server */
    public void start(int port) throws IOException, IllegalStateException;
    public void softShutdown() throws IllegalStateException;
    public void forcedShutdown();
    public boolean isFresh();
    public boolean isSoftlyShuttingDown();
    public boolean isStopped();
    public void join() throws InterruptedException;
    public void join(int milis) throws InterruptedException;
}

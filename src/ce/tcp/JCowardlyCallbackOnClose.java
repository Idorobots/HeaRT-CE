package ce.tcp;

/**
 * A callback for closing connections (a connection can remove itself from the server's list)
 */
public interface JCowardlyCallbackOnClose {

    public void unregister (JCowardice connection);
}

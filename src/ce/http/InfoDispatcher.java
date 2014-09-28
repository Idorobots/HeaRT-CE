package ce.http;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import JHeroic.*;

@Path("/info")
public class InfoDispatcher {
    // /API_VERSION/info/protocol-version/ - returns the protocol version used by this server.
    @GET
    @Path("/protocol-version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProtocolVersion() throws Exception {
        return JHeroicInterface.protocolVersion;
    }

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

@Path("/heart")
public class HeaRTDispatcher {
    static JHeroicInterface heart = new JHeroic();

    @GET
    @Path("/protocol-version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProtocolVersion() throws Exception {
        return JHeroicInterface.protocolVersion;

    @POST
    @Path("/run/{type}/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
        public String runInference(@PathParam("type") String infType,
                                   @PathParam("model") String modelName,
                                   @PathParam("user") String userName,
                                   String data) throws Exception {
        String[] tableState = data.split("\\n");

        return heart.runInference(userName, modelName, infType, tableState[0], tableState[1]);
    }
}

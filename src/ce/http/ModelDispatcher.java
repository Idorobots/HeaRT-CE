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

@Path("/model")
public class ModelDispatcher {

    @GET
    @Path("/getlist")
    @Produces(MediaType.TEXT_PLAIN)
    public String getListPlain() {
        return "[].";
    }

    @GET
    @Path("/getlist")
    @Produces(MediaType.APPLICATION_XML)
    public String getListXML() {
        return "<?xml version=\"1.0\"?>" + "<result>[].</result>";
    }

    @GET
    @Path("/getlist")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListJSON() {
        return "{\"result\" : \"[].\"}";
    }

    @POST
    @Path("/add/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addModel(@PathParam("model") String modelName, @PathParam("user") String user, String data) {
        return "[false, " + data.toString() +"].";
    }
}

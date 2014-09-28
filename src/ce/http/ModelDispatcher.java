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

@Path("/model")
public class ModelDispatcher {
    static JHeroicInterface heart = new JHeroic();

    @GET
    @Path("/get-list")
    @Produces(MediaType.TEXT_PLAIN)
    public String getModelList() throws Exception {
        String out = "[";

        for(JHModel m : heart.getModelList()) {
            out += m.toString() + ",";
        }

        return out.substring(0, out.lastIndexOf(",")) + "]."; // Remove last coma.
    }

    @GET
    @Path("/verify/{mode}/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String verifyModel(@PathParam("model") String modelName,
                              @PathParam("user") String userName,
                              @PathParam("mode") Integer mode) throws Exception {
        return heart.verifyModel(userName, modelName, mode.intValue());
    }

    @GET
    @Path("/get/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserModel(@PathParam("model") String modelName,
                               @PathParam("user") String userName) throws Exception {
        return heart.getUserModel(userName, modelName);
    }

    @POST
    @Path("/add/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addUserModel(@PathParam("model") String modelName,
                               @PathParam("user") String userName,
                               String data) throws Exception {
        return heart.addUserModel(userName, modelName, data);
    }

    @DELETE
    @Path("/remove/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeUserModel(@PathParam("model") String modelName,
                                  @PathParam("user") String userName) throws Exception {
        return heart.removeUserModel(userName, ModelName);
    }

    @POST
    @Path("/add-state/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addStateToModel(@PathParam("model") String modelName,
                                  @PathParam("user") String userName,
                                  String data) throws Exception {
        String[] nameDef = data.split("\\n");
        return heart.addStateToModel(userName, modelName, nameDef[0], nameDef[1]);
    }

    @POST
    @Path("/remove-state/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeStateFromModel(@PathParam("model") String modelName,
                                       @PathParam("user") String userName) throws Exception {
        return heart.removeStateFromModel(userName, ModelName, data);
    }

}

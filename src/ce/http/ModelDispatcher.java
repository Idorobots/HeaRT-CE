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
    static JHeroicInterface heart = new JHeroic(1, "localhost", 8090);

    // /API_VERSION/model/list/ - lists all models.
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_PLAIN)
    public String getModelList() throws Exception {
        String out = "[";

        for(JHModel m : heart.getModelList()) {
            out += m.getReplyString() + ",";
        }

        return out.substring(0, out.lastIndexOf(",")) + "]."; // Remove last coma.
    }

    // /API_VERSION/model/verify/ - verifies a model.
    @GET
    @Path("/verify/{mode}/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String verifyModel(@PathParam("model") String modelName,
                              @PathParam("user") String userName,
                              @PathParam("mode") Integer mode) throws Exception {
        return heart.verifyModel(userName, modelName, mode.intValue());
    }

    // /API_VERSION/model/run/ - runs a model.
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

    // /API_VERSION/model/definition/ - modifies model definitions.
    // /API_VERSION/model/state/ - modifies model states.
    @GET
    @Path("/definition/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserModel(@PathParam("model") String modelName,
                               @PathParam("user") String userName) throws Exception {
        return heart.getUserModel(userName, modelName);
    }

    @POST
    @Path("/definition/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addUserModel(@PathParam("model") String modelName,
                               @PathParam("user") String userName,
                               String data) throws Exception {
        return heart.addUserModel(userName, modelName, data);
    }

    @DELETE
    @Path("/definition/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeUserModel(@PathParam("model") String modelName,
                                  @PathParam("user") String userName) throws Exception {
        return heart.removeUserModel(userName, ModelName);
    }

    @POST
    @Path("/state/{state}/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addStateToModel(@PathParam("model") String modelName,
                                  @PathParam("user") String userName,
                                  @PathParam("state") String stateName,
                                  String data) throws Exception {
        return heart.addStateToModel(userName, modelName, stateName, data);
    }

    @DELETE
    @Path("/state/{state}/{model}/{user}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeStateFromModel(@PathParam("model") String modelName,
                                       @PathParam("user") String userName,
                                       @PathParam("state") String stateName) throws Exception {
        return heart.removeStateFromModel(userName, ModelName, stateName);
    }

}

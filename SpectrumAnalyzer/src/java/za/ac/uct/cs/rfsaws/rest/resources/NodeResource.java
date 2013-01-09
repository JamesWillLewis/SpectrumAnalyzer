/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.rest.resources;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.ac.uct.cs.rfsaws.ejb.LeaseFacade;
import za.ac.uct.cs.rfsaws.entities.LeaseEntity;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("node")
@Stateless
/**
 * Provides general Node REST functions via HTTP requests.
 */
public class NodeResource {

    @Context
    private UriInfo context;
    @EJB
    private LeaseFacade leaseFacade;

    /**
     * Creates a new instance of NodeService
     */
    public NodeResource() {
    }

    /**
     * Print-out list of Node web services available.
     *
     * @return List of web resources/functions.
     */
    @GET
    @Produces("text/html")
    public Response listPaths() {
        String htmlResponse = "<!DOCTYPE html><html><head><title>NODE web services</title></head><body><p>";

        //listing of web services
        htmlResponse += context.getAbsolutePath() + "<br/>";
        htmlResponse += context.getAbsolutePath() + "/list_leases_all" + "<br/>";
        htmlResponse += context.getAbsolutePath() + "/primary/{id}" + "<br/>";
        htmlResponse += context.getAbsolutePath() + "/primary/{id}/submit_allocation" + "<br/>";
        htmlResponse += context.getAbsolutePath() + "/primary/{id}/submit_auction";

        htmlResponse += "</p></body></html>";

        Response.ResponseBuilder r = Response.ok(htmlResponse, MediaType.TEXT_HTML);

        return r.build();
    }

    /**
     * Retrieve list of leases.
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("list_leases_all")
    @Produces("application/json")
    public List<LeaseEntity> listLeases() {
        return leaseFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of NodeService
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
    }
}

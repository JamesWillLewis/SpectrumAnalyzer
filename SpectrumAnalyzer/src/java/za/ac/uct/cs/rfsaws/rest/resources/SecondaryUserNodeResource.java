/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.rest.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import za.ac.uct.cs.rfsaws.ejb.SecondaryUserNodeFacade;
import za.ac.uct.cs.rfsaws.entities.PrimaryUserNodeEntity;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("node/secondary")
@Stateless
public class SecondaryUserNodeResource {

    @Context
    private UriInfo context;
    
    @EJB
    private SecondaryUserNodeFacade nodeFacade;

    /**
     * Creates a new instance of SecondaryUserNodeService
     */
    public SecondaryUserNodeResource() {
    }

    /**
     * Retrieves representation of an instance of za.ac.uct.cs.rfsaws.web.services.SecondaryUserNodeService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes("application/json")
    @Path("{nodeid}/submit_bid")
    public void submitBid(PrimaryUserNodeEntity content) {
        System.out.println(content.toString());
    }
}

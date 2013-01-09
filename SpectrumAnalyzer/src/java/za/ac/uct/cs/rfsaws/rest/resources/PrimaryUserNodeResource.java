/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.rest.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import za.ac.uct.cs.rfsaws.ejb.PrimaryUserNodeFacade;
import za.ac.uct.cs.rfsaws.entities.PrimaryUserNodeEntity;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("node/primary")
@Stateless
public class PrimaryUserNodeResource {

    @Context
    private UriInfo context;
    @EJB
    private PrimaryUserNodeFacade nodeFacade;

    /**
     * Creates a new instance of PrimaryUseService
     */
    public PrimaryUserNodeResource() {
    }

    /**
     * Retrieves representation of an instance of
     * za.ac.uct.cs.rfsaws.web.services.PrimaryUseService
     *
     * @return an instance of za.ac.uct.cs.rfsaws.entities.PrimaryUserNodeEntity
     */
    @GET
    @Produces("application/json")
    @Path("{nodeid}")
    public PrimaryUserNodeEntity queryNode(@PathParam("nodeid") Long id) {
        return nodeFacade.find(id);
    }

    /**
     * PUT method for updating or creating an instance of PrimaryUseService
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/json")
    @Path("{nodeid}/submit_allocation")
    public void submitAllocation(PrimaryUserNodeEntity content) {
        System.out.println(content.toString());
    }

    @POST
    @Consumes("application/json")
    @Path("{nodeid}/submit_auction")
    public void submitAuction(){
     
    }
    
    @POST
    @Consumes("application/json")
    @Path("{nodeid}/reclaim_allocation")
    public void reclaimAllocation(PrimaryUserNodeEntity content) {
        System.out.println(content.toString());
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.web.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import za.ac.uct.cs.rfsaws.ejb.PrimaryUserNodeFacade;
import za.ac.uct.cs.rfsaws.entities.PrimaryUserNode;
import za.ac.uct.cs.rfsaws.web.json.AuctionJSON;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("node/primary/{nodeid}")
@Stateless
public class PrimaryUserNodeService {

    @Context
    private UriInfo context;
    @EJB
    private PrimaryUserNodeFacade nodeFacade;

    /**
     * Creates a new instance of PrimaryUseService
     */
    public PrimaryUserNodeService() {
    }

    /**
     * Retrieves representation of an instance of
     * za.ac.uct.cs.rfsaws.web.services.PrimaryUseService
     *
     * @return an instance of za.ac.uct.cs.rfsaws.entities.PrimaryUserNode
     */
    @GET
    @Produces("application/json")
    public PrimaryUserNode getJson(@PathParam("nodeid") Long id) {
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
    @Path("submit_allocation")
    public void submitAllocation(PrimaryUserNode content) {
        System.out.println(content.toString());
    }

    @GET
    @Produces("application/json")
    @Path("submit_auction")
    public AuctionJSON submitAuction(){
        return new AuctionJSON();
    }
}

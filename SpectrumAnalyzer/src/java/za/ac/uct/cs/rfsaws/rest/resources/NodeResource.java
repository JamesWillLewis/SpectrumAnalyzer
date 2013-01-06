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
import za.ac.uct.cs.rfsaws.ejb.LeaseFacade;
import za.ac.uct.cs.rfsaws.entities.LeaseEntity;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("node/")
@Stateless
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
     * Retrieves representation of an instance of za.ac.uct.cs.rfsaws.web.services.NodeService
     * @return an instance of java.lang.String
     */
    @GET
    @Path("list_leases")
    @Produces("application/json")
    public List<LeaseEntity> listLeases() {
        return leaseFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of NodeService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
    }
}

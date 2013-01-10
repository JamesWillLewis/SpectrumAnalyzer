package za.ac.uct.cs.rfsaws.rest.resources;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import za.ac.uct.cs.rfsaws.ejb.AuctionFacade;
import za.ac.uct.cs.rfsaws.ejb.LeaseFacade;
import za.ac.uct.cs.rfsaws.entities.AuctionEntity;
import za.ac.uct.cs.rfsaws.entities.LeaseEntity;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("node")
@Stateless
public class NodeResource {

    @Context
    private UriInfo context;
    @EJB
    private LeaseFacade leaseFacade;
    @EJB
    private AuctionFacade auctionFacade;
    @PersistenceContext
    private EntityManager em;

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
    @Produces(MediaType.TEXT_HTML)
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<LeaseEntity> listLeases() {
        return leaseFacade.findAll();
    }

    /**
     * Retrieve list of leases.
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("list_leases_node")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LeaseEntity> listLeasesNode(@QueryParam("node") Long nodeID) {
        return em.createNamedQuery("findLeasesOfNode").setParameter("id", nodeID).getResultList();
    }

    @GET
    @Path("list_current_auctions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AuctionEntity> listAuctionsActive() {
        return em.createNamedQuery("findActiveAuctions").getResultList();
    }
}

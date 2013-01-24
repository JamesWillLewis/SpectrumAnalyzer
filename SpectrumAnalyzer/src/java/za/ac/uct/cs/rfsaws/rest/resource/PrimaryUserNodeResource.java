package za.ac.uct.cs.rfsaws.rest.resource;

import java.util.Calendar;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import za.ac.uct.cs.rfsaws.ejb.AllocationFacade;
import za.ac.uct.cs.rfsaws.ejb.AuctionFacade;
import za.ac.uct.cs.rfsaws.ejb.PrimaryUserNodeFacade;
import za.ac.uct.cs.rfsaws.entity.AllocationEntity;
import za.ac.uct.cs.rfsaws.entity.AuctionEntity;
import za.ac.uct.cs.rfsaws.entity.PrimaryUserNodeEntity;
import za.ac.uct.cs.rfsaws.rest.xml.AllocationXML;
import za.ac.uct.cs.rfsaws.rest.xml.AuctionXML;



/**
 * REST Web Service
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Path("node/primary")
@Stateless
public class PrimaryUserNodeResource {

    @Context
    private UriInfo context;
    @EJB
    private PrimaryUserNodeFacade nodeFacade;
    @EJB
    private AuctionFacade auctionFacade;
    @EJB
    private AllocationFacade allocationFacade;

    /**
     * Creates a new instance of PrimaryUseService
     */
    public PrimaryUserNodeResource() {
    }

    /**
     * Retrieves representation of an instance of PrimaryUseService
     *
     * @return an instance of PrimaryUserNodeEntity
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nodeid}/submit_allocation")
    public String submitAllocation(@PathParam("nodeid") Long id, AllocationXML allocationBean) {
        PrimaryUserNodeEntity pu = nodeFacade.find(id);
        AllocationEntity allocationEntity = new AllocationEntity();

        allocationEntity.setBandFreqLower(allocationBean.getLowerBound());
        allocationEntity.setBandFreqUpper(allocationBean.getUpperBound());
        allocationEntity.setBeginDate(allocationBean.getStartDate());
        allocationEntity.setEndDate(allocationBean.getEndDate());
        allocationEntity.setPowerConstraint(allocationBean.getPowerConstraint());
        allocationEntity.setPrimaryUser(pu);

        allocationFacade.create(allocationEntity);

        return String.valueOf(allocationEntity.getId());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nodeid}/submit_auction")
    public String submitAuction(@PathParam("nodeid") Long id, AuctionXML auctionBean) {
        PrimaryUserNodeEntity pu = nodeFacade.find(id);
        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setAllocation(allocationFacade.find(auctionBean.getAllocationID()));
        auctionEntity.setAuctionEnd(auctionBean.getEndDate());
        auctionEntity.setAuctionStart(auctionBean.getStartDate());
        auctionEntity.setResolved((byte) 0);

        auctionFacade.create(auctionEntity);

        return String.valueOf(auctionEntity.getId());
    }

    @GET
    @Path("{nodeid}/reclaim_allocation")
    public String reclaimAllocation(@PathParam("nodeid") Long id, @QueryParam(value = "alloc") Long allocID) {
        AllocationEntity allocationEntity = allocationFacade.find(allocID);
        if (allocationEntity != null) {
            allocationEntity.setEndDate(Calendar.getInstance().getTime());
            allocationFacade.edit(allocationEntity);
            return "SUCCESS";
        } else {
            return "FAIL - ALLOCATION ID=" + allocID + " DOES NOT EXIST";
        }
    }
}

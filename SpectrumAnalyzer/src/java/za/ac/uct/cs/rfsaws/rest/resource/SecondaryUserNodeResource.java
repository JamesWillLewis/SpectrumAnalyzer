
package za.ac.uct.cs.rfsaws.rest.resource;

import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import za.ac.uct.cs.rfsaws.ejb.AuctionFacade;
import za.ac.uct.cs.rfsaws.ejb.SecondaryUserNodeFacade;
import za.ac.uct.cs.rfsaws.ejb.SegmentFacade;
import za.ac.uct.cs.rfsaws.entity.AuctionEntity;
import za.ac.uct.cs.rfsaws.entity.BidEntity;
import za.ac.uct.cs.rfsaws.entity.SecondaryUserNodeEntity;
import za.ac.uct.cs.rfsaws.rest.xml.BidXML;



/**
 * REST Web Service
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Path("node/secondary")
@Stateless
public class SecondaryUserNodeResource {

    @Context
    private UriInfo context;
    @EJB
    private SecondaryUserNodeFacade nodeFacade;
    @EJB
    private AuctionFacade auctionFacade;
    @EJB
    private SegmentFacade segmentFacade;

    /**
     * Creates a new instance of SecondaryUserNodeService
     */
    public SecondaryUserNodeResource() {
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nodeid}/submit_bid")
    public String submitBid(@PathParam("nodeid") Long id, BidXML bidBean) {
        SecondaryUserNodeEntity su = nodeFacade.find(id);
        AuctionEntity targetAuction = auctionFacade.find(bidBean.getAuctionID());
        Date now = Calendar.getInstance().getTime();
        if (now.after(targetAuction.getAuctionStart()) && now.before(targetAuction.getAuctionEnd())) {
            BidEntity newBid = new BidEntity();
            newBid.setAuction(targetAuction);
            newBid.setBidValue(bidBean.getValue());
            newBid.setBidder(su);
            newBid.setSegment(segmentFacade.find(bidBean.getSegmentID()));
            return "SUCCESS: Bid has been placed.";
        } else {
            return "ERROR: Auction has already closed";
        }
    }
}

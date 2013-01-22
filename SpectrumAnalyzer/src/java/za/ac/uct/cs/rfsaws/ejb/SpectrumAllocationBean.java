package za.ac.uct.cs.rfsaws.ejb;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.AuctionEntity;
import za.ac.uct.cs.rfsaws.entity.BidEntity;
import za.ac.uct.cs.rfsaws.entity.LeaseEntity;
import za.ac.uct.cs.rfsaws.util.KnapsackAlgorithm;

/**
 * Stateless session bean which provides business methods for spectrum
 * auctioning and allocation.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class SpectrumAllocationBean {

    /**
     * Provides persistence functionality.
     */
    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;
    /**
     * Injected Lease Facade bean.
     */
    @EJB
    private LeaseFacade leaseFacade;

    /**
     * Performs an optimal lease allocation, using a Knapsack algorithm
     * implementation, for a specified auction. All bids associated with this
     * auction are queried from the database, and then a list of optimum leases
     * is selected - a lease allocated for each winning bid, where the winning
     * bids are selected as to maximize profit earned from the spectrum auction.
     * Each lease is also allocated the exact frequency band in the spectrum, in
     * random order.
     *
     * @param a Auction which is to be allocated leases
     * @return List of new allocated leases
     */
    private List<LeaseEntity> allocate(AuctionEntity a) {
        List<BidEntity> bids = em.createNamedQuery("findBidsOfAuction").setParameter("a", a).getResultList();
        System.out.println("====[" + bids.size() + " bids found for auction id=" + a.getId() + "]====");
        double capacity = a.getAllocation().getBandFreqUpper() - a.getAllocation().getBandFreqLower();

        List<BidEntity> winningBids = new LinkedList<BidEntity>();
        if (bids.size() > 0) {
            KnapsackAlgorithm knapsackAlgorithm = new KnapsackAlgorithm(bids, capacity, a.getId());
            winningBids = knapsackAlgorithm.bestFirstBranchAndBound();
        } else {
            System.out.println("====[Auction id=" + a.getId() + " had no bids - terminated]====");
        }

        //mark auction as resolved using flag
        a.setResolved((byte) 1);
        em.persist(a);
        System.out.println("====[Auction id=" + a.getId() + " has been resolved]====");

        List<LeaseEntity> leases = new LinkedList<LeaseEntity>();
        double spectrumLowerBound = a.getAllocation().getBandFreqLower();

        //order the winning bids randomly, 
        Collections.shuffle(winningBids);

        //create lease for eaching winning bid
        for (BidEntity b : winningBids) {
            LeaseEntity l = new LeaseEntity();
            l.setAllocation(a.getAllocation());
            l.setBandFreqLower(spectrumLowerBound);
            spectrumLowerBound += b.getSegment().getBandWidth();
            l.setBandFreqUpper(spectrumLowerBound);
            l.setDateEnd(a.getAllocation().getEndDate());
            l.setDateStart(a.getAllocation().getBeginDate());
            l.setNode(b.getBidder());
            l.setSegment(b.getSegment());
            leases.add(l);
        }
        System.out.println("====[" + leases.size() + " new leases allocated]====");

        return leases;
    }

    /**
     * Searches for closed, unresolved auctions, and resolves them.
     */
    public void checkAuctions() {
        resolve(findUnresolvedAuctions());
    }

    /**
     * Search for all unresolved Auctions - any auction which has closed (passed
     * it's end time) and hasn't been allocated.
     *
     * @return List of unresolved Auctions.
     */
    private List<AuctionEntity> findUnresolvedAuctions() {
        return em.createNamedQuery("findUnresolvedAuctions").getResultList();
    }

    /**
     * Performs an allocation on each specified auction.
     *
     * @param auctions List of auctions to allocate.
     */
    private void resolve(List<AuctionEntity> auctions) {
        System.out.println("====[" + auctions.size() + " unresolved auctions found]====");
        for (AuctionEntity a : auctions) {
            System.out.println("====[Resolving auction ID=" + a.getId() + "]====");

            List<LeaseEntity> leases = allocate(a);

            if (leases != null) {
                for (LeaseEntity l : leases) {
                    leaseFacade.create(l);
                }
            }
        }
    }
}

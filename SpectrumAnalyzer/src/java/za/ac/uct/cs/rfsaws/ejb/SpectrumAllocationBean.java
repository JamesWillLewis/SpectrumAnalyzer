/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class SpectrumAllocationBean {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;
    
    @EJB 
    private LeaseFacade leaseFacade;

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

        a.setResolved((byte) 1);
        em.persist(a);
        System.out.println("====[Auction id=" + a.getId() + " has been resolved]====");

        List<LeaseEntity> leases = new LinkedList<LeaseEntity>();
        double spectrumLowerBound = a.getAllocation().getBandFreqLower();

        Collections.shuffle(bids);

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
    
    public void checkAuctions(){
        resolve(findUnresolvedAuctions());
    }
    
    
    /**
     * Search for all unresolved Auctions - any auction which has expired
     * (passed it's end time) and hasn't been allocated.
     *
     * @return List of unresolved Auctions.
     */
    private List<AuctionEntity> findUnresolvedAuctions() {
        return em.createNamedQuery("findUnresolvedAuctions").getResultList();
    }

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

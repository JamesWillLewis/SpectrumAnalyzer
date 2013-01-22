package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.AuctionEntity;

/**
 * Session bean face which provides Create/Retrieve/Update/Destroy
 * functions for an Auction persistence entity.
 * Incorporates a JPA persistence context for entity management. 
 * 
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class AuctionFacade extends AbstractFacade<AuctionEntity> {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;
    @EJB
    private TimerBean broker;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Persist new auction, and start a timer.
     *
     * @param entity AuctionEntity to create.
     */
    @Override
    public void create(AuctionEntity entity) {
        broker.pushNewTimer(entity.getAuctionEnd(), "auction-timer(ID=" + entity.getId() + ")");
        super.create(entity);
    }

    /**
     * Update existing auction, and start a new timer. NOTE: For test purposes
     * only. Auctions should never be altered after creation.
     *
     * @param entity AuctionEntity to update.
     */
    @Override
    public void edit(AuctionEntity entity) {
        broker.pushNewTimer(entity.getAuctionEnd(), "auction-timer(ID=" + entity.getId() + ")");
        super.edit(entity);
    }

    public AuctionFacade() {
        super(AuctionEntity.class);
    }
}

package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.BidEntity;

/**
 * Session bean face which provides Create/Retrieve/Update/Destroy functions for
 * a Bid persistence entity. Incorporates a JPA persistence context for entity
 * management.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class BidFacade extends AbstractFacade<BidEntity> {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BidFacade() {
        super(BidEntity.class);
    }
}

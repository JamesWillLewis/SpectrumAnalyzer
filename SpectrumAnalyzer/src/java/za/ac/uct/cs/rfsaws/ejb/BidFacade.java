
package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entities.BidEntity;

/**
 * CRUD operations on Bid entities.
 *
 * @author James Lewis
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

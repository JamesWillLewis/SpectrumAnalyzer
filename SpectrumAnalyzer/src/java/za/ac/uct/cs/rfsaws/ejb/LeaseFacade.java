package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.LeaseEntity;

/**
 * Session bean face which provides Create/Retrieve/Update/Destroy functions for
 * a Lease persistence entity. Incorporates a JPA persistence context for entity
 * management.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class LeaseFacade extends AbstractFacade<LeaseEntity> {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeaseFacade() {
        super(LeaseEntity.class);
    }
}

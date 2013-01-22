package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.SegmentEntity;

/**
 * Session bean face which provides Create/Retrieve/Update/Destroy functions for
 * a Segment persistence entity. Incorporates a JPA persistence context for
 * entity management.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class SegmentFacade extends AbstractFacade<SegmentEntity> {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegmentFacade() {
        super(SegmentEntity.class);
    }
}

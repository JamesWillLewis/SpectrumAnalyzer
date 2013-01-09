
package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entities.LeaseEntity;

/**
 *
 * @author James
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

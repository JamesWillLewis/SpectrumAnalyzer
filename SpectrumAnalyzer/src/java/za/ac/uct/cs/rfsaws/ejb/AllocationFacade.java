package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.AllocationEntity;

/**
 * CRUD operations on Allocation entities. 
 * 
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class AllocationFacade extends AbstractFacade<AllocationEntity> {
    
    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AllocationFacade() {
        super(AllocationEntity.class);
    }
    
}

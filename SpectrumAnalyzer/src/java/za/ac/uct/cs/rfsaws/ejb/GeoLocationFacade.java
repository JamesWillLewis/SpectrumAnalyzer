package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.GeoLocationEntity;

/**
 * Session bean face which provides Create/Retrieve/Update/Destroy functions for
 * a GeoLocation persistence entity. Incorporates a JPA persistence context for
 * entity management.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class GeoLocationFacade extends AbstractFacade<GeoLocationEntity> {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeoLocationFacade() {
        super(GeoLocationEntity.class);
    }
}

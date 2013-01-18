
package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.GeoLocationEntity;

/**
 * CRUD operations on Geo-Location entities.
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entities.Segment;

/**
 *
 * @author James
 */
@Stateless
public class SegmentFacade extends AbstractFacade<Segment> {
    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegmentFacade() {
        super(Segment.class);
    }
    
}

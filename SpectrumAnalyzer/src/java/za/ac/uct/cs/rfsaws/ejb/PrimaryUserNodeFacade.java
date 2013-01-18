/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entity.PrimaryUserNodeEntity;

/**
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Stateless
public class PrimaryUserNodeFacade extends AbstractFacade<PrimaryUserNodeEntity> {
    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrimaryUserNodeFacade() {
        super(PrimaryUserNodeEntity.class);
    }
    
}

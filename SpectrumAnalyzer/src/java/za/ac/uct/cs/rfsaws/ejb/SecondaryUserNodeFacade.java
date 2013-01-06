/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entities.SecondaryUserNodeEntity;

/**
 *
 * @author James
 */
@Stateless
public class SecondaryUserNodeFacade extends AbstractFacade<SecondaryUserNodeEntity> {
    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SecondaryUserNodeFacade() {
        super(SecondaryUserNodeEntity.class);
    }
    
}

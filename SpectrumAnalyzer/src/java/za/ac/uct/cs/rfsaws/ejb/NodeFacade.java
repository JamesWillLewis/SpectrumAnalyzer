/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entities.SecondaryUserNode;

/**
 *
 * @author James
 */
@Stateless
public class NodeFacade extends AbstractFacade<SecondaryUserNode> {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NodeFacade() {
        super(SecondaryUserNode.class);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import za.ac.uct.cs.rfsaws.ejb.facades.PrimaryUserNodeFacade;
import za.ac.uct.cs.rfsaws.ejb.facades.SecondaryUserNodeFacade;
import za.ac.uct.cs.rfsaws.entities.PrimaryUserNode;
import za.ac.uct.cs.rfsaws.entities.SecondaryUserNode;

/**
 *
 * @author James
 */
@ManagedBean
@RequestScoped
public class NodeBean {

    @EJB
    private PrimaryUserNodeFacade primaryNodes;
    @EJB
    private SecondaryUserNodeFacade secondaryNodes;

    public List<PrimaryUserNode> getPrimaryNodes() {
        return primaryNodes.findAll();
    }

    public List<SecondaryUserNode> getSecondaryNodes() {
        return secondaryNodes.findAll();
    }

    /**
     * Creates a new instance of NodeBean
     */
    public NodeBean() {
    }
}

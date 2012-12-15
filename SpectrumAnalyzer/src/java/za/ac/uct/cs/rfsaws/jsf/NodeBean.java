/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import za.ac.uct.cs.rfsaws.ejb.NodeFacade;
import za.ac.uct.cs.rfsaws.entities.SecondaryUserNode;

/**
 *
 * @author James
 */
@ManagedBean
@RequestScoped
public class NodeBean {
    
    @EJB
    private NodeFacade nodeFacade;
    
    public List<SecondaryUserNode> getNodes(){
        return nodeFacade.findAll();
    }

    /**
     * Creates a new instance of NodeBean
     */
    public NodeBean() {
    }
}

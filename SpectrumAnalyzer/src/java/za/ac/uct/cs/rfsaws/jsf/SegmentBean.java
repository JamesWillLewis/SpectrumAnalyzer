/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import za.ac.uct.cs.rfsaws.ejb.facades.SegmentFacade;
import za.ac.uct.cs.rfsaws.entities.Segment;

/**
 *
 * @author James
 */
@ManagedBean
@RequestScoped
public class SegmentBean {
    
    @EJB
    private SegmentFacade segmentFacade;
    
    public List<Segment> getSegments(){
        return segmentFacade.findAll();
    }

    /**
     * Creates a new instance of SegmentBean
     */
    public SegmentBean() {
    }
}

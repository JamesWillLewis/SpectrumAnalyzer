package za.ac.uct.cs.rfsaws.rest.xml;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@XmlRootElement(name="auction")
public class AuctionXML {
    
    @XmlElement(required=true,name="alloc")
    private Long allocationID;
    @XmlElement(required=true,name="start")
    private Date startDate;
    @XmlElement(required=true,name="end")
    private Date endDate;

    public AuctionXML() {
    }

    public Long getAllocationID() {
        return allocationID;
    }

    public void setAllocationID(Long allocationID) {
        this.allocationID = allocationID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    

}

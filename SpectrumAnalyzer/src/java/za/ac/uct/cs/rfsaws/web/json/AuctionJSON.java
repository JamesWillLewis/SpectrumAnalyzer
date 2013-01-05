package za.ac.uct.cs.rfsaws.web.json;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@XmlRootElement(name="AUCTION")
public class AuctionJSON {
    
    @XmlElement(name="alloc")
    public Long allocationID;
    @XmlElement(name="start")
    public Date startDate;
    @XmlElement(name="end")
    public Date endDate;

    public AuctionJSON() {
        allocationID = 1l;
        startDate = new Date();
        endDate = new Date();
    }

}

package za.ac.uct.cs.rfsaws.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@XmlRootElement(name = "bid")
public class BidXML {

    @XmlElement(required = true, name = "auction")
    private Long auctionID;
    @XmlElement(required = true, name = "value")
    private Double value;
    @XmlElement(required = true, name = "segment")
    private Long segmentID;

    public BidXML() {
    }

    public Long getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(Long auctionID) {
        this.auctionID = auctionID;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(Long segmentID) {
        this.segmentID = segmentID;
    }
    
    
    
}

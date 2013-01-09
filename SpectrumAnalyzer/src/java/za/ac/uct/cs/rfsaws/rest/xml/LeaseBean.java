package za.ac.uct.cs.rfsaws.rest.xml;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@XmlRootElement(name = "lease")
public class LeaseBean {

    @XmlElement(required = false)
    private Long id;
    @XmlElement(required = true, name = "node")
    private Long nodeID;
    @XmlElement(required = true, name = "start")
    private Date dateStart;
    @XmlElement(required = true, name = "end")
    private Date dateEnd;
    @XmlElement(required = true, name = "upperFreq")
    private Double bandFreqUpper;
    @XmlElement(required = true, name = "lowerFreq")
    private Double bandFreqLower;
    @XmlElement(required = true, name = "minPow")
    private Double minPower;
    @XmlElement(required = true, name = "maxPow")
    private Double maxPower;

    public LeaseBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNodeID() {
        return nodeID;
    }

    public void setNodeID(Long nodeID) {
        this.nodeID = nodeID;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getBandFreqUpper() {
        return bandFreqUpper;
    }

    public void setBandFreqUpper(Double bandFreqUpper) {
        this.bandFreqUpper = bandFreqUpper;
    }

    public Double getBandFreqLower() {
        return bandFreqLower;
    }

    public void setBandFreqLower(Double bandFreqLower) {
        this.bandFreqLower = bandFreqLower;
    }

    public Double getMinPower() {
        return minPower;
    }

    public void setMinPower(Double minPower) {
        this.minPower = minPower;
    }

    public Double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Double maxPower) {
        this.maxPower = maxPower;
    }
    
    
    
}

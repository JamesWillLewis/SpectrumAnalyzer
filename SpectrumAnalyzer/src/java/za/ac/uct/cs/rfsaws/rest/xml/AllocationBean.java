package za.ac.uct.cs.rfsaws.rest.xml;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@XmlRootElement(name="alloc")
public class AllocationBean {

    @XmlElement(required=false)
    private Long id;
    
    /**
     * Contiguous spectrum band upper frequency bound
     */
    @XmlElement(required=true, name="upper")
    private Double upperBound;
    /**
     * Contiguous spectrum band lower frequency bound
     */
    @XmlElement(required=true, name="lower")
    private Double lowerBound;
    /**
     * Maximum signal propagation power
     */
    @XmlElement(required=true, name="power_lim")
    private Double powerConstraint;
    /**
     * Date/time when allocation available for secondary user.
     */
    @XmlElement(required=true, name="start")
    private Date startDate;
    /**
     * Date/time when allocation claimed by primary user.
     */
    @XmlElement(required=true, name="end")
    private Date endDate;

    public AllocationBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    public Double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Double getPowerConstraint() {
        return powerConstraint;
    }

    public void setPowerConstraint(Double powerConstraint) {
        this.powerConstraint = powerConstraint;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@Entity
@Table(name = "SEGMENTS")
@XmlRootElement
public class SegmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public SegmentEntity() {
    }
    
    
    
    /**
     * Service which this segment may implement (GPRS, 3G, EDGE, WiFI, HDPA,
     * etc)
     */
    private String serviceType;
    /**
     * Minimum signal propagation power
     */
    private Double minPower;
    /**
     *  Spectrum width of this segment (in MHz)
     */
    private Double bandWidth;

    /**
     * Get the value of serviceType
     *
     * @return the value of serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Set the value of serviceType
     *
     * @param serviceType new value of serviceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Get the value of bandWidth
     *
     * @return the value of bandWidth
     */
    public Double getBandWidth() {
        return bandWidth;
    }

    /**
     * Set the value of bandWidth
     *
     * @param bandWidth new value of bandWidth
     */
    public void setBandWidth(Double bandWidth) {
        this.bandWidth = bandWidth;
    }

    /**
     * Get the value of minPower
     *
     * @return the value of minPower
     */
    public Double getMinPower() {
        return minPower;
    }

    /**
     * Set the value of minPower
     *
     * @param minPower new value of minPower
     */
    public void setMinPower(Double minPower) {
        this.minPower = minPower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegmentEntity)) {
            return false;
        }
        SegmentEntity other = (SegmentEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Segment[ id=" + id + " size="+bandWidth+" ]";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@Entity
@Table(name = "LEASES")
@XmlRootElement
public class LeaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Secondary user node who claims this lease.
     */
    @OneToOne
    private SecondaryUserNodeEntity node;
    /**
     * SegmentEntity for which this lease is allocated.
     */
    @OneToOne
    private SegmentEntity segment;
    /**
     * Allocation to which this lease is assigned.
     */
    @OneToOne
    private AllocationEntity allocation;
    /**
     * Start date/time of the lease.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    /**
     * Expiry date/time of the lease.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    /**
     * Allocated upper frequency bound.
     */
    private Double bandFreqUpper;
    /**
     * Allocated lower frequency bound.
     */
    private Double bandFreqLower;

    public LeaseEntity() {
    }
    
    

    /**
     * Get the value of bandFreqLower
     *
     * @return the value of bandFreqLower
     */
    public Double getBandFreqLower() {
        return bandFreqLower;
    }

    /**
     * Set the value of bandFreqLower
     *
     * @param bandFreqLower new value of bandFreqLower
     */
    public void setBandFreqLower(Double bandFreqLower) {
        this.bandFreqLower = bandFreqLower;
    }

    /**
     * Get the value of bandFreqUpper
     *
     * @return the value of bandFreqUpper
     */
    public Double getBandFreqUpper() {
        return bandFreqUpper;
    }

    /**
     * Set the value of bandFreqUpper
     *
     * @param bandFreqUpper new value of bandFreqUpper
     */
    public void setBandFreqUpper(Double bandFreqUpper) {
        this.bandFreqUpper = bandFreqUpper;
    }

    /**
     * Get the value of allocation
     *
     * @return the value of allocation
     */
    public AllocationEntity getAllocation() {
        return allocation;
    }

    /**
     * Set the value of allocation
     *
     * @param allocation new value of allocation
     */
    public void setAllocation(AllocationEntity allocation) {
        this.allocation = allocation;
    }

    /**
     * Get the value of dateEnd
     *
     * @return the value of dateEnd
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Set the value of dateEnd
     *
     * @param dateEnd new value of dateEnd
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Get the value of dateStart
     *
     * @return the value of dateStart
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Set the value of dateStart
     *
     * @param dateStart new value of dateStart
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public SegmentEntity getSegment() {
        return segment;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setSegment(SegmentEntity segment) {
        this.segment = segment;
    }

    /**
     * Get the value of node
     *
     * @return the value of node
     */
    public SecondaryUserNodeEntity getNode() {
        return node;
    }

    /**
     * Set the value of node
     *
     * @param node new value of node
     */
    public void setNode(SecondaryUserNodeEntity node) {
        this.node = node;
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
        if (!(object instanceof LeaseEntity)) {
            return false;
        }
        LeaseEntity other = (LeaseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lease[ id=" + id + " ]";
    }
}

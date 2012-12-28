/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "ALLOCATIONS")
@XmlRootElement
public class Allocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Contiguous spectrum band upper frequency bound
     */
    private Double bandFreqUpper;
    /**
     * Contiguous spectrum band lower frequency bound
     */
    private Double bandFreqLower;
    /**
     * Maximum signal propagation power
     */
    private Double powerConstraint;
    /**
     * Primary User (Node) of this allocation
     */
    @OneToOne
    private PrimaryUserNode primaryUser;
    /**
     * Date/time when allocation available for secondary user.
     */
    private Timestamp beginDate;
    /**
     * Date/time when allocation claimed by primary user.
     */
    private Timestamp endDate;

    /**
     * Get the value of endDate
     *
     * @return the value of endDate
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * Set the value of endDate
     *
     * @param endDate new value of endDate
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the value of beginDate
     *
     * @return the value of beginDate
     */
    public Timestamp getBeginDate() {
        return beginDate;
    }

    /**
     * Set the value of beginDate
     *
     * @param beginDate new value of beginDate
     */
    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public PrimaryUserNode getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(PrimaryUserNode primaryUser) {
        this.primaryUser = primaryUser;
    }

   

    /**
     * Get the value of powerConstraint
     *
     * @return the value of powerConstraint
     */
    public Double getPowerConstraint() {
        return powerConstraint;
    }

    /**
     * Set the value of powerConstraint
     *
     * @param powerConstraint new value of powerConstraint
     */
    public void setPowerConstraint(Double powerConstraint) {
        this.powerConstraint = powerConstraint;
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
        if (!(object instanceof Allocation)) {
            return false;
        }
        Allocation other = (Allocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Allocation[ id=" + id + " ]";
    }
}

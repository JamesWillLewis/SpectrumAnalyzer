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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@Entity
@Table (name="BIDS")
@XmlRootElement
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private SecondaryUserNode bidder;
    private Double bidValue;
    @OneToOne
    private Segment segment;

    /**
     * Get the value of segment
     *
     * @return the value of segment
     */
    public Segment getSegment() {
        return segment;
    }

    /**
     * Set the value of segment
     *
     * @param segment new value of segment
     */
    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    /**
     * Get the value of bidValue
     *
     * @return the value of bidValue
     */
    public Double getBidValue() {
        return bidValue;
    }

    /**
     * Set the value of bidValue
     *
     * @param bidValue new value of bidValue
     */
    public void setBidValue(Double bidValue) {
        this.bidValue = bidValue;
    }

    /**
     * Get the value of bidder
     *
     * @return the value of bidder
     */
    public SecondaryUserNode getBidder() {
        return bidder;
    }

    /**
     * Set the value of bidder
     *
     * @param bidder new value of bidder
     */
    public void setBidder(SecondaryUserNode bidder) {
        this.bidder = bidder;
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
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.uct.cs.rfsaws.entities.Bid[ id=" + id + " ]";
    }
}

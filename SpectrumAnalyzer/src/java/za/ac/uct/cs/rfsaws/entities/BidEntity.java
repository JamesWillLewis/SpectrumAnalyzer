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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@Entity
@Table(name = "BIDS")
@NamedQueries({
    @NamedQuery(name = "findBidsOfAuction", query = "SELECT b FROM BidEntity b WHERE b.auction = :a")})
public class BidEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private SecondaryUserNodeEntity bidder;
    /**
     * Value of this bid (currency)
     */
    private Double bidValue;
    /**
     * SegmentEntity for this bid.
     */
    @ManyToOne
    private SegmentEntity segment;

    @ManyToOne
    private AuctionEntity auction;

    public BidEntity() {
    }
    
    

    /**
     * Get the value of auction
     *
     * @return the value of auction
     */
    public AuctionEntity getAuction() {
        return auction;
    }

    /**
     * Set the value of auction
     *
     * @param auction new value of auction
     */
    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }


    /**
     * Get the value of segment
     *
     * @return the value of segment
     */
    public SegmentEntity getSegment() {
        return segment;
    }

    /**
     * Set the value of segment
     *
     * @param segment new value of segment
     */
    public void setSegment(SegmentEntity segment) {
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
    public SecondaryUserNodeEntity getBidder() {
        return bidder;
    }

    /**
     * Set the value of bidder
     *
     * @param bidder new value of bidder
     */
    public void setBidder(SecondaryUserNodeEntity bidder) {
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
        if (!(object instanceof BidEntity)) {
            return false;
        }
        BidEntity other = (BidEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bid[ id=" + id + " ]";
    }
}

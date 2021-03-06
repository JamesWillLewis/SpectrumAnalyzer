package za.ac.uct.cs.rfsaws.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Database entity which represents an Auction. An auction
 * takes place to assign leases to secondary users to allow them
 * to make secondary use of available spectrum.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "AUCTIONS")
@NamedQueries({
    @NamedQuery(name = "findUnresolvedAuctions",
    query = "SELECT A from AuctionEntity A "
    + "WHERE A.resolved = 0 "
    + "AND A.auctionEnd <= CURRENT_TIMESTAMP"),
    @NamedQuery(name = "findActiveAuctions",
    query = "SELECT A from AuctionEntity A "
    + "WHERE A.resolved = 0 "
    + "AND CURRENT_TIMESTAMP BETWEEN A.auctionStart AND A.auctionEnd")})
@XmlRootElement
public class AuctionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private AllocationEntity allocation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionEnd;
    private byte resolved;

    public AuctionEntity() {
    }

    /**
     * Get the value of resolved
     *
     * @return the value of resolved
     */
    public byte getResolved() {
        return resolved;
    }

    /**
     * Set the value of resolved
     *
     * @param resolved new value of resolved
     */
    public void setResolved(byte resolved) {
        this.resolved = resolved;
    }

    /**
     * Get the value of auctionEnd
     *
     * @return the value of auctionEnd
     */
    public Date getAuctionEnd() {
        return auctionEnd;
    }

    /**
     * Set the value of auctionEnd
     *
     * @param auctionEnd new value of auctionEnd
     */
    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    /**
     * Get the value of auctionStart
     *
     * @return the value of auctionStart
     */
    public Date getAuctionStart() {
        return auctionStart;
    }

    /**
     * Set the value of auctionStart
     *
     * @param auctionStart new value of auctionStart
     */
    public void setAuctionStart(Date auctionStart) {
        this.auctionStart = auctionStart;
    }

    /**
     * Get the value of auctionedSpectrum
     *
     * @return the value of auctionedSpectrum
     */
    public AllocationEntity getAllocation() {
        return allocation;
    }

    /**
     * Set the value of auctionedSpectrum
     *
     * @param auctionedSpectrum new value of auctionedSpectrum
     */
    public void setAllocation(AllocationEntity allocation) {
        this.allocation = allocation;
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
        if (!(object instanceof AuctionEntity)) {
            return false;
        }
        AuctionEntity other = (AuctionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Auction[ id=" + id + " ]";
    }
}

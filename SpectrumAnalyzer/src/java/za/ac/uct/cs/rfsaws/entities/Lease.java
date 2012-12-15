/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.entities;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "LEASES")
@XmlRootElement
public class Lease implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private SecondaryUserNode node;
    @OneToOne
    private Segment segment;
    @OneToOne
    private Allocation allocation;
    private Date dateStart;
    private Date dateEnd;

    /**
     * Get the value of allocation
     *
     * @return the value of allocation
     */
    public Allocation getAllocation() {
        return allocation;
    }

    /**
     * Set the value of allocation
     *
     * @param allocation new value of allocation
     */
    public void setAllocation(Allocation allocation) {
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
    public Segment getString() {
        return segment;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(Segment string) {
        this.segment = string;
    }

    /**
     * Get the value of node
     *
     * @return the value of node
     */
    public SecondaryUserNode getNode() {
        return node;
    }

    /**
     * Set the value of node
     *
     * @param node new value of node
     */
    public void setNode(SecondaryUserNode node) {
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
        if (!(object instanceof Lease)) {
            return false;
        }
        Lease other = (Lease) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.uct.cs.rfsaws.entities.Lease[ id=" + id + " ]";
    }
}
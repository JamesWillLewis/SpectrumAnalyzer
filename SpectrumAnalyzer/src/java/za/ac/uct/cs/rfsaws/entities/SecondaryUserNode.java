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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@Entity
@Table(name = "SECONDARY_USER_NODE")
@NamedQuery(name = "findAllNodes", query = "SELECT n FROM SecondaryUserNode n")
@XmlRootElement
public class SecondaryUserNode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String locationCode;
    private String nodeID;
    private String nodeUserName;
    private Double longitude;
    private Double latitude;

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of nodeUserName
     *
     * @return the value of nodeUserName
     */
    public String getNodeUserName() {
        return nodeUserName;
    }

    /**
     * Set the value of nodeUserName
     *
     * @param nodeUserName new value of nodeUserName
     */
    public void setNodeUserName(String nodeUserName) {
        this.nodeUserName = nodeUserName;
    }

    /**
     * Get the value of nodeID
     *
     * @return the value of nodeID
     */
    public String getNodeID() {
        return nodeID;
    }

    /**
     * Set the value of nodeID
     *
     * @param nodeID new value of nodeID
     */
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    /**
     * Get the value of locationCode
     *
     * @return the value of locationCode
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Set the value of locationCode
     *
     * @param locationCode new value of locationCode
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
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
        if (!(object instanceof SecondaryUserNode)) {
            return false;
        }
        SecondaryUserNode other = (SecondaryUserNode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.uct.cs.rfsaws.entities.Node[ id=" + id + " ]";
    }
}

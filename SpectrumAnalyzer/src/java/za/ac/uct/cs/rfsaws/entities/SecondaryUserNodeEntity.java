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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author James
 */
@Entity
@Table(name = "SECONDARY_USER_NODES")
@XmlRootElement
public class SecondaryUserNodeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nodeID;
    private String nodeUserName;
    @OneToOne
    private GeoLocationEntity location;

    public SecondaryUserNodeEntity() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public GeoLocationEntity getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(GeoLocationEntity location) {
        this.location = location;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecondaryUserNodeEntity)) {
            return false;
        }
        SecondaryUserNodeEntity other = (SecondaryUserNodeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SecondaryUserNode[ id=" + id + " ]";
    }
}

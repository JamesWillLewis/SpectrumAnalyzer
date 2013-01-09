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
@Table(name = "PRIMARY_USER_NODES")
public class PrimaryUserNodeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nodeID;
    private String nodeUserName;
    @OneToOne
    private GeoLocationEntity location;

    public PrimaryUserNodeEntity() {
    }
    
    

    /**
     * Get the value of Location
     *
     * @return the value of Location
     */
    public GeoLocationEntity getLocation() {
        return location;
    }

    /**
     * Set the value of Location
     *
     * @param Location new value of Location
     */
    public void setLocation(GeoLocationEntity Location) {
        this.location = Location;
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
        if (!(object instanceof PrimaryUserNodeEntity)) {
            return false;
        }
        PrimaryUserNodeEntity other = (PrimaryUserNodeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeUserName() {
        return nodeUserName;
    }

    public void setNodeUserName(String nodeUserName) {
        this.nodeUserName = nodeUserName;
    }

    @Override
    public String toString() {
        return "PrimaryUserNode[ id=" + id + " ]";
    }
}

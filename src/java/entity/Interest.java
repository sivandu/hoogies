/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author I320736
 */
@Entity
@Table(name = "interest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interest.findAll", query = "SELECT i FROM Interest i"),
    @NamedQuery(name = "Interest.findByInterestId", query = "SELECT i FROM Interest i WHERE i.interestId = :interestId"),
    @NamedQuery(name = "Interest.findByInterestName", query = "SELECT i FROM Interest i WHERE i.interestName = :interestName"),
    @NamedQuery(name = "Interest.findByPicture", query = "SELECT i FROM Interest i WHERE i.picture = :picture")})
public class Interest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "interest_id")
    private Integer interestId;
    @Column(name = "interest_name")
    private String interestName;
    @Column(name = "picture")
    private String picture;

    public Interest() {
    }

    public Interest(Integer interestId) {
        this.interestId = interestId;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interestId != null ? interestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interest)) {
            return false;
        }
        Interest other = (Interest) object;
        return !((this.interestId == null && other.interestId != null) || (this.interestId != null && !this.interestId.equals(other.interestId)));
    }

    @Override
    public String toString() {
        return interestName;
    }

}

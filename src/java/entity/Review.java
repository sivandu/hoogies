/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author I320736
 */
@Entity
@Table(name = "review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByReviewId", query = "SELECT r FROM Review r WHERE r.reviewId = :reviewId"),
    @NamedQuery(name = "Review.findByRtimestamp", query = "SELECT r FROM Review r WHERE r.rtimestamp = :rtimestamp"),
    @NamedQuery(name = "Review.findByContent", query = "SELECT r FROM Review r WHERE r.content = :content")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "review_id")
    private Integer reviewId;
    @Basic(optional = false)
    @Column(name = "rtimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rtimestamp;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private HoogieGroup group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private HoogieUser user;

    public Review() {
    }

    public Review(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Review(Integer reviewId, Date rtimestamp, String content) {
        this.reviewId = reviewId;
        this.rtimestamp = rtimestamp;
        this.content = content;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Date getTimestamp() {
        return rtimestamp;
    }

    public void setTimestamp(Date rtimestamp) {
        this.rtimestamp = rtimestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public HoogieGroup getGroup() {
        return group;
    }

    public void setGroup(HoogieGroup group) {
        this.group = group;
        if (group != null) {
            if (!group.getReviews().contains(this)) {
                group.getReviews().add(this);
            }
        }
    }
    
    public HoogieUser getUser() {
        return user;
    }

    public void setUser(HoogieUser user) {
        this.user = user;
        if (user != null) {
            if (!user.getReviews().contains(this)) {
                user.getReviews().add(this);
            }
        }  
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Review[ reviewId=" + reviewId + " ]";
    }

    /* Cleans relations to other entities. Used before delete of entity */
    public void detach() {
        group.getReviews().remove(this);
        user.getReviews().remove(this);
        setGroup(null);
        setUser(null);
    }

}

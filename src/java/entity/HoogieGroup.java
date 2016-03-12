/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@Entity
@Table(name = "hoogiegroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoogieGroup.findAll", query = "SELECT g FROM HoogieGroup g"),
    @NamedQuery(name = "HoogieGroup.findByGroupId", query = "SELECT g FROM HoogieGroup g WHERE g.groupId = :groupId"),
    @NamedQuery(name = "HoogieGroup.findByGroupName", query = "SELECT g FROM HoogieGroup g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "HoogieGroup.findByDescription", query = "SELECT g FROM HoogieGroup g WHERE g.description = :description"),
    @NamedQuery(name = "HoogieGroup.findByPicture", query = "SELECT g FROM HoogieGroup g WHERE g.picture = :picture"),
    @NamedQuery(name = "HoogieGroup.findByPrice", query = "SELECT g FROM HoogieGroup g WHERE g.price = :price"),
    @NamedQuery(name = "HoogieGroup.findByPhoneNumber", query = "SELECT g FROM HoogieGroup g WHERE g.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "HoogieGroup.findByEmail", query = "SELECT g FROM HoogieGroup g WHERE g.email = :email"),
    @NamedQuery(name = "HoogieGroup.findByMaxParticipants", query = "SELECT g FROM HoogieGroup g WHERE g.maxParticipants = :maxParticipants"),
    @NamedQuery(name = "HoogieGroup.findByAddress", query = "SELECT g FROM HoogieGroup g WHERE g.address = :address"),
    @NamedQuery(name = "HoogieGroup.findByDays", query = "SELECT g FROM HoogieGroup g WHERE g.days = :days"),
    @NamedQuery(name = "HoogieGroup.findByHour", query = "SELECT g FROM HoogieGroup g WHERE g.groupHour = :groupHour"),
    @NamedQuery(name = "HoogieGroup.findByDuration", query = "SELECT g FROM HoogieGroup g WHERE g.duration = :duration"),
    @NamedQuery(name = "HoogieGroup.findByInterest", query = "SELECT g FROM HoogieGroup g WHERE g.interest.interestName = :interest"),
    @NamedQuery(name = "HoogieGroup.findByKey", query = "SELECT g FROM HoogieGroup g WHERE g.groupName LIKE :key"),
    @NamedQuery(name = "HoogieGroup.findByKeyOrInterest", query = "SELECT g FROM HoogieGroup g WHERE g.groupName LIKE :key or g.interest.interestName LIKE :key")})
public class HoogieGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "group_id")
    private Integer groupId;
    @Basic(optional = false)
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "description")
    private String description;
    @Column(name = "picture")
    private String picture;
    @Column(name = "price")
    private Integer price;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "max_participants")
    private int maxParticipants;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "days")
    private String days;
    @Basic(optional = false)
    @Column(name = "group_hour")
    private String groupHour;
    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "interest_id")
    private Interest interest;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private HoogieUser organizer;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
    private List<HoogieUser> participants = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public HoogieGroup() {
        picture = "/resources/images/Default.png";
    }

    public HoogieGroup(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getGroupHour() {
        return groupHour;
    }

    public void setGroupHour(String groupHour) {
        this.groupHour = groupHour;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public HoogieUser getOrganizer() {
        return organizer;
    }

    public void setOrganizer(HoogieUser organizer) {
        this.organizer = organizer;
        if (organizer != null) {
            if (!organizer.getManagedGroups().contains(this)) {
                organizer.getManagedGroups().add(this);
            }
        }
    }

    public List<HoogieUser> getParticipants() {
        return participants;
    }

    public void setParticipants(List<HoogieUser> participants) {
        this.participants = participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HoogieGroup)) {
            return false;
        }
        HoogieGroup other = (HoogieGroup) object;

        return !((this.groupId == null && other.groupId != null)
                || (this.groupId != null
                && !this.groupId.equals(other.groupId)));
    }

    @Override
    public String toString() {
        return "entity.HoogieGroup[ groupId=" + groupId + " ]";
    }

    /* Gets number of free places in a group. Returns int for number of free places left */
    public int calcFreePlaces() {
        return (this.getMaxParticipants() - this.getParticipants().size());
    }

    /* Checks if the group is fully booked. Returns true if it's full, otherwise returns false */
    public boolean isFull() {
        return (this.getMaxParticipants() == this.getParticipants().size());
    }

    private void removeGroupFromUsers() {
        for (HoogieUser u : participants) {
            u.getGroups().remove(this);
            new DataQueryUsers().updateUser(u);
        }
    }

    /* Cleans relations to other entities. Used before delete of entity */
    public void detach() {
        removeGroupFromUsers();
        setOrganizer(null);
        setInterest(null);
    }

}

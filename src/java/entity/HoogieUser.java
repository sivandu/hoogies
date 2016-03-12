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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author I320736
 */
@Entity
@Table(name = "hoogieuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoogieUser.control", query = "SELECT u FROM HoogieUser u WHERE u.userName = :userName and u.password = :password"),
    @NamedQuery(name = "HoogieUser.findAll", query = "SELECT u FROM HoogieUser u"),
    @NamedQuery(name = "HoogieUser.findByUserId", query = "SELECT u FROM HoogieUser u WHERE u.userId = :userId"),
    @NamedQuery(name = "HoogieUser.findByFirstName", query = "SELECT u FROM HoogieUser u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "HoogieUser.findByLastName", query = "SELECT u FROM HoogieUser u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "HoogieUser.findByEmail", query = "SELECT u FROM HoogieUser u WHERE u.email = :email"),
    @NamedQuery(name = "HoogieUser.findByUserName", query = "SELECT u FROM HoogieUser u WHERE u.userName = :userName"),
    @NamedQuery(name = "HoogieUser.findByPassword", query = "SELECT u FROM HoogieUser u WHERE u.password = :password"),
    @NamedQuery(name = "HoogieUser.findByPhoneNumber", query = "SELECT u FROM HoogieUser u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "HoogieUser.findByPicture", query = "SELECT u FROM HoogieUser u WHERE u.picture = :picture")})
public class HoogieUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "picture")
    private String picture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HoogieGroup> managedGroups = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "participants",
            joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "group_id", referencedColumnName = "group_id")})
    private List<HoogieGroup> groups = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public HoogieUser() {
        picture = "/resources/images/Default.png";
    }

    public HoogieUser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<HoogieGroup> getManagedGroups() {
        return managedGroups;
    }

    public void setManagedGroups(List<HoogieGroup> managedGroups) {
        this.managedGroups = managedGroups;
    }

    public List<HoogieGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<HoogieGroup> groups) {
        this.groups = groups;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HoogieUser)) {
            return false;
        }
        HoogieUser other = (HoogieUser) object;
        return !((this.userId == null && other.userId != null)
                || (this.userId != null && !this.userId.equals(other.userId)));
    }

    @Override
    public String toString() {
        return "entity.HoogieUser[ userId=" + userId + " ]";
    }

}

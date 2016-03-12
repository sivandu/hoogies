/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieGroup;
import entity.HoogieUser;
import entity.Review;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import query.DataQueryGroups;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "groupPage")
@ViewScoped
public class GroupPageBean implements Serializable {

    private HoogieGroup group;
    private HoogieUser currentUser;

    private int groupId;

    /**
     * Creates a new instance of GroupPageBean
     */
    public GroupPageBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        groupId = Integer.parseInt(fc.getExternalContext().getRequestParameterMap().get("group"));
        group = new DataQueryGroups().getGroupById(groupId);
    }

    public HoogieGroup getGroup() {
        return group;
    }

    public void setGroup(HoogieGroup group) {
        this.group = group;
    }

    public HoogieUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(HoogieUser currentUser) {
        this.currentUser = currentUser;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /* Checks if a given user is the organizer of the current group. Returns true if the user is organizer, otherwise returns false */
    public boolean isOrganizer(String user) {
        try {
            currentUser = new DataQueryUsers().getUserByName(user);
            return group.getOrganizer().equals(currentUser);
        } catch (NoResultException e) {
            return false;
        }
    }

    /* Checks if a given user is member of the current group. Returns true if the user is member, otherwise returns false */
    public boolean isMemberOf(String user) {
        try {
            currentUser = new DataQueryUsers().getUserByName(user);
            return group.getParticipants().contains(currentUser);
        } catch (NoResultException e) {
            return false;
        }
    }

    /* Checks if a given user is the author of a given review. Returns true if the user is author, otherwise returns false */
    public boolean isAuthor(Review review, String user) {
        if (review == null || user == null) {
            return false;
        }

        try {
            currentUser = new DataQueryUsers().getUserByName(user);
            return review.getUser().equals(currentUser);
        } catch (NoResultException e) {
            return false;
        }
    }

    /* Adds a given User to a Group. Returns a string representing the next view for redirect */
    public String joinGroup(String user) {
        DataQueryUsers queryUsers = new DataQueryUsers();
        try {
            currentUser = queryUsers.getUserByName(user);

            //user is logged on. add user to group, add group to user. update user entity in database.
            group.getParticipants().add(currentUser);
            currentUser.getGroups().add(group);
            queryUsers.updateUser(currentUser);

            //reload page
            return JSFUtilities.refreshWithParameters();
        } catch (NoResultException e) {
            //user does not exist. do nothing.
            return "";
        }
    }

    /* Removes a given User from a Group. Returns a string representing the next view for redirect */
    public String leaveGroup(String user) {
        DataQueryUsers queryUsers = new DataQueryUsers();
        try {
            currentUser = queryUsers.getUserByName(user);
            
            //user is logged on. remove user from the group, remove group from user. update user entity in database.
            group.getParticipants().remove(currentUser);
            currentUser.getGroups().remove(group);
            queryUsers.updateUser(currentUser);

            //reload page
            return JSFUtilities.refreshWithParameters();
        } catch (NoResultException e) {
            //user does not exist. do nothing.
            return "";
        }
    }

    /* Deletes group */
    public void deleteGroup() {
        new DataQueryGroups().deleteGroup(group);
    }

    /* Converts Date to SimpleDateFormat. Returns formated Date */
    public String getFormatedDate(Date date) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return localDateFormat.format(date);
    }

    /* Sets a given picture to the current group */
    public void setGroupPicture(String picturePath) {
        group.setPicture(picturePath);
        new DataQueryGroups().updateGroup(group);
    }

    /* Checks if group is full. Returns a string to be displayed on view */
    public String isGroupFull() {
        if (group.isFull()) {
            return "Group is full !";
        }
        // group is not full. do nothing.
        return "";
    }

    /* Converts price from int to String. Returns a string to be displayed on view */
    public String getGroupPrice() {
        if (group.getPrice() == 0) {
            return " Free";
        }
        return group.getPrice().toString() + " ILS";
    }

}

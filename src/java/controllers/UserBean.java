/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieGroup;
import entity.HoogieUser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "user")
@ViewScoped
public class UserBean {

    @ManagedProperty("#{login.userName}")
    private String userName;

    @ManagedProperty("#{login}")
    private LoginBean login;

    private HoogieUser currentUser;
    private boolean userNameChanged = false;
    private boolean emailChanged = false;
    private String output;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {

    }

    @PostConstruct
    public void init() {
        currentUser = new DataQueryUsers().getUserByName(userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HoogieUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(HoogieUser currentUser) {
        this.currentUser = currentUser;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public List<HoogieGroup> getMemberOfGroups(String user) {
        return currentUser.getGroups();
    }

    public List<HoogieGroup> getOrganizedGroups(String user) {
        return currentUser.getManagedGroups();
    }

    /* Edit user profile. Returns a string representing the next view for redirect */
    public String editProfile() {
        setOutput("");

        //validate new values. throws error if user name or email already taken
        DataQueryUsers query = new DataQueryUsers();

        if (userNameChanged && query.doesUserNameExist(currentUser.getUserName())) {
            setOutput("Username already exists.");
            return "";
        }

        if (emailChanged && query.doesEmailExist(currentUser.getEmail())) {
            setOutput("Email address already exists.");
            return "";
        }

        //update user with new values
        HoogieUser u = new DataQueryUsers().updateUser(currentUser);
        if (u == null) {
            setOutput("Update profile error: Internal error");
            return "";
        }
      
        if (userNameChanged) {
            return logout();
        }
        
        setOutput("Profile updated successfully !");
        return null;
    }

    /* Listener for user name changes. Switch to true if user name has changed */
    public void userNameChanged(ValueChangeEvent event) {
        userNameChanged = true;
    }

    /* Listener for email address changes. Switch to true if email has changed */
    public void emailChanged(ValueChangeEvent event) {
        emailChanged = true;
    }

    /* Performs logout. Returns a string representing the next view for redirect */
    public String logout() {
        return login.logout();
    }

    /* Change user picture to a given picture path */
    public void setUserPicture(String picturePath) {
        currentUser.setPicture(picturePath);
        new DataQueryUsers().updateUser(currentUser);
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
}

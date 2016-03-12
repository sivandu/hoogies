/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

    private String userName;
    private String password;

    private boolean isValidated;
    private String output = "";

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {

    }

    public boolean getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(boolean val) {
        this.isValidated = val;
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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    /* Validates user and password in database. Returns a string representing the next view for redirect */
    public String validateUserPassword() {
        setOutput("");

        if (userName == null || password == null) {
            return "";
        }

        isValidated = new DataQueryUsers().validateUser(userName, password);
        if (!isValidated) {
            setOutput("Incorrect Username or Password");
            return "";
        }

        return JSFUtilities.refreshWithParameters();
    }

    /* Checks if a user is logged in. Return true if user is logged in, otherwise returns false */
    public boolean isLoggedIn() {
        return isValidated;
    }

    /* Performs logout. Returns a string representing the next view for redirect */
    public String logout() {
        userName = null;
        isValidated = false;
        return "/index.xhtml?faces-redirect=true";
    }

}

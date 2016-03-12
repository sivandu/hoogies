/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieUser;
import exceptions.EntityExistsException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "signup")
@RequestScoped
public class SignupBean {

    private String FirstName;
    private String LastName;
    private String email;
    private String userName;
    private String password;

    private String output = "";

    /**
     * Creates a new instance of SignupBean
     */
    public SignupBean() {

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    /* Registers a new user to the system. Returns a string representing the next view for redirect */
    public String register() {
        setOutput("");
        HoogieUser user = new HoogieUser();
        user.setFirstName(FirstName);
        user.setLastName(LastName);
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);

        try {
            new DataQueryUsers().addUser(user);
            return JSFUtilities.refreshWithParameters();
        } catch (EntityExistsException e) {
            setOutput("Registration error: " + e.getMessage());
        } catch (Exception e) {
            setOutput("Registration error: Internal error " + e.getMessage());
        }

        return "";
    }

}

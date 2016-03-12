/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieGroup;
import entity.HoogieUser;
import entity.Interest;
import exceptions.EntityExistsException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import query.DataQueryGroups;
import query.DataQueryInterests;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "group")
@ViewScoped
public class GroupBean implements Serializable {

    @ManagedProperty("#{login.userName}")
    private String userName;

    private String name;
    private String description;
    private String picture;
    private int price;
    private String phone;
    private String email;
    private int maxParticipants;
    private String address;
    private String days[] = {};
    private String hour;
    private int duration;
    private String interest;
    private String output;

    private HoogieGroup groupForEdit;

    private String daysOfWeek[] = {
        "Sunday",
        "Monday",
        "Thesday",
        "Wedensday",
        "Thursday",
        "Friday",
        "Saturday"
    };

    /**
     * Creates a new instance of GroupBean
     */
    public GroupBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String param = fc.getExternalContext().getRequestParameterMap().get("group");
        //Fetch groupForEdit from DB
        if (param != null) {
            int groupId = Integer.parseInt(param);
            groupForEdit = new DataQueryGroups().getGroupById(groupId);
            fetchGroupForEdit(groupForEdit);
        }
    }

    /* Initializes bean variables with values from a fetched Group object */
    public final void fetchGroupForEdit(HoogieGroup group) {
        if (group != null) {
            name = group.getGroupName();
            description = group.getDescription();
            price = group.getPrice();
            phone = group.getPhoneNumber();
            email = group.getEmail();
            maxParticipants = group.getMaxParticipants();
            address = group.getAddress();
            days = daysStringToArray(group.getDays());
            hour = group.getGroupHour();
            duration = group.getDuration();
            interest = String.valueOf(group.getInterest().getInterestId());
        }
    }

    /* Initializes Group object with values from bean variables (view input values) */
    public void fillGroupFromInput(HoogieGroup group) {
        Interest selectedInterest = new DataQueryInterests().getInterestById(Integer.parseInt(interest));
        HoogieUser organizer = new DataQueryUsers().getUserByName(userName);
        String selectedDays = daysArrayToString(days);

        group.setGroupName(name);
        group.setDescription(description);
        group.setAddress(address);
        group.setDays(selectedDays);
        group.setGroupHour(hour);
        group.setDuration(duration);
        group.setMaxParticipants(maxParticipants);
        group.setPrice(price);
        group.setEmail(email);
        group.setPhoneNumber(phone);
        group.setInterest(selectedInterest);
        group.setOrganizer(organizer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public HoogieGroup getGroupForEdit() {
        return groupForEdit;
    }

    public void setGroupForEdit(HoogieGroup groupForEdit) {
        this.groupForEdit = groupForEdit;
    }

    /* Gets all available interests. Returns a list of interests */
    public List<Interest> getAvailableInterests() {
        return new DataQueryInterests().getAllInterests();
    }

    /* Creates a new Group from input values. Returns a string representing the next view for redirect */
    public String createGroup() {
        HoogieGroup group = new HoogieGroup();
        fillGroupFromInput(group);

        try {
            new DataQueryGroups().addGroup(group);
            return "/index.xhtml?faces-redirect=true";
        } catch (EntityExistsException e) {
            setOutput("Registration error: " + e.getMessage());
        } catch (Exception e) {
            setOutput("Registration error: Internal error");
        }

        return "";
    }

    /* Edits existing Group. Returns a string representing the next view for redirect */
    public String editGroup() {
        if (groupForEdit == null) {
            return "";
        }

        fillGroupFromInput(groupForEdit);
        new DataQueryGroups().updateGroup(groupForEdit);
        return "/viewGroup.xhtml?group=" + groupForEdit.getGroupId() + "faces-redirect=true";
    }

    /* Converts string array of days to simple string. Returns string representing days in format of <day>, <day>. e.g Sunday, Monday */
    public String daysArrayToString(String[] daysArray) {
        String res = "";
        for (int i = 0; i < daysArray.length; i++) {
            int dayIndex = Integer.parseInt(daysArray[i]);
            res += daysOfWeek[dayIndex - 1];
            if (i != daysArray.length - 1) {
                res += ", ";
            }
        }
        return res;
    }

    /* Converts string of days to String array. Returns String array */
    public String[] daysStringToArray(String days) {
        String[] daysArray = days.split(", ");
        int dayIndex;
        for (int i = 0; i < daysArray.length; i++) {
            for (int j = 0; j < daysOfWeek.length; j++) {
                if (daysArray[i].equals(daysOfWeek[j])) {
                    dayIndex = j + 1;
                    daysArray[i] = "" + dayIndex + "";
                }
            }
        }
        return daysArray;
    }

    public String[] getDaysAsStringArray() {
        if (groupForEdit == null) {
            return new String[0];
        }
        return daysStringToArray(groupForEdit.getDays());
    }

    public void setDaysAsStringArray(String[] selectedDays) {
        days = selectedDays;
    }

}

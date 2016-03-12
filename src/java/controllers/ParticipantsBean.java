/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieGroup;
import entity.HoogieUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import query.DataQueryGroups;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "participants")
@ViewScoped
public class ParticipantsBean {

    private int groupId;
    private HoogieGroup group;
    private Map<Integer, Boolean> checked = new HashMap<>();

    /**
     * Creates a new instance of participantsBean
     */
    public ParticipantsBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        groupId = Integer.parseInt(fc.getExternalContext().getRequestParameterMap().get("group"));
        group = new DataQueryGroups().getGroupById(groupId);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public HoogieGroup getGroup() {
        return group;
    }

    public void setGroup(HoogieGroup group) {
        this.group = group;
    }

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Integer, Boolean> checked) {
        this.checked = checked;
    }

    /* Removes participant from a group. Returns a string representing the next view for redirect */
    public String deleteParticipant() {
        List<HoogieUser> participants = group.getParticipants();
        List<HoogieUser> checkedItems = new ArrayList<>();

        // build a list of checked users
        for (HoogieUser user : participants) {
            if (checked.get(user.getUserId())) {
                checkedItems.add(user);
            }
        }

        checked.clear();

        //delete all checked users from the group
        for (HoogieUser user : checkedItems) {
            group.getParticipants().remove(user);
            user.getGroups().remove(group);
            new DataQueryUsers().updateUser(user);
            new DataQueryGroups().updateGroup(group);
        }

        //refresh page
        return null;
    }

    /* Checks if group has no participants. Returns true if group is empty otherwise return false */
    public boolean isParticipantsListEmpty() {
        return group.getParticipants().isEmpty();
    }

}

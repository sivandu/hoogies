/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Interest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import query.DataQueryInterests;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "interestsBean")
@SessionScoped
public class InterestsBean implements Serializable {

    private List<Interest> interestsList; //for display in a datatable view

    public InterestsBean() {
        interestsList = new ArrayList<>();
    }

    public List<Interest> getInterestsList() {
        interestsList = new DataQueryInterests().getAllInterests();
        return interestsList;
    }

    public void setInterestsList(List interestsList) {
        this.interestsList = interestsList;
    }

}

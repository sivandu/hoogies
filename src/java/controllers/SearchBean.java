/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieGroup;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import query.DataQueryGroups;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "search")
@RequestScoped
public class SearchBean {

    private String key;
    private List<HoogieGroup> resultsList;

    /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
        key = "";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<HoogieGroup> getResultsList() {
        return resultsList;
    }

    public void setResultsList(List<HoogieGroup> resultsList) {
        this.resultsList = resultsList;
    }

    /* Searchs for groups with the key or part of it in their name. Returns search results page */
    public String searchByKey() {
        if (!key.isEmpty()) {
            resultsList = new DataQueryGroups().getGroupsByKey(key);
        }

        return "searchResults";
    }

    /* Searchs for groups by Interest. Returns search results page */
    public String searchByInterest(String interestName) {
        if (!interestName.equals("")) {
            key = interestName;
            resultsList = new DataQueryGroups().getGroupsByInterest(key);
        }
        return "searchResults";
    }

}

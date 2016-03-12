/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieGroup;
import entity.HoogieUser;
import entity.Review;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import query.DataQueryGroups;
import query.DataQueryReviews;
import query.DataQueryUsers;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "review")
@ViewScoped
public class ReviewBean {

    @ManagedProperty(value = "#{groupPage.group}")
    private HoogieGroup group;

    @ManagedProperty(value = "#{groupPage.currentUser}")
    private HoogieUser currentUser;

    private Review currentReview;
    private String reviewText;

    /**
     * Creates a new instance of ReviewBean
     */
    public ReviewBean() {

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

    public Review getCurrentReview() {
        return currentReview;
    }

    public void setCurrentReview(Review currentReview) {
        this.currentReview = currentReview;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String text) {
        this.reviewText = text;
    }

    /* Adds a new review to the group. Returns a string representing the nexr view for redirectWithParameters */
    public String createReview(String user) {
        Review r = new Review();
        Date date = new Date();
        
        currentUser = new DataQueryUsers().getUserByName(user);

        //initialize review content
        r.setContent(reviewText);
        r.setTimestamp(date);
        r.setGroup(group);
        r.setUser(currentUser);
        new DataQueryReviews().addReview(r);
        reviewText = "";

        return JSFUtilities.refreshWithParameters();
    }

    /* Edits existing review. Returns a string representing the next view for redirectWithParameters */
    public String editReview(Review reviewForEdit) {
        new DataQueryReviews().updateReview(reviewForEdit);

        return JSFUtilities.refreshWithParameters();
    }

    /* Removes existing review. Returns a string representing the next view for redirectWithParameters */
    public String deleteReview(Review review) {
        new DataQueryReviews().deleteReview(review);
        new DataQueryGroups().updateGroup(group);

        return JSFUtilities.refreshWithParameters();
    }

}

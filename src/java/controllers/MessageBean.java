/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.HoogieGroup;
import entity.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import query.DataQueryGroups;
import query.DataQueryMessages;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "message")
@ViewScoped
public class MessageBean {

    @ManagedProperty(value = "#{groupPage.group}")
    private HoogieGroup group;

    private Message currentMessage;
    private String msgText;

    /**
     * Creates a new instance of MessageBean
     */
    public MessageBean() {

    }

    public HoogieGroup getGroup() {
        return group;
    }

    public void setGroup(HoogieGroup group) {
        this.group = group;
    }

    public Message getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(Message currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String text) {
        this.msgText = text;
    }

    /* Adds a new message to the group */
    public void createMessage() {
        Message m = new Message();
        Date date = new Date();

        m.setContent(msgText);
        m.setTimestamp(date);
        m.setGroup(group);
        new DataQueryMessages().addMessage(m);
        msgText = "";
    }

    /* Edits existing message. Returns a string representing the next view for redirect */
    public String editMessage(Message message) {
        new DataQueryMessages().updateMessage(message);
        return JSFUtilities.refreshWithParameters();
    }

    /* Removes existing message. Returns a string representing the next view for redirect */
    public String deleteMessage(Message message) {
        new DataQueryMessages().deleteMessage(message);
        new DataQueryGroups().updateGroup(group);
        return JSFUtilities.refreshWithParameters();
    }

    /* Gets the last message for the group. Returns the last message if exists, otherwise returns null */
    public Message getLastMessage() {
        if (!group.getMessages().isEmpty()) {
            return group.getMessages().get(group.getMessages().size() - 1);
        }
        return null;
    }

    /* Gets all messages for the group except for the last one. Returns a list of all previous messages */
    public List<Message> getPreviousMessages() {
        List<Message> allPreviousMessages = new ArrayList<>(group.getMessages());
        Collections.reverse(allPreviousMessages);
        //remove most recent message (last message entered)
        allPreviousMessages.remove(0);
        return allPreviousMessages;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author I320736
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByMessageId", query = "SELECT m FROM Message m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "Message.findByTimestamp", query = "SELECT m FROM Message m WHERE m.mtimestamp = :mtimestamp"),
    @NamedQuery(name = "Message.findByContent", query = "SELECT m FROM Message m WHERE m.content = :content")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Basic(optional = false)
    @Column(name = "mtimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mtimestamp;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private HoogieGroup group;

    public Message() {
    }

    public Message(Integer messageId) {
        this.messageId = messageId;
    }

    public Message(Integer messageId, Date timestamp, String content, HoogieGroup group) {
        this.messageId = messageId;
        this.mtimestamp = timestamp;
        this.content = content;
        this.group = group;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getTimestamp() {
        return mtimestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.mtimestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HoogieGroup getGroup() {
        return group;
    }

    public void setGroup(HoogieGroup group) {
        this.group = group;
        if (group != null) {
            if (!group.getMessages().contains(this)) {
                group.getMessages().add(this);
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Message[ messageId=" + messageId + " ]";
    }

    /* Cleans relations to other entities. Used before delete of entity */
    public void detach() {
        group.getMessages().remove(this);
        setGroup(null);
    }

}

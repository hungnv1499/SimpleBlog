/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Calendar;
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
 * @author DELL
 */
@Entity
@Table(name = "Comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findById", query = "SELECT c FROM Comments c WHERE c.id = :id"),
    @NamedQuery(name = "Comments.findByArticle", query = "SELECT c FROM Comments c WHERE c.articleId.id = :id and c.status =:status ORDER BY c.dateCreated desc"),
    @NamedQuery(name = "Comments.findByContents", query = "SELECT c FROM Comments c WHERE c.contents = :contents"),
    @NamedQuery(name = "Comments.findByStatus", query = "SELECT c FROM Comments c WHERE c.status = :status"),
    @NamedQuery(name = "Comments.findByDateCreated", query = "SELECT c FROM Comments c WHERE c.dateCreated = :dateCreated")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Contents")
    private String contents;
    @Column(name = "Status")
    private String status;
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "ArticleId", referencedColumnName = "Id")
    @ManyToOne
    private Articles articleId;
    @JoinColumn(name = "RegistrationId", referencedColumnName = "Email")
    @ManyToOne
    private Registrations registrationId;

    public Comments() {
        
    }

    public Comments(Integer id) {
        this.id = id;
    }
    
    public Comments(Articles articles, Registrations registrations, String contents) {
        this.articleId = articles;
        this.registrationId = registrations;
        this.contents = contents;
        this.dateCreated = Calendar.getInstance().getTime();
        this.status = "Actived";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Articles getArticleId() {
        return articleId;
    }

    public void setArticleId(Articles articleId) {
        this.articleId = articleId;
    }

    public Registrations getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Registrations registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Comments[ id=" + id + " ]";
    }

}

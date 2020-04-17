/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Articles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articles.findAllByAdminAsc", query = "SELECT a FROM Articles a ORDER BY a.dateCreated asc"),
    @NamedQuery(name = "Articles.findAllByAdminDesc", query = "SELECT a FROM Articles a ORDER BY a.dateCreated desc"),
    @NamedQuery(name = "Articles.countAllByAdmin", query = "SELECT count(a) FROM Articles a"),
    @NamedQuery(name = "Articles.findByStatusAsc", query = "SELECT a FROM Articles a WHERE a.status = :status ORDER BY a.dateCreated asc"),
    @NamedQuery(name = "Articles.findByStatusDesc", query = "SELECT a FROM Articles a WHERE a.status = :status ORDER BY a.dateCreated desc"),
    @NamedQuery(name = "Articles.countByStatus", query = "SELECT count(a) FROM Articles a WHERE a.status = :status"),
    @NamedQuery(name = "Articles.findByContentAsc", query = "SELECT a FROM Articles a WHERE a.contents LIKE :contents ORDER BY a.dateCreated asc"),
    @NamedQuery(name = "Articles.findByContentDesc", query = "SELECT a FROM Articles a WHERE a.contents LIKE :contents ORDER BY a.dateCreated desc"),
    @NamedQuery(name = "Articles.countByContent", query = "SELECT count(a) FROM Articles a WHERE a.contents LIKE :contents"),
    @NamedQuery(name = "Articles.findByContentStatusAsc", query = "SELECT a FROM Articles a WHERE a.contents LIKE :contents and a.status = :status ORDER BY a.dateCreated asc"),
    @NamedQuery(name = "Articles.findByContentStatusDesc", query = "SELECT a FROM Articles a WHERE a.contents LIKE :contents and a.status = :status ORDER BY a.dateCreated desc"),
    @NamedQuery(name = "Articles.countByContentStatus", query = "SELECT count(a) FROM Articles a WHERE a.contents LIKE :contents and a.status = :status"),
    @NamedQuery(name = "Articles.findByTitleAsc", query = "SELECT a FROM Articles a WHERE a.title LIKE :title ORDER BY a.dateCreated asc"),
    @NamedQuery(name = "Articles.findByTitleDesc", query = "SELECT a FROM Articles a WHERE a.title LIKE :title ORDER BY a.dateCreated desc"),
    @NamedQuery(name = "Articles.countByTitle", query = "SELECT count(a) FROM Articles a WHERE a.title LIKE :title"),
    @NamedQuery(name = "Articles.findByTitleStatusAsc", query = "SELECT a FROM Articles a WHERE a.title LIKE :title and a.status = :status ORDER BY a.dateCreated asc"),
    @NamedQuery(name = "Articles.findByTitleStatusDesc", query = "SELECT a FROM Articles a WHERE a.title LIKE :title and a.status = :status ORDER BY a.dateCreated desc"),
    @NamedQuery(name = "Articles.countByTitleStatus", query = "SELECT count(a) FROM Articles a WHERE a.title LIKE :title and a.status = :status"),
    @NamedQuery(name = "Articles.findById", query = "SELECT a FROM Articles a WHERE a.id = :id"),
    @NamedQuery(name = "Articles.findByShortDescription", query = "SELECT a FROM Articles a WHERE a.shortDescription = :shortDescription"),
    @NamedQuery(name = "Articles.getSize", query = "SELECT COUNT(a.title) FROM Articles a"),
    @NamedQuery(name = "Articles.getStatus", query = "SELECT a.status FROM Articles a GROUP BY a.status")})
public class Articles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Column(name = "ShortDescription")
    private String shortDescription;
    @Column(name = "Contents")
    private String contents;
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "Status")
    private String status;
    @JoinColumn(name = "Author", referencedColumnName = "Email")
    @ManyToOne
    private Registrations author;
    @OneToMany(mappedBy = "articleId")
    private List<Comments> commentsList;

    public Articles() {
    }

    public Articles(Integer id) {
        this.id = id;
    }

    public Articles(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Articles(String title, String shortDescription, String contents, Registrations author) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.contents = contents;
        this.author = author;
        dateCreated = Calendar.getInstance().getTime();
        status = "New";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Registrations getAuthor() {
        return author;
    }

    public void setAuthor(Registrations author) {
        this.author = author;
    }

    @XmlTransient
    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
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
        if (!(object instanceof Articles)) {
            return false;
        }
        Articles other = (Articles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Articles[ id=" + id + " ]";
    }
    
}

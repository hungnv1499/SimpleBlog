/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Registrations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registrations.findAll", query = "SELECT r FROM Registrations r"),
    @NamedQuery(name = "Registrations.findByEmail", query = "SELECT r FROM Registrations r WHERE r.email = :email"),
    @NamedQuery(name = "Registrations.login", query = "SELECT r FROM Registrations r WHERE r.email = :email and r.password = :password"),
    @NamedQuery(name = "Registrations.findByName", query = "SELECT r FROM Registrations r WHERE r.name = :name"),
    @NamedQuery(name = "Registrations.findByPassword", query = "SELECT r FROM Registrations r WHERE r.password = :password"),
    @NamedQuery(name = "Registrations.findByStatus", query = "SELECT r FROM Registrations r WHERE r.status = :status"),
    @NamedQuery(name = "Registrations.findByDeleted", query = "SELECT r FROM Registrations r WHERE r.deleted = :deleted"),
    @NamedQuery(name = "Registrations.findByRole", query = "SELECT r FROM Registrations r WHERE r.role = :role")})
public class Registrations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Column(name = "Name")
    private String name;
    @Column(name = "Password")
    private String password;
    @Column(name = "Status")
    private String status;
    @Column(name = "Deleted")
    private Boolean deleted;
    @Column(name = "Role")
    private String role;
    @OneToMany(mappedBy = "author")
    private List<Articles> articlesList;
    @OneToMany(mappedBy = "registrationId")
    private List<Comments> commentsList;

    public Registrations() {
    }

    public Registrations(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        deleted = false;
        role = "User";
        status = "New";
    }

    public Registrations(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    public List<Articles> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<Articles> articlesList) {
        this.articlesList = articlesList;
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
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrations)) {
            return false;
        }
        Registrations other = (Registrations) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Registrations[ email=" + email + " ]";
    }

}

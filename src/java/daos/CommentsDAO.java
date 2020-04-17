/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import database.MyEntityManager;
import java.io.Serializable;
import entities.Comments;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author DELL
 */
public class CommentsDAO implements Serializable {

    public CommentsDAO() {
    }
    private EntityManager em = null;

    public void closeEntityManager() throws Exception {
        if (em != null) {
            em.close();
        }
    }

    public List<Comments> getCommentsByArticle(int id) throws Exception {
        List<Comments> listComments = null;
        try {
            em = MyEntityManager.getEntityManager();
            listComments = em.createNamedQuery("Comments.findByArticle").setParameter("id", id).
                    setParameter("status", "Actived").getResultList();
        } finally {
            closeEntityManager();
        }
        return listComments;
    }

    public void postComment(Comments comments) throws Exception {
        try {
            em = MyEntityManager.getEntityManager();
            em.getTransaction().begin();
            em.persist(comments);
            em.getTransaction().commit();
        } finally {
            closeEntityManager();
        }
    }
}

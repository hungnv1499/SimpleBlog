/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import database.MyEntityManager;
import daos.exceptions.NonexistentEntityException;
import entities.Articles;
import java.io.Serializable;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author DELL
 */
public class ArticlesDAO implements Serializable {

    public ArticlesDAO() {
    }
    private EntityManager em = null;

    public void closeEntityManager() throws Exception {
        if (em != null) {
            em.close();
        }
    }
    
    public void create(Articles articles) throws Exception {
        try {
            em = MyEntityManager.getEntityManager();
            em.getTransaction().begin();
            em.persist(articles);
            em.getTransaction().commit();
        } finally {
            closeEntityManager();
        }
    }

    public Articles findArticle(int id) throws NonexistentEntityException, Exception {
        Articles articles = null;
        try {
            em = MyEntityManager.getEntityManager();
            em.getTransaction().begin();
            articles = (Articles) em.createNamedQuery("Articles.findById").setParameter("id", id).getSingleResult();
            em.getTransaction().commit();
        } finally {
            closeEntityManager();
        }
        return articles;
    }

    public List<Articles> getArticlesByAdmin(int maxResult, int pageIndex, String sort) throws Exception {
        List<Articles> list = null;
        try {
            em = MyEntityManager.getEntityManager();
            Query query = null;
            if (sort.equals("asc")) {
                query = em.createNamedQuery("Articles.findAllByAdminAsc", Articles.class);
            } else {
                query = em.createNamedQuery("Articles.findAllByAdminDesc", Articles.class);
            }
            list = query.setMaxResults(maxResult)
                    .setFirstResult(maxResult * (pageIndex - 1)).getResultList();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    public Integer countArticlesByAdmin() throws Exception {
        Integer count = null;
        try {
            em = MyEntityManager.getEntityManager();
            Object temp = em.createNamedQuery("Articles.countAllByAdmin").getSingleResult();
            count = Integer.parseInt(temp.toString());
        } finally {
            closeEntityManager();
        }
        return count;
    }

    public List<Articles> getArticlesByContent(int maxResult, int pageIndex, String content, String sort) throws Exception {
        List<Articles> list = null;
        try {
            em = MyEntityManager.getEntityManager();
            Query query = null;
            if (sort.equals("asc")) {
                query = em.createNamedQuery("Articles.findByContentAsc", Articles.class);
            } else {
                query = em.createNamedQuery("Articles.findByContentDesc", Articles.class);
            }
            list = query.setParameter("contents", "%" + content + "%")
                    .setMaxResults(maxResult)
                    .setFirstResult(maxResult * (pageIndex - 1)).getResultList();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    public Integer countArticlesByContent(String content) throws Exception {
        Integer count = null;
        try {
            em = MyEntityManager.getEntityManager();
            Object temp = em.createNamedQuery("Articles.countByContent")
                    .setParameter("contents", "%" + content + "%")
                    .getSingleResult();
            count = Integer.parseInt(temp.toString());
        } finally {
            closeEntityManager();
        }
        return count;
    }

    public List<Articles> getArticlesByStatus(int maxResult, int pageIndex, String status, String sort) throws Exception {
        List<Articles> list = null;
        try {
            em = MyEntityManager.getEntityManager();
            Query query = null;
            if (sort.equals("asc")) {
                query = em.createNamedQuery("Articles.findByStatusAsc", Articles.class);
            } else {
                query = em.createNamedQuery("Articles.findByStatusDesc", Articles.class);
            }
            list =query.setParameter("status", status)
                    .setMaxResults(maxResult)
                    .setFirstResult(maxResult * (pageIndex - 1)).getResultList();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    public Integer countArticlesByStatus(String status) throws Exception {
        Integer count = null;
        try {
            em = MyEntityManager.getEntityManager();
            Object temp = em.createNamedQuery("Articles.countByStatus")
                    .setParameter("status", status)
                    .getSingleResult();
            count = Integer.parseInt(temp.toString());
        } finally {
            closeEntityManager();
        }
        return count;
    }

    public List<Articles> getArticlesByTitle(int maxResult, int pageIndex, String content, String sort) throws Exception {
        List<Articles> list = null;
        try {
            em = MyEntityManager.getEntityManager();
            Query query = null;
            if (sort.equals("asc")) {
                query = em.createNamedQuery("Articles.findByTitleAsc", Articles.class);
            } else {
                query = em.createNamedQuery("Articles.findByTitleDesc", Articles.class);
            }
            list = query.setParameter("title", "%" + content + "%")
                    .setMaxResults(maxResult)
                    .setFirstResult(maxResult * (pageIndex - 1)).getResultList();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    public Integer countArticlesByTitle(String content) throws Exception {
        Integer count = null;
        try {
            em = MyEntityManager.getEntityManager();
            Object temp = em.createNamedQuery("Articles.countByTitle")
                    .setParameter("title", "%" + content + "%")
                    .getSingleResult();
            count = Integer.parseInt(temp.toString());
        } finally {
            closeEntityManager();
        }
        return count;
    }

    public List<Articles> getArticlesByContentStatus(int maxResult, int pageIndex, String status, String content, String sort) throws Exception {
        List<Articles> list = null;
        try {
            em = MyEntityManager.getEntityManager();
            Query query = null;
            if (sort.equals("asc")) {
                query = em.createNamedQuery("Articles.findByContentStatusAsc", Articles.class);
            } else {
                query = em.createNamedQuery("Articles.findByContentStatusDesc", Articles.class);
            }
            list = query.setParameter("status", status)
                    .setParameter("contents", "%" + content + "%")
                    .setMaxResults(maxResult)
                    .setFirstResult(maxResult * (pageIndex - 1)).getResultList();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    public Integer countArticlesByContentStatus(String status, String content) throws Exception {
        Integer count = null;
        try {
            em = MyEntityManager.getEntityManager();
            Object temp = em.createNamedQuery("Articles.countByContentStatus")
                    .setParameter("status", status)
                    .setParameter("contents", "%" + content + "%")
                    .getSingleResult();
            count = Integer.parseInt(temp.toString());
        } finally {
            closeEntityManager();
        }
        return count;
    }

    public List<Articles> getArticlesByTitleStatus(int maxResult, int pageIndex, String status, String title, String sort) throws Exception {
        List<Articles> list = null;
        try {
            em = MyEntityManager.getEntityManager();
            Query query = null;
            if (sort.equals("asc")) {
                query = em.createNamedQuery("Articles.findByTitleStatusAsc", Articles.class);
            } else {
                query = em.createNamedQuery("Articles.findByTitleStatusDesc", Articles.class);
            }
            list = query.setParameter("status", status)
                    .setParameter("title", "%" + title + "%")
                    .setMaxResults(maxResult)
                    .setFirstResult(maxResult * (pageIndex - 1)).getResultList();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    public Integer countArticlesByTitleStatus(String status, String title) throws Exception {
        Integer count = null;
        try {
            em = MyEntityManager.getEntityManager();
            Object temp = em.createNamedQuery("Articles.countByTitleStatus")
                    .setParameter("status", status)
                    .setParameter("title", "%" + title + "%")
                    .getSingleResult();
            count = Integer.parseInt(temp.toString());
        } finally {
            closeEntityManager();
        }
        return count;
    }

    public List<String> getStatus() throws Exception {
        List<String> list = null;
        try {
            em = MyEntityManager.getEntityManager();
            list = em.createNamedQuery("Articles.getStatus", String.class).getResultList();
        } finally {
            closeEntityManager();
        }
        return list;
    }

    public Integer getSize() throws Exception {
        Integer size = null;
        try {
            em = MyEntityManager.getEntityManager();
            Object obj = em.createNamedQuery("Articles.getSize").getSingleResult();
            size = Integer.parseInt(obj.toString());
        } finally {
            closeEntityManager();
        }
        return size;
    }

    public void changeStatus(int id, String status) throws Exception {
        try {
            em = MyEntityManager.getEntityManager();
            em.getTransaction().begin();
            Articles articles = em.createNamedQuery("Articles.findById", Articles.class)
                    .setParameter("id", id)
                    .getSingleResult();
            if (articles != null) {
                articles.setStatus(status);
                em.merge(articles);
            }
            em.getTransaction().commit();
        } finally {
            closeEntityManager();
        }
    }

}

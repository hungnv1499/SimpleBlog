/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DELL
 */
public class MyEntityManager implements Serializable{
    private static EntityManagerFactory emf = null;
    public static EntityManager getEntityManager() throws Exception{
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Lab231_SimpleBlogPU");
        }
        return emf.createEntityManager();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DELL
 */
public class BlogViewModel implements Serializable{
    private int size;
    private List<ArticlesModel> listArticles;

    public BlogViewModel(int size, List<ArticlesModel> listArticles) {
        this.size = size;
        this.listArticles = listArticles;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ArticlesModel> getListArticles() {
        return listArticles;
    }

    public void setListArticles(List<ArticlesModel> listArticles) {
        this.listArticles = listArticles;
    }
    
}

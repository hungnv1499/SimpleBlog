/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errors;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ArticleError implements Serializable{
    private String titleError, shortDescriptionError, contentsError;

    public ArticleError() {
    }

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getShortDescriptionError() {
        return shortDescriptionError;
    }

    public void setShortDescriptionError(String shortDescriptionError) {
        this.shortDescriptionError = shortDescriptionError;
    }

    public String getContentsError() {
        return contentsError;
    }

    public void setContentsError(String contentsError) {
        this.contentsError = contentsError;
    }
    
}

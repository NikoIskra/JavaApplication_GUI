package hr.java.production.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ducan implements Serializable {
    private String id;
    private String market;
    private String article;
    private String quantity;
    private LocalDateTime modifiedDate;


    public Ducan(String market, String article, String quantity) {
        this.market = market;
        this.article = article;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

package hr.java.production.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Recept implements Serializable  {
    private String id;
    private String lijek;
    private String napomena;
    private LocalDateTime datumKreiranja;
    private String dostava;

    public Recept(String id, String lijek, String napomena, LocalDateTime datumKreiranja, String dostava) {
        this.id = id;
        this.lijek = lijek;
        this.napomena = napomena;
        this.datumKreiranja = datumKreiranja;
        this.dostava = dostava;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLijek() {
        return lijek;
    }

    public void setLijek(String lijek) {
        this.lijek = lijek;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public LocalDateTime getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDateTime datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public String getDostava() {
        return dostava;
    }

    public void setDostava(String dostava) {
        this.dostava = dostava;
    }
}

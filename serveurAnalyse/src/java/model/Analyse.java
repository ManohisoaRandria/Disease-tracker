/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author manohisoa
 */
public class Analyse implements Serializable{
    private String _id;
    private Date dateAnalyses;
    private ComposantTaux[] taux;
    private String color;

    public Analyse(String _id, Date dateAnalyses, ComposantTaux[] taux, String color) {
        this._id = _id;
        this.dateAnalyses = dateAnalyses;
        this.taux = taux;
        this.color = color;
    }

   

    public Analyse() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getDateAnalyses() {
        return dateAnalyses;
    }

    public void setDateAnalyses(Date dateAnalyse) {
        this.dateAnalyses = dateAnalyse;
    }

    public  ComposantTaux[] getTaux() {
        return taux;
    }

    public void setTaux( ComposantTaux[] taux) {
        this.taux = taux;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

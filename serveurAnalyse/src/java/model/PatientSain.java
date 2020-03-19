/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author manohisoa
 */
public class PatientSain implements Serializable{
    private String _id;
    private ComposantTaux[] valeur;
    private ComposantTaux [] proportion;
    private String color;

    public PatientSain(String _id, ComposantTaux[] valeur, ComposantTaux[] proportion, String color) {
        this._id = _id;
        this.valeur = valeur;
        this.proportion = proportion;
        this.color = color;
    }

    public PatientSain() {
    }

  

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ComposantTaux[] getValeur() {
        return valeur;
    }

    public void setValeur(ComposantTaux[] valeur) {
        this.valeur = valeur;
    }

    public ComposantTaux[] getProportion() {
        return proportion;
    }

    public void setProportion(ComposantTaux[] proportion) {
        this.proportion = proportion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

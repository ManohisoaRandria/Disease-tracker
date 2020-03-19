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
public class Maladie implements Serializable {

    private String _id;
    private String nom;
    private ComposantTauxInterv[] caracteristique;//proportion

    public Maladie(String _id, String nom, ComposantTauxInterv[] caracteristique) {
        this._id = _id;
        this.nom = nom;
        this.caracteristique = caracteristique;
    }

    public Maladie() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ComposantTauxInterv[] getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(ComposantTauxInterv[] caracteristique) {
        this.caracteristique = caracteristique;
    }

}

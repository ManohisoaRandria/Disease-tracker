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
public class Patient implements Serializable{
    private String _id;
    private String nom;
    private String prenom;
    private Date naissance;
    private String sexe;
    private Analyse[] analyses;

    public Patient(String _id, String nom, String prenom, Date naissance, String sexe, Analyse[] analyses) {
        this._id = _id;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.sexe = sexe;
        this.analyses = analyses;
    }

   

    public Patient() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

  

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Analyse[] getAnalyses() {
        return analyses;
    }

    public void setAnalyses(Analyse[] analyses) {
        this.analyses = analyses;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
}

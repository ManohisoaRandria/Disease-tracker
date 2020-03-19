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
public class ComposantTauxInterv implements Serializable{
    private String composant;
    private Double min;
    private Double max;

    public ComposantTauxInterv(String composant, Double min, Double max) {
        this.composant = composant;
        this.min = min;
        this.max = max;
    }

    public ComposantTauxInterv() {
    }

    public String getComposant() {
        return composant;
    }

    public void setComposant(String composant) {
        this.composant = composant;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

   
}

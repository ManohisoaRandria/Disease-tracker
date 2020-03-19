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
public class PourcentageMaladie implements Serializable {
    private String maladie;
    private Double pourcentage;

    public PourcentageMaladie() {
    }

    public PourcentageMaladie(String maladie, Double pourcentage) {
        this.maladie = maladie;
        this.pourcentage = pourcentage;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }
    
         
}

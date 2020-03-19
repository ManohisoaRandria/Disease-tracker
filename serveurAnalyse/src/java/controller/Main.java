/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Analyse;
import model.Patient;
import model.PourcentageMaladie;

/**
 *
 * @author manohisoa
 */
public class Main {
    public static void main(String[] args) throws Exception {
        controllerAnalyse an=new controllerAnalyse();
         Patient analyse=an.getAnalysesByPatient("P5");
                Analyse[] annn=analyse.getAnalyses();
                  System.out.println(annn.length);
                Analyse temp=annn[0];
              ArrayList<PourcentageMaladie> pourc= an.getMaladie(temp);
                System.out.println(pourc.size());
     // rm.UpdateAnalyse("P6","anal1", new Double[]{1.0,1.0,1.0,1.0,1.0});
    }
}

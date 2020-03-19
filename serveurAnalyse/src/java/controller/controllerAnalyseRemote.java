/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.ejb.Remote;
import model.Analyse;
import model.Patient;
import model.PourcentageMaladie;

/**
 *
 * @author manohisoa
 */
@Remote
public interface controllerAnalyseRemote {
       public void insertAnalyse(String idpatient, Analyse an) throws Exception ;
       public void UpdateAnalyse(String idpatient, String idAnalyse,String idtaux,Double valeurs) throws Exception ;
      public void insertAnalyse() throws Exception ;
   public Patient[] getPatients()throws Exception;
   public Patient[] getPatientsDiffId(String idpatient)throws Exception;
   public Patient getAnalysesByPatient(String idpatient)throws Exception;
   public  ArrayList<PourcentageMaladie>getMaladie(Analyse analyse)throws Exception;
}

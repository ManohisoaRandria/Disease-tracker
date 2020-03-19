/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import javax.ejb.Stateless;
import model.Analyse;
import model.ComposantTaux;
import model.ComposantTauxInterv;
import model.Maladie;
import model.Patient;
import model.PatientSain;
import model.PourcentageMaladie;
import org.bson.Document;
import outil.DbConnect;

/**
 *
 * @author manohisoa
 */
@Stateless
public class controllerAnalyse implements controllerAnalyseRemote {

    @Override
    public void insertAnalyse(String idpatient, Analyse an) throws Exception {
        MongoClient con = null;
        try {
            con = DbConnect.connect();
            ComposantTaux[] ct = new ComposantTaux[3];
            for (int i = 0; i < 3; i++) {
                ct[i] = new ComposantTaux("C" + i, 12.0);
            }
            an.setTaux(ct);

            MongoDatabase base = con.getDatabase("predictiondb");
            MongoCollection<Document> col = base.getCollection("patient");
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(an);
            col.updateOne(eq("_id", "P5"), Updates.addToSet("analyses", Document.parse(json)));

        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void UpdateAnalyse(String idpatient, String idAnalyse, String idt, Double valeurs) throws Exception {
        MongoClient con = null;
        try {
            con = DbConnect.connect();

            MongoDatabase base = con.getDatabase("predictiondb");
            MongoCollection<Document> col = base.getCollection("patient");

            //  col.deleteOne(Filters.and(eq("_id",idpatient),eq("analyses._id",idAnalyse)));
            col.updateOne(Filters.and(eq("_id", idpatient)), Updates.set("analyses." + idAnalyse + ".taux." + idt + ".valeur", valeurs));
//            col.updateOne(Filters.and(eq("_id",idpatient),eq("analyses._id",idAnalyse)),Updates.set("taux", scores));
//            col.updateOne(Filters.and(eq("_id",idpatient),eq("analyses._id",idAnalyse)),Updates.set("taux", scores));
//            col.updateOne(Filters.and(eq("_id",idpatient),eq("analyses._id",idAnalyse)),Updates.set("taux", scores));
//            col.updateOne(Filters.and(eq("_id",idpatient),eq("analyses._id",idAnalyse)),Updates.set("taux", scores));
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void insertAnalyse() throws Exception {
        MongoClient con = null;
        try {
            con = DbConnect.connect();
            ComposantTaux[] ct = new ComposantTaux[3];
            for (int i = 0; i < 3; i++) {
                ct[i] = new ComposantTaux("C" + i, 12.0);
            }
            List<Document> scores = asList(new Document("composant", "c1").append("valeur", 12),
                    new Document("composant", "c2").append("valeur", 12),
                    new Document("composant", "c3").append("valeur", 12));
            MongoDatabase base = con.getDatabase("predictiondb");
            MongoCollection<Document> col = base.getCollection("patient");
            Document score = new Document().append("_id", "anal1")
                    .append("dateAnalyses", "1999-01-01").append("taux", scores);
            col.updateOne(eq("_id", "P5"), Updates.addToSet("analyses", score));

        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public Patient[] getPatients() throws Exception {
        MongoClient con = null;
        try {
            con = DbConnect.connect();
            MongoDatabase base = con.getDatabase("predictiondb");
            MongoCollection<Document> col = base.getCollection("patient");
            FindIterable<Document> doc = col.find();

            Patient[] comp = new Patient[0];
            Patient temp;
            ArrayList<Patient> tata = new ArrayList<Patient>();
            ObjectMapper obj = new ObjectMapper();
            if (doc != null) {
                for (Document test : doc) {
                    temp = obj.readValue(test.toJson(), Patient.class);
                    tata.add(temp);
                }
                comp = tata.toArray(comp);
                return comp;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    @Override
    public Patient[] getPatientsDiffId(String idpatient) throws Exception {
        MongoClient con = null;
        try {
            con = DbConnect.connect();
            MongoDatabase base = con.getDatabase("predictiondb");
            MongoCollection<Document> col = base.getCollection("patient");
            FindIterable<Document> doc = col.find(Filters.not(eq("_id", idpatient)));

            Patient[] comp = new Patient[0];
            Patient temp;
            ArrayList<Patient> tata = new ArrayList<Patient>();
            ObjectMapper obj = new ObjectMapper();
            if (doc != null) {
                for (Document test : doc) {
                    temp = obj.readValue(test.toJson(), Patient.class);
                    tata.add(temp);
                }
                comp = tata.toArray(comp);
                return comp;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    @Override
    public Patient getAnalysesByPatient(String idpatient) throws Exception {
        MongoClient con = null;
        try {
            con = DbConnect.connect();
            MongoDatabase base = con.getDatabase("predictiondb");
            MongoCollection<Document> col = base.getCollection("patient");
            FindIterable<Document> doc = col.find(eq("_id", idpatient));

            if (doc != null) {
                Document test = doc.first();
                ObjectMapper obj = new ObjectMapper();
                Patient comp = obj.readValue(test.toJson(), Patient.class);
                return comp;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    @Override
    public ArrayList<PourcentageMaladie> getMaladie(Analyse analyse) throws Exception {
        MongoClient con = null;
        try {
            ArrayList<PourcentageMaladie> liste = new ArrayList<PourcentageMaladie>();
            con = DbConnect.connect();
            Maladie[] allMaladie = getMaladie();
            String tahiana="Tahina";
            ComposantTaux[] ct=analyse.getTaux();
             Double tahianaValue=0.0;
             Double[]proportion=new Double[ct.length];
            for (ComposantTaux ct1 : ct) {
                if (ct1.getComposant().equalsIgnoreCase(tahiana)) {
                    tahianaValue = ct1.getValeur();
                }
            }
             System.out.println("tahianaValue=-----------" + tahianaValue);
           for(int i=0;i<ct.length;i++){
                proportion[i]=ct[i].getValeur()/tahianaValue;
                 System.out.println("proportion=-----------" + proportion[i]);
            }
           int compteur=0;
           for (Maladie allMaladie1 : allMaladie) {
               ComposantTauxInterv[] temp=allMaladie1.getCaracteristique();
                System.out.println("temp=-----------" + temp.length);
               for(int i=0;i<temp.length;i++){
                 
                      if(proportion[i]>=temp[i].getMin()&&proportion[i]<=temp[i].getMax()){
                            compteur++;
                            System.out.println("compteur=-----------" + compteur);
                        } 
         
               }
               if(compteur==0){
                   PourcentageMaladie prctemp = new PourcentageMaladie(allMaladie1.getNom(),0.0 );
                   liste.add(prctemp);
               }else{
                   PourcentageMaladie prctemp = new PourcentageMaladie(allMaladie1.getNom(),Double.valueOf((compteur*100)/temp.length) );
                   liste.add(prctemp);
                    System.out.println("pourc=-----------" + Double.valueOf((compteur*100)/temp.length));
               }
                compteur=0;
            }
  
            return liste;
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public Double TestNormalite(Analyse analyse, PatientSain normal) throws Exception {
        Double res = 0.0;

        ComposantTaux[] tauxAn = analyse.getTaux();
        ComposantTaux[] tauxNorm = normal.getValeur();
        ComposantTaux[] proportionNormal = normal.getProportion();
        Double[] proportionAnalyse = new Double[tauxNorm.length];

        for (int i = 0; i < tauxAn.length; i++) {
            proportionAnalyse[i] = tauxAn[i].getValeur() / tauxNorm[i].getValeur();
        }

        Double normePropAnalyse = 0.0;
        Double normePropNormal = 0.0;

        for (int i = 0; i < proportionAnalyse.length; i++) {
            normePropAnalyse += (proportionAnalyse[i] * proportionAnalyse[i]);
            normePropNormal += (proportionNormal[i].getValeur() * proportionNormal[i].getValeur());
        }
        normePropAnalyse = Math.sqrt(normePropAnalyse);
        normePropNormal = Math.sqrt(normePropNormal);

//        if(((normePropAnalyse*100)/normePropNormal)>=90){
//            
//        }
        res = (normePropAnalyse * 100) / normePropNormal;
        return res;
    }

    public Double TestMaladie(Analyse analyse, PatientSain normal, Maladie maladie) throws Exception {
        Double pourcentage = 0.0;

        ComposantTaux[] tauxAn = analyse.getTaux();
        ComposantTaux[] tauxNorm = normal.getValeur();
        ComposantTaux[] proportionNormal = normal.getProportion();
        ComposantTaux[] proportionMaladie = normal.getProportion();
        Double[] proportionAnalyse = new Double[tauxNorm.length];

        for (int i = 0; i < tauxAn.length; i++) {
            proportionAnalyse[i] = tauxAn[i].getValeur() / tauxNorm[i].getValeur();
            System.out.println("proportion=-----------" + proportionAnalyse[i]);
        }
        System.out.println("/////////////////////////////////////");
        Double[] ecartSalamaAnalyse = new Double[tauxNorm.length];
        Double[] ecartSalamaMaladie = new Double[tauxNorm.length];
        for (int i = 0; i < proportionNormal.length; i++) {
            ecartSalamaAnalyse[i] = proportionAnalyse[i] - proportionNormal[i].getValeur();
            if (ecartSalamaAnalyse[i] == 0.0) {
                ecartSalamaAnalyse[i] = 1.0;
            }
            System.out.println("ecartSalamaAnalyse=-----------" + ecartSalamaAnalyse[i]);
            ecartSalamaMaladie[i] = proportionMaladie[i].getValeur() - proportionNormal[i].getValeur();
            if (ecartSalamaMaladie[i] == 0.0) {
                ecartSalamaMaladie[i] = 1.0;
            }
            System.out.println("ecartSalamaMaladie=-----------" + ecartSalamaMaladie[i]);
        }
        System.out.println("/////////////////////////////////////");
        //norme
        Double normePropAnalyse = 0.0;
        Double normePropMaladie = 0.0;

        for (int i = 0; i < ecartSalamaAnalyse.length; i++) {
            normePropAnalyse += (ecartSalamaAnalyse[i] * ecartSalamaAnalyse[i]);
            normePropMaladie += (ecartSalamaMaladie[i] * ecartSalamaMaladie[i]);
        }
        normePropAnalyse = Math.sqrt(normePropAnalyse);
        normePropMaladie = Math.sqrt(normePropMaladie);
        System.out.println("normePropAnalyse=-----------" + normePropAnalyse);
        System.out.println("normePropMaladie=-----------" + normePropMaladie);
        System.out.println("pourc=" + (normePropAnalyse * 100) / normePropMaladie);
        return (normePropAnalyse * 100) / normePropMaladie;
    }

    public PatientSain getPatientSain() throws Exception {
        PatientSain comp = new PatientSain();
        comp.set_id("pats1");
        comp.setColor("rgba(0,200,0,0.2)");
        ComposantTaux[] valeur = new ComposantTaux[5];

        valeur[0] = new ComposantTaux();
        valeur[1] = new ComposantTaux();
        valeur[2] = new ComposantTaux();
        valeur[3] = new ComposantTaux();
        valeur[4] = new ComposantTaux();

//      "proportion":[{"composant":"c1","valeur":1.2},
//      {"composant":"c2","valeur":1.3}
//      ,{"composant":"c3","valeur":1},
//      {"composant":"c4","valeur":0.4},
//      {"composant":"c5","valeur":0.2}],
        valeur[0].setComposant("c1");
        valeur[0].setValeur(70.0);
        valeur[1].setComposant("c2");
        valeur[1].setValeur(10.0);
        valeur[2].setComposant("c3");
        valeur[2].setValeur(15.0);
        valeur[3].setComposant("c4");
        valeur[3].setValeur(35.0);
        valeur[4].setComposant("c5");
        valeur[4].setValeur(42.0);
        ComposantTaux[] proportion = new ComposantTaux[5];

        proportion[0] = new ComposantTaux();
        proportion[1] = new ComposantTaux();
        proportion[2] = new ComposantTaux();
        proportion[3] = new ComposantTaux();
        proportion[4] = new ComposantTaux();

        proportion[0].setComposant("c1");
        proportion[0].setValeur(1.2);
        proportion[1].setComposant("c2");
        proportion[1].setValeur(1.3);
        proportion[2].setComposant("c3");
        proportion[2].setValeur(1.0);
        proportion[3].setComposant("c4");
        proportion[3].setValeur(0.4);
        proportion[4].setComposant("c5");
        proportion[4].setValeur(0.2);
        comp.setProportion(proportion);
        comp.setValeur(valeur);
//        MongoClient con = null;
//        try {
//            con = DbConnect.connect();
//            MongoDatabase base = con.getDatabase("predictiondb");
//            MongoCollection<Document> col = base.getCollection("patientSain");
//            FindIterable<Document> doc = col.find();
//           
//            if (doc != null) {
//                Document test = doc.first();
//                ObjectMapper obj = new ObjectMapper();
//                PatientSain comp = obj.readValue(test.toJson(), PatientSain.class);
//                return comp;
//            }
//
//
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//        }
        return comp;
    }

    public Maladie[] getMaladie() throws Exception {
        MongoClient con = null;
        try {
            con = DbConnect.connect();
            MongoDatabase base = con.getDatabase("predictiondb");
            MongoCollection<Document> col = base.getCollection("maladie");
            FindIterable<Document> doc = col.find();

            Maladie[] comp = new Maladie[0];
            Maladie temp;
            ArrayList<Maladie> tata = new ArrayList<Maladie>();
            ObjectMapper obj = new ObjectMapper();
            if (doc != null) {
                for (Document test : doc) {
                    temp = obj.readValue(test.toJson(), Maladie.class);
                    tata.add(temp);
                }
                comp = tata.toArray(comp);
                return comp;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}

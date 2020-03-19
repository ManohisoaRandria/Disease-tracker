/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Analyse;
import model.Patient;
import model.PourcentageMaladie;

/**
 *
 * @author manohisoa
 */
public class TestServ extends HttpServlet {

    @EJB
    controllerAnalyseRemote an;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get analyse
        if (request.getParameter("patient") != null && request.getParameter("getAnalyse") != null) {
            try {
                Patient analyse=an.getAnalysesByPatient(request.getParameter("patient"));
               //  request.setAttribute("analyses", analyse);
               //  Patient[] p = an.getPatientsDiffId(analyse.get_id());
                ObjectMapper obj = new ObjectMapper();
                String patient=obj.writeValueAsString(analyse);
                PrintWriter out = response.getWriter();
                out.println(patient);
            } catch (Exception ex) {
                Logger.getLogger(TestServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(request.getParameter("diag") != null){
            int idAnalyse=Integer.valueOf(request.getParameter("idAnalyse"));
            String idPatient=request.getParameter("idPatient");
            System.out.println(idAnalyse);
            System.out.println(idPatient);
            try {
                Patient analyse=an.getAnalysesByPatient(idPatient);
                Analyse[] annn=analyse.getAnalyses();
                  System.out.println(annn.length);
                Analyse temp=annn[idAnalyse];
               System.out.println("taux="+temp.getTaux()[0].getValeur());
                if(temp!=null){
                     ArrayList<PourcentageMaladie> pourc= an.getMaladie(temp);
                     ObjectMapper obj = new ObjectMapper();
                    String pourcc=obj.writeValueAsString(pourc);
                    PrintWriter out = response.getWriter();
                    out.println(pourcc);
                }
            } catch (Exception ex) {
                Logger.getLogger(TestServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(request.getParameter("update") != null){
            
            try {
                an.UpdateAnalyse(request.getParameter("idpatient"), request.getParameter("idAnalyse"), request.getParameter("idtaux"),Double.valueOf(request.getParameter("valeur")));
            } catch (Exception ex) {
                Logger.getLogger(TestServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            //get patient
            try {
                Patient[] p = an.getPatients();
                request.setAttribute("patients", p);
                RequestDispatcher reqd = request.getRequestDispatcher("analyse.jsp");
                reqd.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(TestServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outil;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


/**
 *
 * @author manohisoa
 */
public class DbConnect {
   
  
    
    public static MongoClient connect() throws Exception{
          MongoClient mongoClient=null;
     try{
            mongoClient = MongoClients.create("mongodb://localhost:27017");
         
            System.out.println("connected");
           // mongoClient.close();
           return mongoClient;
        } catch (Exception e) {
            throw e;
        }
    }

}

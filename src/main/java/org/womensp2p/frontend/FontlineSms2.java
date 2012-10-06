package org.womensp2p.frontend;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.*;
import java.io.*;


public class FontlineSms2{
  public static ResultSet rs,result;
  private static String resourcePath;
  public static ImportDriver1 ImD1;
  public static ImportDriver ImD;
  public static void main(String args[]){ 
    System.out.println(getConfigDirectoryPath());
    loadFonkozeBase();
    loadSmsBase();
  }

  public static void loadSmsBase(){
    ImD=new ImportDriver(getConfigDirectoryPath(), "", "");
    if(ImD.verifyConnection()){
      System.out.println("Connected to database fontlineSms");
      try{
        rs=ImD.executer("SELECT * FROM message");
        if(rs !=null){
          while(rs.next()){
            System.out.println("id: "+rs.getInt("id"));
            System.out.println("Date: "+rs.getInt("Date"));
            System.out.println("Expediteur: "+rs.getString("senderMsisdn"));
            System.out.println("Message: "+rs.getString("textContent")+"\n\n");
            String chaine=rs.getString("textContent");
            String chaineTab[]=chaine.split(" ");
            if (chaineTab.length!=0){
              for(int i = 0; i<chaineTab.length; i++){
                System.out.println("chaine("+i+")="+chaineTab[i]);
              }
              if(result !=null){
                while(result.next()){
                  if (result.getString("numero_membre")==chaineTab[0]){
                    result.moveToInsertRow();
                    result.updateString("telephone",chaineTab[1]);
                    result.insertRow();
                    break;
                  }
                }
              }
            }
            else{ System.out.println("the database is empty");}
          }
        }
        else{
          System.out.println("the database is empty");
        }	   
      }catch(SQLException ex){
        Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
      }
    }else{
      System.out.println("Connection to message failed !!!");
    }
  }
  public static void loadFonkozeBase(){
    ImD1 = new ImportDriver1("//localhost/fonkoze","root","");
    if(ImD1.verifyConnection()){
      try{
        result=ImD1.executer("SELECT * FROM membre");
        if(result !=null){
          while(result.next()){
            System.out.println("id: "+result.getInt("id"));
            System.out.println("NumeroMembre: "+result.getString("numero_membre"));
            System.out.println("Nom: "+result.getString("nom"));
            System.out.println("Prenom: "+result.getString("prenom"));
            System.out.println("Centre: "+result.getString("prenom"));
            System.out.println("Telephone: "+result.getString("telephone"));
            System.out.println("Adresse: "+result.getString("adresse")+"\n");
          }
        }
        else{
          System.out.println("the database is empty");
        }
      } catch(SQLException ex){
        Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      System.out.println("Connection failed !!!");
    }
  }

  public static String getUserHome() {
    return System.getProperty("user.home")+File.separatorChar+"AppData"+File.separatorChar+"Roaming"+File.separatorChar+"Microsoft"+File.separatorChar+"Windows"+File.separatorChar+"FrontlineSMS" + File.separatorChar;
  }

  public synchronized static String getConfigDirectoryPath() {
    if(resourcePath == null) {
      resourcePath = getUserHome();
    }
    if(resourcePath.charAt(resourcePath.length()-1) != File.separatorChar) {
      resourcePath += File.separatorChar;
    }
    return resourcePath + "frontlinesms_h2_db";
  }
}

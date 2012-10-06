package org.womensp2p.ivr;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.*;
import java.io.*;

class ImportDriver{
  private String link="";
  private String username="";
  private String passwd="";
  private Connection connect;
  private Statement statement;

  public ImportDriver(String link, String username, String passwd){
    this.link=link;
    this.username=username;
    this.passwd=passwd;
  }
  public boolean verifyConnection(){
    try{
      Class.forName("org.h2.Driver").newInstance();
      this.connect=DriverManager.getConnection("jdbc:h2:file:"+this.link, this.username, this.passwd);
      this.statement=this.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      return true;
    }
    catch(SQLException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);}
    catch(ClassNotFoundException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);}
    catch(InstantiationException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);}
    catch(IllegalAccessException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);}
    return false;
  }

  public ResultSet executer(String chaineSQL){
    try{
      ResultSet rs=this.statement.executeQuery(chaineSQL);
      return rs;
    }catch(SQLException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);}
    return null;
  }

  public void close(){
    try{
      this.statement.close();
      this.connect.close();
    }catch(SQLException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

class ImportDriver1{
  private String link="";
  private String username="";
  private String passwd="";
  private Connection connect;
  private Statement statement;
  public ImportDriver1(String link, String username, String passwd){
    this.link=link;
    this.username=username;
    this.passwd=passwd;
  }
  public boolean verifyConnection(){
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      this.connect=DriverManager.getConnection("jdbc:mysql:"+this.link,this.username, this.passwd);
      this.statement=this.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      return true;
    }catch(SQLException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(ClassNotFoundException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(InstantiationException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(IllegalAccessException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public ResultSet executer(String chaineSQL){
    try{
      ResultSet rs=this.statement.executeQuery(chaineSQL);
      return rs;
    }catch(SQLException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public void close(){
    try{
      this.statement.close();
      this.connect.close();
    }catch(SQLException ex){
      Logger.getLogger(ImportDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

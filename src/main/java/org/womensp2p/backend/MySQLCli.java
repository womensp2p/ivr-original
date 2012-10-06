package org.womensp2p.backend;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySQLCli {
  private String dbURL = "";
  private String user = "";
  private String password = "";
  private java.sql.Connection dbConnect = null;
  private java.sql.Statement dbStatement = null;
  private int countrow;

  //setter
  public void setlastrow (int lr)
  {this.countrow=lr;}

  /**
   * Constructeur
   * @param url
   * @param user
   * @param password
   */
  public MySQLCli(String url, String user, String password)
  {
    this.dbURL = url;
    this.user = user;
    this.password = password;
  }

  /**********************Méthodes******************/

  /**
   * Connecter à la base de données
   * @return false en cas d'échec
   */

  public Boolean connect()
  {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();                                    this.dbConnect=DriverManager.getConnection("jdbc:mysql:"+this.dbURL,this.user,this.password);
        this.dbStatement = this.dbConnect.createStatement();
      return true;
    } 
    catch(SQLException ex)
    { Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE,null,ex);} 
    catch(ClassNotFoundException ex)
    { Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);}
    catch(InstantiationException ex)
    { Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);}
    catch(IllegalAccessException ex)
    { Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);}
    return false;
  }

  /**
   * Executer une requete SQL
   * @param sql
   * @return resultat de la requete
   */

  public ResultSet exec(String sql)
  {
    try { 
      ResultSet rs = this.dbStatement.executeQuery(sql);
      return rs;
    } 
    catch (SQLException ex)
    { Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);}
    return null;
  }

  /**
   * Executer une requête SQL
   * @param sql
   * @return le numero de la ligne
   */
  public int exec1(String sql)
  {
    try {
      int rowcount = this.dbStatement.executeUpdate(sql);
      return rowcount;
    }
    catch(SQLException ex) 
    {Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);}
    return 0;
  }

  /**
   * Fermer la connexion au serveur de donnees
   */

  public void close()
  {
    try {
      this.dbStatement.close();
      this.dbConnect.close();
    }
    catch (SQLException ex) 
    {Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


}

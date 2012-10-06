package org.womensp2p.ivr;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repertoire_telephonique {
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here

    MySQLCli mysqlCli = new MySQLCli("//localhost/smsapp", "root", "");
    if (mysqlCli.connect()) 
    {  
      System.out.println("Connected to database smsapp");
      Refresh R=new Refresh(mysqlCli);
      try {
        int NumberAdd=R.CheckAdd();
        if(NumberAdd!=0)
        {
          R.treatment();
        }
      } catch (SQLException ex) { Logger.getLogger(Repertoire_telephonique.class.getName()).log(Level.SEVERE, null, ex);
      }
    }



  }
}

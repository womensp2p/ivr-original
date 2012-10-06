package org.womensp2p.ivr;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Refresh {
  private MySQLCli M;

  public Refresh (MySQLCli m)
  {
    this.M=m;
  }
  public int CheckAdd() throws SQLException
  {
    int NbreAjout=0;
    ResultSet R=M.exec("SELECT value FROM variable WHERE ID=1");
    if(R!=null && R.absolute(1))
    { 
      int x=R.getInt("Value");
      ResultSet RS=M.exec("SELECT COUNT(*)FROM message");
      if (RS!=null && RS.absolute(1)) {
        int y=RS.getInt("COUNT(*)");
        if(x<y)
        {
          NbreAjout=y-x;
        }
      }
    }
    return NbreAjout;
  }


  public void treatment () throws SQLException
  {
    ResultSet R=M.exec("SELECT value FROM variable WHERE ID=1");
    if(R!=null && R.absolute(1))
    {
      int lastposition=R.getInt("Value");
      ResultSet Re=M.exec("SELECT * FROM message");
      if (Re!=null && Re.absolute(lastposition))
      {
        while (Re.next())
        {
          String textcontent=Re.getString("textContent");
          String[] tabsplitmessage=textcontent.split(" ");
          if (tabsplitmessage.length==3)
          {
            boolean keyvalid=Checkkey(tabsplitmessage[0]);
            boolean IDvalid=CheckID(tabsplitmessage[1]);
            boolean numvalid=Checknum(tabsplitmessage[2]);  
            if(keyvalid==true && IDvalid==true && numvalid==true)
            {
              String ID=tabsplitmessage[1];                                         
              String num=tabsplitmessage[2];
              Date date=Re.getDate("date");
              ResultSet Res= M.exec("SELECT IDmember FROM updatetab WHERE IDmember=tabsplitmessage[1]");
              if(Res==null)
              {
                int countrow = M.exec1("INSERT INTO updatetab (IDmember, NewNumber,Dateupdate) VALUES('" + ID + "','" + num+ "','" + date + "'");
                lastposition++;
              }
              if (Res!=null &&! Res.getString ("NewNumber").equals (tabsplitmessage [2]))
              {
                String IDMember=Res.getString ("IDmember");
                String OldNumber=Res.getString("NewNumber");
                Date DateEnable=Res.getDate("Dateupdate");
                Date DateDisable=date;

                int countrowar=M.exec1("INSERT INTO archive (IDMember,OldNumber,DateEnable,DateDisable)VALUE ('"+IDMember+"','"+OldNumber+"','"+DateEnable+"','"+DateDisable+"')");
                int countrowup=M.exec1("DELETE FROM updatetab WHERE IDmember=IDMember");        int countrow = M.exec1("INSERT INTO updatetab (IDmember, NewNumber, Dateupdate) VALUES('" + ID + "','" + num+ "','" + date + "'");
                lastposition++;
              }
              if (Res!=null && Res.getString("NewNumber").equals(tabsplitmessage[2]))
              {
                lastposition++;
              }

            } else {lastposition++ ;}
          } else {lastposition++ ;}
        }
        int up=M.exec1 ("UPDATE variable SET value=lastposition WHERE ID=1");
      }
    }
  }


  /**
   * Check if the keyword is valid
   * @param s
   * @return state of the keyword
   */

  public static boolean Checkkey (String s)
  {
    if ("fk".equals(s)||"Fk".equals(s)||"FK".equals(s)||"fK".equals(s))
    {

      return true;
    }
    else
    {
      return false;
    }
  }



  /**
   * Check if the IDmember is correct
   * @param s
   * @return  state of the IDMember
   */

  public static boolean CheckID (String s)
  {    String [] tab=s.split ("");
    if(tab.length==12 && "7".equals(tab[1]) && "0".equals(tab[2]) && "5".equals(tab[3]) &&"0".equals(tab[10])&& "1".equals(tab[11]))
    {   System.out.println("ID correct");
      return true;
    }
    else
    {  System.out.println("ID incorrect");
      return false;
    }
  }

  /**
   * Check if the number's format is correct
   * @param s
   * @return state of the number's format
   */

  public static boolean Checknum(String s)
  {
    String [] tab= s.split ("");
    if (tab.length==9)
    {
      System.out.println("Numero correct");
      return true;
    }
    else
    {
      System.out.println("Numero incorrect");
      return false;
    }
  }

}


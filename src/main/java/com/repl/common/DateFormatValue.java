/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.common;
 
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author admin
 */
public class DateFormatValue {
   
    private static  java.sql.Date date12=null;
    public  static Date getDateFormatValue(String dates) throws java.text.ParseException
    {
        try {
              DateFormat formatter ; 
              formatter = new SimpleDateFormat("dd-MM-yyyy");
              date12 = new java.sql.Date(formatter.parse(dates).getTime());
              System.out.println(date12);
             }
        catch (Exception e)
             {
                 System.out.println("Exception :"+e);  
             }  
     
     System.out.println(date12);
        return date12;
    }
}

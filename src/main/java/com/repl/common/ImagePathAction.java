/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.common;
import com.repl.common.CommonAction;

import java.util.List;

/**
 *
 * @author admin
 */
public class ImagePathAction extends CommonAction{
    public static String companylogo="";
    public static String q="";
    public static List<Object[]> listdata = null;

    public static String getCompanylogo() {
        return companylogo;
    }

    public static void setCompanylogo(String companylogo) {
        ImagePathAction.companylogo = companylogo;
    }
    
    
    public static String getimagepath()
    {
        try 
            {
                q = "select imagepath from invoicelogomaster";
                listdata = GenericDAO.getInstance().getDataFromQuery(q);
                Object getpath = null;
                if (listdata.size() == 0 || listdata.isEmpty())
                {
                    String file = "rikexim.png";
                    System.out.println("get default image path is:" + file);
                    setCompanylogo("123");
                } 
                else
                {
                    getpath = listdata.get(0);
                    System.out.println("logo path is:" + getpath.toString());
                    setCompanylogo(getpath.toString());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companylogo;
    }
}

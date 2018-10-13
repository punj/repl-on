/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.common;

import com.repl.action.exim.EximBean;
import com.repl.hibernate.OnionExport;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Punj
 */
public class HibernateConnection {

    /*
    
    
    select 
count(*) Total,
DATE_FORMAT(sb_date,'%d/%m/%Y')'Date',
product_description 'Description',
concat(exporter_name,', ',exporter_state) 'Exporter_Name',
concat(consignee_name, 
 if(consignee_country='NOT KNOWN','',concat(', ',consignee_country)),', ',consignee_address ) 'Consignee_Name', 
concat(destination_port,', ',destination_country) 'Destination_Port',
destination_country 'Destination_Country', 
concat(quantity,' ',unit) as 'Quantity', 
round(unit_price_inr/1000,2) as 'Price_INR', 
concat(invoice_currency,round(item_rate_inv_curr,2),'/',unit) as 'Rate',
contact_info 'Contact_Info',
comment 'Comments',
is_contact_info_found 'isContactFound'
from onion_export 
-- where consignee_name like '%3 FOR 8 TRADING INTERNATIONAL%'
group by consignee_name, exporter_name 
order by consignee_name
limit 0,1
;


     */
    public static void main(String[] args) {
//        List<ConsigneeBean> consignees = new ArrayList<>();
        System.out.println("SGN");
//        EximBean cb = new EximBean();
        System.out.println("Maven + Hibernate + MySQL");
        System.out.println("Maven + Hibernate + MySQL");
        Session session =HibernateClose.currentSession();
        String query = "select \n"
                + "count(*) Total,\n"
                + " consignee_name, \n"
                + "consignee_country,\n"
                + "consignee_address,\n"
                + "is_contact_info_found\n"
                + "from onion_export \n"
                + "\n"
                + "group by consignee_name, exporter_name \n"
                + "order by consignee_name \n"
                + "limit 0,2\n"
                + ";";
        System.out.println("SGN Query " + query);
        session.close();
       /* List onions = (List) session.createSQLQuery(query).list();
        for (int i = 0; i < onions.size(); i++) {
            System.out.println("" + onions.get(i));
            Object[] o = (Object[]) onions.get(i);
            System.out.println("" + o[1]);
            
            if(null!=o[0]){
                cb.setTotal(Integer.parseInt(String.valueOf(o[0])));
            }
            
            if(null!=o[1]){
                cb.setConsigneeName((String.valueOf(o[1])));
            }
            
            if(null!=o[2]){
                cb.setConsigneeCountry((String.valueOf(o[2])));
            }
            
            if(null!=o[3]){
                cb.setConsigneeCountry((String.valueOf(o[3])));
            }
            
            if(null!=o[4]){
                cb.setConsigneeCountry((String.valueOf(o[4])));
            }
           /// consignees.add(cb);
        }*/
//        List onions = (List) session.createQuery("from OnionExport").list();

//        System.out.println("SGN Size " + onions.size());
        //System.out.println("SGN Size " + consignees.toString());
     //   NewHibernateUtil.shutdown();
    }
}

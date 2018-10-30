/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action.exim;

import com.repl.common.NewHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Punj
 */
public class EximViewAction extends EximViewBean {

    private Session session;
    private EximViewBean eximBean = new EximViewBean();

    public EximViewAction() {
        session = NewHibernateUtil.getSessionFactory().openSession();
        //  eximBean.setIgnoreSearchOnWebsite(" -panjiva -52wmb");

    }

    public String view() {
        System.out.println("SGN :: View");
        return "view";
    }

    public void autocomplete() throws IOException {
        try {
            String phrase = "";
//            System.out.println("autcomplete");
            if (null != request.getParameter("phrase")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                phrase = (request.getParameter("phrase"));

            }
            String query = "select distinct(consignee_name) \n"
                    + "from onion_export \n"
                    + "where UPPER(consignee_name) like UPPER('" + phrase + "%')\n"
                    + "order by consignee_name;";

//            System.out.println("queryqueryqueryqueryquery " + query);
//            setMaxPrice("12344");
//            maxPrice = "12";
//            setAvgPrice("12344");
//            setMinPrice("12344");
            List priceInfo = (List) session.createSQLQuery(query).list();
            JSONObject obj = new JSONObject();
//            System.out.println("priceInfo.size()priceInfo.size() " + priceInfo.size());
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < priceInfo.size(); i++) {
//                System.out.println("^^^&&& " + priceInfo.get(i));

//            Object[] o = (Object[]) priceInfo.get(i);
//            System.out.println("***22222&  " + String.valueOf(o[0]));
                JSONObject studentJSON = new JSONObject();
                studentJSON.put("name", String.valueOf(priceInfo.get(i)));
//            studentJSON.put("age", o[1]);
                jsonArray.add(studentJSON);
            }
            // obj.put("StudentList", jsonArray);
            //  obj.put(obj, NONE);
//            System.out.println("^^^^^^^ " + jsonArray.toString());
            PrintWriter writer = null;

            writer = response.getWriter();

            writer.println(jsonArray.toString());
//            writer.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setConsigneeValues() {
        try {
            String cname = "";
            System.out.println("autcomplete");
            if (null != request.getParameter("consigneeName")) {
                System.out.println("**cname PARAMETER VALUE****  " + request.getParameter("consigneeName"));
                cname = (request.getParameter("consigneeName"));

            }

            String query = "select \n"
                    + "consignee_name,\n" //0
                    + "consignee_country,\n" //1
                    + "phone,\n" //2
                    + "email,\n" //3
                    + "web,\n" //4
                    + "is_contact_info_found,\n" //5
                    + "hasTooManyShipments,\n" //6
                    + "round(min(item_rate_inv_curr),2) min_price,\n" //7
                    + "round(avg(item_rate_inv_curr),2) avg_price,\n" //8
                    + "round(max(item_rate_inv_curr),2) max_price,\n" //9
                    + "concat(round(sum(quantity),2),' ',unit) quantity,\n" //10
                    + "comment,\n" //11
//                    + "comment,\n" //11
                    + "count(*) total_shipments,\n" //12
                    + "consignee_address,\n" //13
                    + "isFraud,\n" //14
                    + "everContacted\n" //15
                    + "from onion_export where UPPER(consignee_name) like UPPER('" + cname + "');";

            System.out.println("queryqueryqueryqueryquery " + query);

//            setMaxPrice("12344");
//            maxPrice = "12";
//            setAvgPrice("12344");
//            setMinPrice("12344");
            List priceInfo = (List) session.createSQLQuery(query).list();
            JSONObject obj = new JSONObject();
            System.out.println("priceInfo.size()priceInfo.size() " + priceInfo.size());
            JSONObject studentJSON = new JSONObject();
            for (int i = 0; i < priceInfo.size(); i++) {
//                System.out.println("^^^&&& " + priceInfo.get(i));

                Object[] o = (Object[]) priceInfo.get(i);
                System.out.println("***22222&  " + String.valueOf(o[0]));

                if (null != o[0]) {
                    studentJSON.put("consignee_name", String.valueOf(o[0]));
                    System.out.println("consignee_name " + String.valueOf(o[0]));
                }
                if (null != o[1]) {
                    studentJSON.put("consignee_country", String.valueOf(o[1]));
                    System.out.println("consignee_country " + String.valueOf(o[1]));
                }
                if (null != o[2]) {
                    studentJSON.put("phone", String.valueOf(o[2]));
                    System.out.println("phone " + String.valueOf(o[2]));
                }
                if (null != o[3]) {
                    studentJSON.put("email", String.valueOf(o[3]));
                    System.out.println("email " + String.valueOf(o[3]));
                }
                if (null != o[4]) {
                    studentJSON.put("web", String.valueOf(o[4]));
                    System.out.println("web " + String.valueOf(o[4]));
                }
                if (null != o[5]) {
                    studentJSON.put("is_contact_info_found", String.valueOf(o[5]));
                    System.out.println("is_contact_info_found " + String.valueOf(o[5]));
                }
                if (null != o[6]) {
                    studentJSON.put("hasTooManyShipments", String.valueOf(o[6]));
                    System.out.println("hasTooManyShipments " + String.valueOf(o[6]));
                }
                if (null != o[7]) {
                    studentJSON.put("min_price", String.valueOf(o[7]));
                    System.out.println("min_price " + String.valueOf(o[7]));
                }
                if (null != o[8]) {
                    studentJSON.put("avg_price", String.valueOf(o[8]));
                    System.out.println("avg_price " + String.valueOf(o[8]));
                }
                if (null != o[9]) {
                    studentJSON.put("max_price", String.valueOf(o[9]));
                    System.out.println("max_price " + String.valueOf(o[9]));
                }
                if (null != o[10]) {
                    studentJSON.put("quantity", String.valueOf(o[10]));
                    System.out.println("quantity " + String.valueOf(o[10]));
                }
                if (null != o[11]) {
                    studentJSON.put("comment", String.valueOf(o[11]));
                    System.out.println("comment " + String.valueOf(o[11]));
                }
                if (null != o[12]) {
                    studentJSON.put("count", String.valueOf(o[12]));
                    System.out.println("count " + String.valueOf(o[12]));
                }
                if (null != o[13]) {
                    studentJSON.put("consignee_address", String.valueOf(o[13]));
                    System.out.println("consignee_address " + String.valueOf(o[13]));
                }
                if (null != o[14]) {
                    studentJSON.put("isFraud", String.valueOf(o[14]));
                    System.out.println("isFraud " + String.valueOf(o[14]));
                }
                if (null != o[15]) {
                    studentJSON.put("everContacted", String.valueOf(o[15]));
                    System.out.println("everContacted " + String.valueOf(o[15]));
                }
//                studentJSON.put("name", String.valueOf(priceInfo.get(i)));
//            studentJSON.put("age", o[1]);
                // jsonArray.add(studentJSON);
            }
            // obj.put("StudentList", jsonArray);
            //  obj.put(obj, NONE);
            System.out.println("^^^^^^^ " + studentJSON.toString());
            PrintWriter writer = null;

            writer = response.getWriter();

            writer.println(studentJSON.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateConsigneeInfo() throws IOException {
        PrintWriter writer = null;

        writer = response.getWriter();
        try {

            String consigneeName = "";
            String is_contact_info_found = "";
            String hasTooManyShipments = "";
            String phone = "";
            String web = "";
            String email = "";
            String comments = "";
            String isBlackListed = "";
            String everContacted = "";
            if (null != request.getParameter("consigneeName")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                consigneeName = (request.getParameter("consigneeName"));

            }
            if (null != request.getParameter("is_contact_info_found")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                is_contact_info_found = (request.getParameter("is_contact_info_found"));

            }
            if (null != request.getParameter("hasTooManyShipments")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                hasTooManyShipments = (request.getParameter("hasTooManyShipments"));

            }
            if (null != request.getParameter("phone")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                phone = (request.getParameter("phone"));

            }
            if (null != request.getParameter("web")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                web = (request.getParameter("web"));

            }
            if (null != request.getParameter("email")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                email = (request.getParameter("email"));

            }
            if (null != request.getParameter("comments")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                comments = (request.getParameter("comments"));

            }
            if (null != request.getParameter("isBlackListed")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                isBlackListed = (request.getParameter("isBlackListed"));

            }
            if (null != request.getParameter("everContacted")) {
//                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                everContacted = (request.getParameter("everContacted"));

            }

            //  Session session = NewHibernateUtil.getSessionFactory().openSession();
            String query = "UPDATE OnionExport SET \n"
                    + "phone ='" + phone + "' ,\n"
                    + "comment = '" + comments + "' ,\n"
                    + "web = '" + web + "' ,\n"
                    + "email = '" + email + "' \n";
            if (!is_contact_info_found.equals("-1")) {
                query += ",is_contact_info_found = '" + is_contact_info_found + "' \n";
            }
            if (!hasTooManyShipments.equalsIgnoreCase("-1")) {
                query += ",hasTooManyShipments = '" + hasTooManyShipments + "' \n";
            }
            if (!isBlackListed.equalsIgnoreCase("-1")) {
                query += ",isFraud = '" + isBlackListed + "' \n";
            }
            if (!everContacted.equalsIgnoreCase("-1")) {
                query += ",everContacted = '" + everContacted + "' \n";
            }
            query += " WHERE consigneeName like ('%" + consigneeName+ "%')";
            System.out.println(" $$queryquery$$$$ " + query);
            Transaction t = session.beginTransaction();

            Query querys = session.createQuery(query);
            int rr = querys.executeUpdate();

            t.commit();

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("SUCCESS");
            //  session.close();
        } catch (HibernateException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("FAILED");
            response.flushBuffer();
            e.printStackTrace();
        }
        //return "updated";
    }

}

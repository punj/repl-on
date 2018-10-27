/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action.exim;

import com.repl.common.NewHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Punj
 */
public class EximGridAction extends EximGridBean {

    private EximGridBean bean = new EximGridBean();
    Session session = null;

    public EximGridAction() {
        session = NewHibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public String execute() throws Exception {
        return "gridView"; //To change body of generated methods, choose Tools | Templates.
    }

    public String createGrid() throws Exception {
        if (null != request.getParameter("cName")) {
            System.out.println("**cName PARAMETER VALUE****  " + request.getParameter("cName"));

            setcName(request.getParameter("cName"));
            //  phrase = (request.getParameter("phrase"));

        }
        return "eximView"; //To change body of generated methods, choose Tools | Templates.
    }

    private String cName;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void test() {
        System.out.println("TEST ");
    }

    public int getTotalRecords() {

        String query = "select count(distinct(consignee_name)) from onion_export ";
        List priceInfo = (List) session.createSQLQuery(query).list();
        System.out.println(" getTotalRecords  " + priceInfo.get(0));

        return Integer.valueOf(priceInfo.get(0).toString());
    }

    public void loadGridData() throws IOException {
        try {
            getTotalRecords();
            System.out.println("getQueryString " + java.net.URLDecoder.decode(request.getQueryString(), "UTF-8"));
            int draw = 1;
            String orderFiled;
            String orderBy;
            String length = null, start = null, searchValue = null;
            System.out.println("SGN");
//search[value]
            String query = "select \n"
                    + "distinct(consignee_name),\n" //0
                    + "sum(quantity) qty,\n"//1
                    + "unit,\n"//2
                    + "DATE_FORMAT(sb_date, '%d/%m/%Y'),\n" //3
                    + "destination_port,\n" //4
                    + "ifnull(hasTooManyShipments,'Unkown'),\n" //5
                    + "ifnull(is_contact_info_found,'Not Yet')\n" //6
                    + "from onion_export\n"; //

            /* 
             private String DATE_COLUMN = "columns[0][search]"; //
    private String CONSIGNEE_NAME_COLUMN = "columns[1][search]";
    private String DESTINATION_PORT_COLUMN = "columns[2][search]";
    private String QUANTITY_COLUMN = "columns[3][search]";
    private String UNIT_COLUMN = "columns[4][search]";
    private String IS_CONTACT_INFO_FOUND_COLUMN = "columns[5][search]";
    private String HAS_TOO_MANY_SHIPMENTSCOLUMN = "columns[6][search]";
             */
            if (checkIfParameterValueIsNotNull("search[value]")
                    || checkIfParameterValueIsNotNull(DATE_COLUMN)
                    || checkIfParameterValueIsNotNull(CONSIGNEE_NAME_COLUMN)
                    || checkIfParameterValueIsNotNull(DESTINATION_PORT_COLUMN)
                    || checkIfParameterValueIsNotNull(QUANTITY_COLUMN)
                    || checkIfParameterValueIsNotNull(UNIT_COLUMN)
                    || checkIfParameterValueIsNotNull(IS_CONTACT_INFO_FOUND_COLUMN)
                    || checkIfParameterValueIsNotNull(HAS_TOO_MANY_SHIPMENTSCOLUMN)) {
                query += " WHERE ";
            }

            // any search
            if (checkIfParameterValueIsNotNull("search[value]")) {
                searchValue = getParameterValue("search[value]");
                query += " UPPER(consignee_name) like '" + searchValue + "%' ";
                firstSearch = false;
            }

            // unit search
            if (checkIfParameterValueIsNotNull(UNIT_COLUMN)) {
                searchValue = getParameterValue(UNIT_COLUMN);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " UPPER(unit) like '%" + searchValue.toUpperCase() + "%' ";
            }
            // consignee_name search
            if (checkIfParameterValueIsNotNull(CONSIGNEE_NAME_COLUMN)) {
                searchValue = getParameterValue(CONSIGNEE_NAME_COLUMN);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " UPPER(consignee_name) like '" + searchValue + "%' ";
            }
            // Port Name Search
            if (checkIfParameterValueIsNotNull(DESTINATION_PORT_COLUMN)) {
                searchValue = getParameterValue(DESTINATION_PORT_COLUMN);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " UPPER(destination_port) like '" + searchValue.toUpperCase() + "%' ";
            }
            // IS CONTACT FOUND
            if (checkIfParameterValueIsNotNull(IS_CONTACT_INFO_FOUND_COLUMN)) {
                searchValue = getParameterValue(IS_CONTACT_INFO_FOUND_COLUMN);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " UPPER(is_contact_info_found) like '" + searchValue + "%' ";
            }
            // IS CONTACT FOUND
            if (checkIfParameterValueIsNotNull(IS_CONTACT_INFO_FOUND_COLUMN)) {
                searchValue = getParameterValue(IS_CONTACT_INFO_FOUND_COLUMN);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " UPPER(is_contact_info_found) like '" + searchValue + "%' ";
            }

            query += "group by consignee_name\n";

            // ORDER BY
            /*
                private String ORDER_DATE = "order[0][column]=0";
                private String ORDER_CONSIGNEE_NAME = "order[0][column]=1";
                private String ORDER_DESTINATION_PORT_COLUMN = "order[0][column]=2";
                private String ORDER_QUANTITY_COLUMN = "order[0][column]=3";
                private String ORDER_UNIT_COLUMN = "order[0][column]=4";
                private String ORDER_HAS_TOO_MANY_SHIPMENTSCOLUMN = "order[0][column]=5";
                private String ORDER_BY = "order[0][dir]";

             */
            if (null == ORDER_DATE
                    && null == ORDER_CONSIGNEE_NAME
                    && null == ORDER_DESTINATION_PORT_COLUMN
                    && null == ORDER_QUANTITY_COLUMN
                    && null == ORDER_UNIT_COLUMN
                    && null == ORDER_IS_CONTACT_INFO_FOUND_COLUMN
                    && null == ORDER_HAS_TOO_MANY_SHIPMENTSCOLUMN
                    && null == ORDER_DATE) {
                query += "order by qty desc, consignee_name ";
            } else if (checkIfParameterValueIsNotNull("order[0][column]")) {
                orderFiled = getParameterValue("order[0][column]");
                orderBy = getParameterValue(ORDER_BY);

                if (orderFiled.equalsIgnoreCase(ORDER_DATE)) {
                    query += "order by  sb_date " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_CONSIGNEE_NAME)) {
                    query += "order by  consignee_name " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_DESTINATION_PORT_COLUMN)) {
                    query += "order by  destination_port " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_QUANTITY_COLUMN)) {
                    query += "order by  qty " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_UNIT_COLUMN)) {
                    query += "order by  unit " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_IS_CONTACT_INFO_FOUND_COLUMN)) {
                    query += "order by  is_contact_info_found " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_HAS_TOO_MANY_SHIPMENTSCOLUMN)) {
                    query += "order by  hasTooManyShipments " + orderBy + "  ";

                }
                //  query += "order by  " + orderFiled + " " + orderBy;
            }
            if (checkIfParameterValueIsNotNull("start") && checkIfParameterValueIsNotNull("length")) {
                start = getParameterValue("start");
                length = getParameterValue("length");
                query += "limit " + start + "," + length;
            } else {
                query += "limit 10";

            }
            if (checkIfParameterValueIsNotNull("draw")) {
                draw = Integer.valueOf(getParameterValue("draw"));
            }

            System.out.println("queryqueryqueryqueryquery " + query);

//            setMaxPrice("12344");
//            maxPrice = "12";
//            setAvgPrice("12344");
//            setMinPrice("12344");
            List priceInfo = (List) session.createSQLQuery(query).list();
            JSONObject obj = new JSONObject();
            obj.put("draw", draw);
//            System.out.println("priceInfo.size()priceInfo.size() " + priceInfo.size());
            JSONArray dataTablesRecords = new JSONArray();
            for (int i = 0; i < priceInfo.size(); i++) {
                JSONObject dataTableObj = new JSONObject();
//            System.out.println("^^^&&& " + priceInfo.get(i));

                Object[] o = (Object[]) priceInfo.get(i);
//            System.out.println("***22222&  " + String.valueOf(o[0]));
                if (checkNull(String.valueOf(o[0]))) {
                    dataTableObj.put("consignee_name", String.valueOf(o[0]));
//                    System.out.println("Consignee Name " + String.valueOf(o[0]));
                }
                if (checkNull(String.valueOf(o[1]))) {
                    dataTableObj.put("quantity", String.valueOf(o[1]));
//                    System.out.println("quantity " + String.valueOf(o[1]));

                }
                if (checkNull(String.valueOf(o[2]))) {
                    dataTableObj.put("unit", String.valueOf(o[2]));
//                    System.out.println("unit " + String.valueOf(o[2]));

                }
                if (checkNull(String.valueOf(o[3]))) {
                    dataTableObj.put("sb_date", String.valueOf(o[3]));
//                    System.out.println("sb_date " + String.valueOf(o[3]));

                }
                if (checkNull(String.valueOf(o[4]))) {
                    dataTableObj.put("destination_port", String.valueOf(o[4]));
//                    System.out.println("destination_port " + String.valueOf(o[4]));

                }
                if (checkNull(String.valueOf(o[5]))) {
                    dataTableObj.put("hasTooManyShipments", String.valueOf(o[5]));
//                    System.out.println("hasTooManyShipments " + String.valueOf(o[5]));

                }
                if (checkNull(String.valueOf(o[6]))) {
                    dataTableObj.put("is_contact_info_found", String.valueOf(o[6]));
//                    System.out.println("is_contact_info_found " + String.valueOf(o[6]));

                }
//                System.out.println("");

                //  dataTableObj.put("consignee_name", String.valueOf(priceInfo.get(i)));
//            studentJSON.put("age", o[1]);
                dataTablesRecords.add(dataTableObj);
            }
            obj.put("recordsTotal", getTotalRecords());
            obj.put("recordsFiltered", priceInfo.size());
            obj.put("data", dataTablesRecords);
            obj.put("pagees", 244);
            // dataTablesRecords.add(obj);// obj.put("StudentList", jsonArray);
            //  obj.put(obj, NONE);
            System.out.println("^^^^^^^ " + obj.toJSONString());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(obj.toJSONString());
            response.flushBuffer();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        EximGridAction ex = new EximGridAction();
        ex.loadGridData();
//        PrintWriter writer = null;
//        writer.println(jsonArray.toString());
//            writer.println(obj);
    }

    private boolean checkNull(String str) {
        if (null != str) {
            return true;
        } else {
            return false;
        }
    }

    public void test2() {
        System.out.println("SSSSS");
    }

    private boolean checkIfParameterValueIsNotNull(String str) {

        if (null != request.getParameter(str)) {
            System.out.println("Parameter " + str + " value = " + request.getParameter(str));

            return true;
        } else {
            System.out.println("Parameter " + str + " value is null");

            return false;
        }
    }

    private String getParameterValue(String str) {
        System.out.println("Parameter " + str + " has value " + request.getParameter(str));

        return request.getParameter(str);

    }

    /* 
                                {"data": "sb_date", "visible": false},  //0
                                {"data": "consignee_name", "visible": false}, //1
                                {"data": "destination_port", "visible": false}, //2
                                {"data": "quantity", "visible": false}, //3
                                {"data": "unit", "visible": false}, //4
                                {"data": "is_contact_info_found", "visible": false}, //5
                                {"data": "hasTooManyShipments", "visible": false} //6
     */
    private String DATE_COLUMN = "columns[0][search][value]"; //
    private String CONSIGNEE_NAME_COLUMN = "columns[1][search][value]";
    private String DESTINATION_PORT_COLUMN = "columns[2][search][value]";
    private String QUANTITY_COLUMN = "columns[3][search][value]";
    private String UNIT_COLUMN = "columns[4][search][value]";
    private String IS_CONTACT_INFO_FOUND_COLUMN = "columns[5][search][value]";
    private String HAS_TOO_MANY_SHIPMENTSCOLUMN = "columns[6][search][value]";
    private boolean firstSearch = true;

    /* private String ORDER_DATE = "order[0][column]=0";
    private String ORDER_CONSIGNEE_NAME = "order[0][column]=1";
    private String ORDER_DESTINATION_PORT_COLUMN = "order[0][column]=2";
    private String ORDER_QUANTITY_COLUMN = "order[0][column]=3";
    private String ORDER_UNIT_COLUMN = "order[0][column]=4";
    private String ORDER_IS_CONTACT_INFO_FOUND_COLUMN = "order[0][column]=5";
    private String ORDER_HAS_TOO_MANY_SHIPMENTSCOLUMN = "order[0][column]=6";*/
    private String ORDER_BY = "order[0][dir]";

    private String ORDER_DATE = "0";
    private String ORDER_CONSIGNEE_NAME = "1";
    private String ORDER_DESTINATION_PORT_COLUMN = "2";
    private String ORDER_QUANTITY_COLUMN = "3";
    private String ORDER_UNIT_COLUMN = "4";
    private String ORDER_IS_CONTACT_INFO_FOUND_COLUMN = "5";
    private String ORDER_HAS_TOO_MANY_SHIPMENTSCOLUMN = "6";

}

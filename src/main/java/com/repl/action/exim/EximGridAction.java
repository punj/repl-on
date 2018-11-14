/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action.exim;

import com.repl.common.NewHibernateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Punj
 */
public class EximGridAction extends EximGridBean {

    private EximGridBean bean = new EximGridBean();
    private List<String> searchPortMap = new ArrayList<>();
    private List<String> searchUnitList = new ArrayList<>();
    private List<String> searchCountryList = new ArrayList<>();
    private Map<String, String> searchIsFraudMap = new HashMap<>();
    private Map<String, String> searchHasTooManyShipmentsMap = new HashMap<>();
    private Map<String, String> searchContactFoundMap = new HashMap<>();
    private Map<String, String> searchEverContactedMap = new HashMap<>();

    Session session = null;

    public EximGridAction() {
        session = NewHibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public String execute() throws Exception {
        getPorts();
        getUnits();
        getCountries();
        getSearchIsFraud();
        getHasTooManyShipments();
        getContactFound();
        getEverContactedMap();
        return "gridView"; //To change body of generated methods, choose Tools | Templates.
    }

    public String createGrid() throws Exception {
        System.out.println("getQueryString ======>" + request.getQueryString());
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

        String query = "select count(distinct(consignee_name)) from onion_export group by consignee_name, unit";
        // List priceInfo = (List) session.createSQLQuery(query).list();
        //System.out.println(" getTotalRecords  " + priceInfo.get(0));

        return session.createSQLQuery(query).list().size();
    }

    public List<String> getPorts() {

        String query = "select distinct(destination_port) from onion_export\n"
                + " order by destination_port asc; ";
        searchPortMap = (List) session.createSQLQuery(query).list();
        System.out.println(" getTotalPorts  " + searchPortMap.get(0));
        System.out.println(" getPorts  " + searchPortMap.toString());

        return searchPortMap;
    }

    public List<String> getUnits() {

        String query = "select distinct(unit) from onion_export\n"
                + " order by unit asc; ";
        searchUnitList = (List) session.createSQLQuery(query).list();
        System.out.println(" getUnits  " + searchUnitList.get(0));
        System.out.println(" getUnits  " + searchUnitList.toString());

        return searchUnitList;
    }

    public List<String> getCountries() {

        String query = "select distinct(consignee_country) from onion_export\n"
                + " order by consignee_country asc;";
        searchCountryList = (List) session.createSQLQuery(query).list();
        System.out.println(" getCountries  " + searchCountryList.get(0));
        System.out.println(" getCountries  " + searchCountryList.toString());

        return searchCountryList;
    }

    private Map<String, String> getSearchIsFraud() {

        String query = "select \n"
                + "distinct(\n"
                + "case\n"
                + "                    ifnull(isfraud,'Unkown') \n"
                + "                    when 'N' then 'NO'\n"
                + "                    when 'Y' then 'YES'\n"
                + "                    when 'Unkown' then '?'\n"
                + "                    end )as `isFraudValue`, ISFRAUD as IsFraudKey\n"
                + "from onion_export order by isFraudValue asc;";
        List list = (List) session.createSQLQuery(query).list();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("...> " + list.get(i));
            Object[] o = (Object[]) list.get(i);
            searchIsFraudMap.put(String.valueOf(o[1]), String.valueOf(o[0]));
//           if (checkNull(String.valueOf(o[0]))) {}

        }

        System.out.println(" getSearchIsFraud  " + searchIsFraudMap.get(0));
        System.out.println(" getSearchIsFraud  " + searchIsFraudMap.toString());

        return searchIsFraudMap;
    }

    private Map<String, String> getHasTooManyShipments() {

        String query = "select \n"
                + "distinct(\n"
                + "case\n"
                + "                    ifnull(hasTooManyShipments,'Unkown') \n"
                + "                    when 'B' then 'BIG'\n"
                + "                    when 'M' then 'MICRO'\n"
                + "                    when 'S' then 'SMALL'\n"
                + "                    when 'O' then 'MEDIUM'\n"
                + "                    when 'L' then 'LARGE'\n"
                + "                    when 'G' then 'GIANT'\n"
                + "                    when 'Unkown' then '?'\n"
                + "                    end )as `hasTooManyShipmentsValue`, hasTooManyShipments as 'hasTooManyShipmentsKey'\n"
                + "from onion_export order by hasTooManyShipments asc;";
        List list = (List) session.createSQLQuery(query).list();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("...> " + list.get(i));
            Object[] o = (Object[]) list.get(i);
            searchHasTooManyShipmentsMap.put(String.valueOf(o[1]), String.valueOf(o[0]));
//           if (checkNull(String.valueOf(o[0]))) {}

        }

        System.out.println(" getHasTooManyShipments  " + searchHasTooManyShipmentsMap.get(0));
        System.out.println(" getHasTooManyShipments  " + searchHasTooManyShipmentsMap.toString());

        return searchHasTooManyShipmentsMap;
    }

    private Map<String, String> getContactFound() {

        String query = "select \n"
                + "distinct(\n"
                + "case\n"
                + "                    ifnull(is_contact_info_found,'Unkown') \n"
                + "                    when 'N' then 'NO'\n"
                + "                    when 'Y' then 'YES'\n"
                + "                    when 'L' then 'LATER'\n"
                + "                    when 'Unkown' then '?'\n"
                + "                    end )as `is_contact_info_found_value`, is_contact_info_found as 'is_contact_info_found_key'\n"
                + "from onion_export order by is_contact_info_found_value asc;";
        List list = (List) session.createSQLQuery(query).list();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("...> " + list.get(i));
            Object[] o = (Object[]) list.get(i);
            searchContactFoundMap.put(String.valueOf(o[1]), String.valueOf(o[0]));
//           if (checkNull(String.valueOf(o[0]))) {}
        }

        System.out.println(" searchContactFoundMap  " + searchContactFoundMap.get(0));
        System.out.println(" searchContactFoundMap  " + searchContactFoundMap.toString());

        return searchContactFoundMap;
    }

    private Map<String, String> getEverContactedMap() {

        String query = "select \n"
                + "distinct(\n"
                + "case\n"
                + "                    ifnull(everContacted,'Unkown') \n"
                + "                    when 'N' then 'NO'\n"
                + "                    when 'Y' then 'YES'\n"
                + "                    when 'Unkown' then '?'\n"
                + "                    end )as `everContactedValue`, everContacted as `everContactedKey`\n"
                + "from onion_export order by everContacted asc;";
        List list = (List) session.createSQLQuery(query).list();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("...> " + list.get(i));
            Object[] o = (Object[]) list.get(i);
            searchEverContactedMap.put(String.valueOf(o[1]), String.valueOf(o[0]));
//           if (checkNull(String.valueOf(o[0]))) {}
        }

        System.out.println(" searchEverContactedMap  " + searchEverContactedMap.get(0));
        System.out.println(" searchEverContactedMap  " + searchEverContactedMap.toString());

        return searchEverContactedMap;
    }

    public void loadGridData() throws IOException, ParseException {
        try {

            getTotalRecords();

            System.out.println("getQueryString " + java.net.URLDecoder.decode(request.getQueryString(), "UTF-8"));
            int draw = 1;
            String orderFiled;
            String orderBy;
            String length = null, start = null, searchValue = null, searchMinQty = null, searchMaxQty = null;
            System.out.println("SGN");
//search[value]
            String query = "select \n"
                    + "distinct(consignee_name),\n"
                    + "sum(quantity) qty,\n"
                    + "unit,\n"
                    + "DATE_FORMAT(sb_date, '%d/%m/%Y') date,\n"
                    + "destination_port,\n"
                    + "case\n"
                    + "ifnull(hasTooManyShipments,'Unkown') \n"
                    + "	when 'B' then 'BIG'\n"
                    + "	when 'M' then 'MICRO'\n"
                    + "	when 'S' then 'SMALL'\n"
                    + "	when 'O' then 'MEDIUM'\n"
                    + "	when 'L' then 'LARGE'\n"
                    + "	when 'G' then 'GIANT'\n"
                    + "	when 'Unkown' then '?'\n"
                    + "	 end as `hasTooManyShipments`,\n"
                    + "case\n"
                    + "ifnull(is_contact_info_found,'Not Yet')\n"
                    + "	when 'Y' then 'YES'\n"
                    + "	when 'L' then 'LATER'\n"
                    + "	when 'N' then 'NO'\n"
                    + "	when 'Not Yet' then 'Not Yet'\n"
                    + "	 end as `is_contact_info_found`,\n"
                    + "consignee_country,\n"
                    + "date_format(updatedON, '%d/%m/%Y %l:%i %p') updatedon,\n"
                    + "case\n"
                    + "ifnull(isFraud,'?')\n"
                    + "	when 'Y' then 'YES'\n"
                    + "	when 'N' then 'NO'\n"
                    + "	when '?' then '?'\n"
                    + "	 end as `isFraud`,\n"
                    + "case\n"
                    + "ifnull(everContacted,'N')\n"
                    + "	when 'Y' then 'YES'\n"
                    + "	when 'N' then 'NO'\n"
                    + "	end as `everContacted`\n"
                    + "from onion_export"; //

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
            /* if (checkIfParameterValueIsNotNull("search[value]")) {
                searchValue = getParameterValue("search[value]");
                query += " UPPER(consignee_name) like '%" + searchValue + "%' ";
                firstSearch = false;
            }*/
            // unit search
            if (checkIfParameterValueIsNotNull(UNIT_COLUMN)) {
                searchValue = getParameterValue(UNIT_COLUMN);
                if (!searchValue.isEmpty()) {
                    System.out.println("  firstSearch in unit Search " + firstSearch);
                    if (!firstSearch) {
                        query += " AND ";
                    } else {
                        firstSearch = false;
                    }
                    query += " UPPER(unit) like '%" + searchValue.toUpperCase() + "%' ";
                }
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
                    if (!searchValue.isEmpty()) {
//                query += " UPPER(consignee_name) like '%" + searchValue.toUpperCase() + "%' ";
                    query += " UPPER(consignee_name) like '" + searchValue.toUpperCase() + "' ";
                } else {
                    query += " UPPER(consignee_name) like '%" + searchValue.toUpperCase() + "%' ";

                }
            }
            // Port Name Search
            if (checkIfParameterValueIsNotNull(DESTINATION_PORT_COLUMN)) {
                searchValue = getParameterValue(DESTINATION_PORT_COLUMN);
                if (!searchValue.isEmpty()) {
                    System.out.println("  firstSearch in unit Search " + firstSearch);
                    if (!firstSearch) {
                        query += " AND ";
                    } else {
                        firstSearch = false;
                    }
                    query += " UPPER(destination_port) like '" + searchValue.toUpperCase() + "%' ";
                }
            }

            // IS CONTACT FOUND
            if (checkIfParameterValueIsNotNull(IS_CONTACT_INFO_FOUND_COLUMN)) {
                searchValue = getParameterValue(IS_CONTACT_INFO_FOUND_COLUMN);
                if (!searchValue.isEmpty()) {
                    System.out.println("  firstSearch in unit Search " + firstSearch);
                    if (!firstSearch) {
                        query += " AND ";
                    } else {
                        firstSearch = false;
                    }
                    if (searchValue.equalsIgnoreCase("null")) {
                        query += " UPPER(is_contact_info_found) IS NULL ";
                    } else {
                        query += " UPPER(is_contact_info_found) like '" + searchValue + "%' ";
                    }

                }
            }
            // IS CONTACT FOUND
            /* if (checkIfParameterValueIsNotNull(IS_CONTACT_INFO_FOUND_COLUMN)) {
                searchValue = getParameterValue(IS_CONTACT_INFO_FOUND_COLUMN);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " UPPER(is_contact_info_found) like '" + searchValue + "%' ";
            }*/
            // IS FRAUD
            if (checkIfParameterValueIsNotNull(SEARCH_IS_FRAUD) && !getParameterValue(SEARCH_IS_FRAUD).isEmpty()) {

                searchValue = getParameterValue(SEARCH_IS_FRAUD);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                if (searchValue.equalsIgnoreCase("null")) {
                    query += " UPPER(ISFRAUD) IS NULL ";
                } else {
                    query += " UPPER(ISFRAUD) like '" + searchValue + "%' ";
                }
            }
            // SEARCH :: HAS_TOO_MANY_SHIPMENTSCOLUMN
            if (checkIfParameterValueIsNotNull(HAS_TOO_MANY_SHIPMENTSCOLUMN) && !getParameterValue(HAS_TOO_MANY_SHIPMENTSCOLUMN).isEmpty()) {
                searchValue = getParameterValue(HAS_TOO_MANY_SHIPMENTSCOLUMN);
                System.out.println("  firstSearch in HAS_TOO_MANY_SHIPMENTSCOLUMN Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                if (searchValue.equalsIgnoreCase("null")) {
                    query += " UPPER(hasTooManyShipments) IS NULL ";
                } else {
                    query += " UPPER(hasTooManyShipments) like '" + searchValue + "%' ";
                }
            }
            // SEARCH :: SEARCH_CONSIGNEE_COUNTRY
            if (checkIfParameterValueIsNotNull(SEARCH_CONSIGNEE_COUNTRY) && !getParameterValue(SEARCH_CONSIGNEE_COUNTRY).isEmpty()) {
                searchValue = getParameterValue(SEARCH_CONSIGNEE_COUNTRY);
                System.out.println("  firstSearch in SEARCH_CONSIGNEE_COUNTRY Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " UPPER(consignee_country) like '" + searchValue + "%' ";
            }
            // SEARCH :: DATE BETWEEN
            if (checkIfParameterValueIsNotNull(SEARCH_DATE_BETWEEN) && !getParameterValue(SEARCH_DATE_BETWEEN).isEmpty()) {
                searchValue = getParameterValue(SEARCH_DATE_BETWEEN);
                System.out.println("  firstSearch in SEARCH_CONSIGNEE_COUNTRY Search " + firstSearch);
//                JSONObject tempJSON = new JSONObject();
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(searchValue);
                String startDate = json.get("startDate").toString();
                String endDate = json.get("endDate").toString();
                System.out.println(" json startDate ****** " + json.get("startDate"));
                System.out.println(" json endDate ****** " + json.get("endDate"));
                System.out.println(" json ****** " + json);

                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                query += " sb_date BETWEEN '" + startDate + "%'  AND  '" + endDate + "'";
            }
            // EVER CONTACTED
            if (checkIfParameterValueIsNotNull(SEARCH_EVER_CONTACTED) && !getParameterValue(SEARCH_EVER_CONTACTED).isEmpty()) {
                searchValue = getParameterValue(SEARCH_EVER_CONTACTED);
                System.out.println("  firstSearch in unit Search " + firstSearch);
                if (!firstSearch) {
                    query += " AND ";
                } else {
                    firstSearch = false;
                }
                if (searchValue.equalsIgnoreCase("null")) {
                    query += " UPPER(EVERCONTACTED) IS NULL ";
                } else {
                    query += " UPPER(EVERCONTACTED) like '" + searchValue + "%' ";
                }
            }

            query += "group by consignee_name, unit\n";

            // QTY SEARCH between
            if (checkIfParameterValueIsNotNull(SEARCH_MINQTY) && checkIfParameterValueIsNotNull(SEARCH_MAXQTY)) {
                searchMinQty = getParameterValue(SEARCH_MINQTY);
                searchMaxQty = getParameterValue(SEARCH_MAXQTY);
                if (!searchMaxQty.isEmpty() && !searchMinQty.isEmpty()) {
                    System.out.println("1  firstHaving in  Search " + firstHaving);
                    System.out.println("1  firstHaving in  searchMinQty-searchMinQty " + searchMinQty.isEmpty());
                    System.out.println("1  firstHaving in  searchMinQty-searchMinQty " + searchMaxQty.isEmpty());
                    if (firstHaving) {
                        query += " having ";
                    } else {
                        firstHaving = false;
                    }
                    query += " qty > " + searchMinQty + " and qty < " + searchMaxQty;
                }
            }
            searchMinQty = getParameterValue(SEARCH_MINQTY);
            searchMaxQty = getParameterValue(SEARCH_MAXQTY);
            System.out.println("MIN QTY ISEMPTY " + searchMinQty.isEmpty());
            System.out.println("MAX QTY ISEMPTY " + searchMaxQty.isEmpty());

            //search MIN qty >
            if (!searchMinQty.isEmpty() && searchMaxQty.isEmpty()) {
                System.out.println("in Search of MIN > value");
                searchMinQty = getParameterValue(SEARCH_MINQTY);
//                if (searchMinQty.isEmpty()) {
                // searchMaxQty = getParameterValue(SEARCH_MAXQTY);
                System.out.println("2  firstHaving in  Search " + firstHaving);
                if (firstHaving) {
                    query += " having ";
                } else {
                    firstHaving = false;
                }
                query += " qty > " + searchMinQty;
//                }
            }
            //search MAX qty >
            if (!searchMaxQty.isEmpty() && searchMinQty.isEmpty()) {
                System.out.println("CHECK MAX QTY SEARCH");
//                searchMinQty = getParameterValue(SEARCH_MAXQTY);
                searchMaxQty = getParameterValue(SEARCH_MAXQTY);
                System.out.println("3  firstHaving in  Search " + firstHaving);
                if (firstHaving) {
                    query += " having ";
                } else {
                    firstHaving = false;
                }
                query += " qty < " + searchMaxQty;
            }

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
                query += " order by  UpdatedOn desc, qty desc, consignee_name ";
            } else if (checkIfParameterValueIsNotNull("order[0][column]")) {
                orderFiled = getParameterValue("order[0][column]");
                orderBy = getParameterValue(ORDER_BY);

                if (orderFiled.equalsIgnoreCase(ORDER_DATE)) {
                    query += " order by  sb_date " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_CONSIGNEE_NAME)) {
                    query += " order by  consignee_name " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_DESTINATION_PORT_COLUMN)) {
                    query += " order by  destination_port " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_QUANTITY_COLUMN)) {
                    query += " order by  qty " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_UNIT_COLUMN)) {
                    query += " order by  unit " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_IS_CONTACT_INFO_FOUND_COLUMN)) {
                    query += " order by  is_contact_info_found " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_HAS_TOO_MANY_SHIPMENTSCOLUMN)) {
                    query += " order by  hasTooManyShipments " + orderBy + "  ";

                }
                if (orderFiled.equalsIgnoreCase(ORDER_UPDATED_ON)) {
                    query += " order by  updatedOn " + orderBy + "  ";

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
                if (checkNull(String.valueOf(o[7]))) {
                    dataTableObj.put("consignee_country", String.valueOf(o[7]));
//                    System.out.println("is_contact_info_found " + String.valueOf(o[6]));
                }
                if (checkNull(String.valueOf(o[8]))) {
                    dataTableObj.put("updatedOn", String.valueOf(o[8]));

                }
                if (checkNull(String.valueOf(o[9]))) {
                    //   System.out.println("IS Fraud " + String.valueOf(o[9]));
                    dataTableObj.put("isFraud", String.valueOf(o[9]));

                }
                if (checkNull(String.valueOf(o[10]))) {
                    dataTableObj.put("everContacted", String.valueOf(o[10]));

                }
//                System.out.println("");

                //  dataTableObj.put("consignee_name", String.valueOf(priceInfo.get(i)));
//            studentJSON.put("age", o[1]);
                dataTablesRecords.add(dataTableObj);
            }
            obj.put("recordsTotal", getTotalRecords());
//            obj.put("recordsTotal", 100);
//            obj.put("recordsFilter", priceInfo.size());
            System.out.println("datatables records  size " + getFilteredRecords(query));
            obj.put("recordsFiltered", getFilteredRecords(query));
//            obj.put("recordsFiltered", 40);
//            obj.put("recordsFiltered", 40);
//            obj.put("recordsDisplay", 20);
            obj.put("data", dataTablesRecords);
//            obj.put("pages", 244);
//            obj.put("length", Integer.valueOf(length));
//            obj.put("end", Integer.valueOf(start) + Integer.valueOf(length));
//            obj.put("start", (Integer.valueOf(start) + Integer.valueOf(length)) - Integer.valueOf(length));
//            obj.put("start", Integer.valueOf(start)-Integer.valueOf(length));
            // dataTablesRecords.add(obj);// obj.put("StudentList", jsonArray);
            //  obj.put(obj, NONE);
            //  System.out.println("^^^^^^^ " + obj.toJSONString());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(obj.toJSONString());
            response.flushBuffer();
        } catch (HibernateException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.flushBuffer();

        }
    }

    private Integer getFilteredRecords(String query) {
        query = query.substring(0, query.indexOf("limit"));
        System.out.println("Query &&&&& " + query);
        Integer size = session.createSQLQuery(query).list().size();
        System.out.println("size **** >>> " + size);
        return size;
    }

    public static void main(String[] args) throws IOException {
        EximGridAction ex = new EximGridAction();
//        ex.loadGridData();
        ex.getSearchIsFraud();
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

    private boolean isStringEmpty(String str) {
        if (str.isEmpty()) {
            return true;
        } else {
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
    private String SEARCH_CONSIGNEE_COUNTRY = "columns[7][search][value]";
    private String SEARCH_MINQTY = "minQty";
    private String SEARCH_MAXQTY = "maxQty";
    private String SEARCH_IS_FRAUD = "columns[9][search][value]";
    private String SEARCH_EVER_CONTACTED = "columns[10][search][value]";
    private String SEARCH_DATE_BETWEEN = "columns[0][search][value]";
    private boolean firstSearch = true;
    private boolean firstHaving = true;

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
    private String ORDER_UPDATED_ON = "8";

    public List<String> getSearchPortMap() {
        return searchPortMap;
    }

    public void setSearchPortMap(List<String> searchPortMap) {
        this.searchPortMap = searchPortMap;
    }

    public List<String> getSearchUnitList() {
        return searchUnitList;
    }

    public void setSearchUnitList(List<String> searchUnitList) {
        this.searchUnitList = searchUnitList;
    }

    public List<String> getSearchCountryList() {
        return searchCountryList;
    }

    public void setSearchCountryList(List<String> searchCountryList) {
        this.searchCountryList = searchCountryList;
    }

    public Map<String, String> getSearchIsFraudMap() {
        return searchIsFraudMap;
    }

    public void setSearchIsFraudMap(Map<String, String> searchIsFraudMap) {
        this.searchIsFraudMap = searchIsFraudMap;
    }

    public Map<String, String> getSearchHasTooManyShipmentsMap() {
        return searchHasTooManyShipmentsMap;
    }

    public void setSearchHasTooManyShipmentsMap(Map<String, String> searchHasTooManyShipmentsMap) {
        this.searchHasTooManyShipmentsMap = searchHasTooManyShipmentsMap;
    }

    public Map<String, String> getSearchContactFoundMap() {
        return searchContactFoundMap;
    }

    public void setSearchContactFoundMap(Map<String, String> searchContactFoundMap) {
        this.searchContactFoundMap = searchContactFoundMap;
    }

    public Map<String, String> getSearchEverContactedMap() {
        return searchEverContactedMap;
    }

    public void setSearchEverContactedMap(Map<String, String> searchEverContactedMap) {
        this.searchEverContactedMap = searchEverContactedMap;
    }

}

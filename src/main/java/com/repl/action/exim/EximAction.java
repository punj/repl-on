/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action.exim;

import com.repl.bean.ShipmentRecord;
import com.repl.common.NewHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import com.repl.hibernate.OnionExport;
import java.util.List;
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
public class EximAction extends EximBean {// implements SessionAware, ModelDriven<User>{

    private EximBean eximBean = new EximBean();
    private ShipmentRecord shipments = new ShipmentRecord();
    private String minPrice, maxPrice, avgPrice, totalQtyShipment;
    private Session session;

    public EximAction() {
        session = NewHibernateUtil.getSessionFactory().openSession();
        eximBean.setIgnoreSearchOnWebsite(" -panjiva -52wmb");

    }

    @Override
    public String execute() throws Exception {

        if (null != request.getParameter("pageNo")) {
            System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("pageNo"));
            Integer pageno = Integer.parseInt(request.getParameter("pageNo"));
            eximBean.setPageNo(pageno);
        }

//        loadPageNos();
        loadCountries();
        loadDistinctConsigneeData();
        //  loadConsigneeDetailReport();

        return "view";
    }

    public void loadCountries() {
        System.out.println("%%%%%% loadCountries %%%%%%%");

        String query = "select distinct(destination_country) from onion_export \n"
                + "order by destination_country desc";
        System.out.println("loadDistinctConsigneeData SGN Query \n" + query);
        List<String> countryList = (List) session.createSQLQuery(query).list();
        eximBean.setCountries(countryList);
        //  session.close();
        // NewHibernateUtil.getSessionFactory().close();
    }

    public void loadPageNos() {
        System.out.println("%%%%%% loadPageNos %%%%%%%");
        List<Integer> pages = new ArrayList<>();
        // session = NewHibernateUtil.getSessionFactory().openSession();
        String query = "select \n"
                + "count(distinct(consignee_name)) Total\n"
                + "from onion_export \n"
                + "where is_contact_info_found like 'N' or is_contact_info_found is NULL\n"
                + "order by consignee_name ";
        System.out.println("loadPageNos SGN Query \n" + query);
        Integer countryList = Integer.valueOf(String.valueOf(session.createSQLQuery(query).list().get(0)));
        for (int i = 0; i < countryList - 1; i++) {
            pages.add(i);
        }
        eximBean.setTotalPageNos(pages);
        // eximBean.setPageNo(countryList);
        //  session.close();
        // NewHibernateUtil.getSessionFactory().close();
    }

    public void loadDistinctConsigneeData() {

//        System.out.println("SGN");
//        System.out.println("***************");
        // exim.setConsigneeCountry("SGN Country");
        // Session session = NewHibernateUtil.getSessionFactory().openSession();
        /*  String query = "select \n"
                + " distinct(consignee_name),\n"
                + "count(*) Total,\n" //1
                //                + " consignee_name, \n" //2
                + "consignee_country,\n" //3
                + "consignee_address,\n" //4
                + "is_contact_info_found,\n" //5
                + "web,\n" //6
                + "phone,\n" //7
                + "email,\n" //8 
                + "comment\n"//8
                + "from onion_export \n"
                + "\n "
                + "\n where is_contact_info_found is NULL "
                //                + "\n where is_contact_info_found like 'N' or is_contact_info_found is NULL "
                + "group by consignee_name  \n"
                + "order by consignee_name \n"
                + "limit " + eximBean.getPageNo() + ",1\n"
                + ";";*/
        String query = "select \n"
                + "\n"
                + "consignee_name,\n"
                + "count(*) Total,\n"
                + "consignee_country,\n"
                + "consignee_address,\n"
                + "is_contact_info_found,\n"
                + "web,\n"
                + "phone,\n"
                + "email,\n"
                + "comment,\n"
                + "sum(quantity) as qty,\n"
                + "unit,\n"
                + "-- avg(`fob_value_usd`),\n"
                + "concat(min(item_rate_inv_curr),' ',invoice_currency) MIN,\n"
                + "concat(avg(item_rate_inv_curr),' ',invoice_currency) AVG,\n"
                + "concat(max(item_rate_inv_curr),' ',invoice_currency) MAX,\n"
                + "concat(destination_port, ' ',destination_country),\n"
                + "(exporter_name),\n"
                + "exporter_address,\n"
                + "hasTooManyShipments\n"
                + "from onion_export\n"
                + "where\n"
                + "-- upper(consignee_name) like  UPPER('%ska%')  and \n"
                + "unit like 'MTS' \n"
                + "\n and is_contact_info_found is NULL "
                + "\n"
                + "group by consignee_name\n"
                + "  having qty >=50 and qty <=500 \n"
                + " order by qty desc"
                + " limit " + eximBean.getPageNo() + ",1\n"
                + ";";
        System.out.println("loadDistinctConsigneeData SGN Query \n" + query);
        List onions = (List) session.createSQLQuery(query).list();
        for (int i = 0; i < onions.size(); i++) {
            // System.out.println("" + onions.get(i));
            Object[] o = (Object[]) onions.get(i);
//            System.out.println("" + o[1]);

            if (null != o[1]) {
                eximBean.setTotal(Integer.parseInt(String.valueOf(o[1])));
            }

            if (null != o[0]) {

                eximBean.setConsigneeName((String.valueOf(o[0])));
            }

            if (null != o[2]) {
                eximBean.setConsigneeCountry((String.valueOf(o[2])));
            }

            if (null != o[3]) {
                eximBean.setConsigneeAddress((String.valueOf(o[3])));
            }

            if (null != o[4]) {
                eximBean.setIs_contact_info_found((String.valueOf(o[4])));
            }

            if (null != o[5]) {
                eximBean.setWeb((String.valueOf(o[5])));
            }

            if (null != o[6]) {
                eximBean.setPhone((String.valueOf(o[6])));
            }

            if (null != o[7]) {
                eximBean.setEmail((String.valueOf(o[7])));
            }

            if (null != o[8]) {
                eximBean.setComment((String.valueOf(o[8])));
            }
            if (null != o[9]) {
                eximBean.setHasTooManyShipments((String.valueOf(o[9])));
            }

        }
        // session.close();
    }

    public void loadConsigneeDetailReport() {
        try {
            if (null != request.getParameter("consigneeName")) {
                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("consigneeName"));
                String consigneeName = (request.getParameter("consigneeName"));
                eximBean.setConsigneeName(consigneeName);
            }
            //  System.out.println("SGN");
            List<ShipmentRecord> shipmentRecords = new ArrayList<>();
            System.out.println("***************");
            // exim.setConsigneeCountry("SGN Country");
            //   Session session = NewHibernateUtil.getSessionFactory().openSession();
//            getPriceInfo();
            String query = "#	Date	Last Name	Description	Exporter	POL	POD	Destination	Quantity	Price in Inr	FOB\n"
                    + "\n"
                    + "select \n"
                    + "DATE_FORMAT(sb_date,'%d/%m/%Y') 'Date', \n" //1
                    + "product_description 'Product_Description', \n" //2
                    + "exporter_name 'Exporter_Name',  \n" //3
                    + "exporter_city 'Exporter_City', \n" //4
                    + "concat( destination_port,', ',destination_country) 'Destination',\n" //5
                    + "concat(quantity,' ',unit) 'Quantity', \n" //6
                    + "unit_price_inr 'Price in INR',\n" //7 
                    + "fob_value_usd 'FOB in USD', \n" //
                    + "concat(ROUND(item_rate_inv_curr, 2),' ',invoice_currency) 'Unit_Price'  \n" //
                    + "from onion_export\n"
                    + "where UPPER(consignee_name) like UPPER('" + eximBean.getConsigneeName().trim() + "')\n"
                    //                + "where UPPER(consignee_name) like UPPER('%ABDUL LATIF FOODSTUFF TRADING LLC%')\n"
                    + "-- group by consignee_name, exporter_name \n"
                    + "order by consignee_name \n"
                    + " ";
            System.out.println("loadConsigneeDetailReport SGN Query \n" + query);

            String table = "<table id=\"tablePreview\" class=\"table table-striped table-hover table-sm table-bordered\">\n"
                    + "                    <!--Table head-->\n"
                    + "                    <thead>\n"
                    + "                        <tr>\n"
                    + "                            <th>#</th>\n"
                    + "                            <th>Date</th>\n"
                    + "                            <!--<th>Last Name</th>-->\n"
                    + "                            <th>Exporter</th>\n"
                    + "                            <th>City</th>\n"
                    + "                            <th>Description</th>\n"
                    + "                            <th>Destination</th>\n"
                    + "                            <!--<th>Destination</th>-->\n"
                    + "                            <th>Quantity</th>\n"
                    + "                            <th>Price in Inr</th>\n"
                    + "                            <th>FOB</th>\n"
                    + "                            <th>Unit Price</th>\n"
                    + "\n"
                    + "                        </tr>\n"
                    + "                    </thead>\n"
                    + "                    <!--Table head-->\n"
                    + "                    <!--Table body-->\n"
                    + "                    <tbody>";
            List onions = (List) session.createSQLQuery(query).list();
            int cnt = 1;
            for (int i = 0; i < onions.size(); i++) {
//            System.out.println("" + onions.get(i));
                Object[] o = (Object[]) onions.get(i);
//            System.out.println("INDEX *****>>>> " + i);
                shipments.setRowNumber(String.valueOf(i + 1));
                if (null != o[0]) {
                    shipments.setDate((String.valueOf(o[0])));
                }

                if (null != o[1]) {

                    shipments.setDescription(String.valueOf(o[1]));
                }

                if (null != o[2]) {
                    shipments.setExporterName(String.valueOf(o[2]));
                }

                if (null != o[3]) {
                    shipments.setExportersCity((String.valueOf(o[3])));
                }

                if (null != o[4]) {
                    shipments.setPortOfDestination((String.valueOf(o[4])));
                }
                if (null != o[5]) {
                    shipments.setQuantity((String.valueOf(o[5])));
                }
                if (null != o[6]) {
                    shipments.setPriceInINR((Double.valueOf(String.valueOf(o[6]))));
                }
                if (null != o[7]) {
                    shipments.setFOB((Double.valueOf(String.valueOf(o[7]))));
                }
                String unitPrice = "";
                if (null != o[8]) {
                    unitPrice = String.valueOf(o[8]);
                }
                //  System.out.println("%&*&*&*&*&*" + shipments.toString());

                //upto above line working well
                table += "<tr>\n"
                        + "                                <th scope=\"row\"> " + cnt + "</th>\n"
                        + "                                <td>" + shipments.getDate() + "</td>\n"
                        + "                                <td>" + shipments.getExporterName() + "</td>\n"
                        + "                                <td>" + shipments.getExportersCity() + "</td>\n"
                        + "                                <td>" + shipments.getDescription() + "</td>\n"
                        + "                                <td>" + shipments.getPortOfDestination() + "</td>\n"
                        + "                                <td>" + shipments.getQuantity() + "</td>\n"
                        + "                                <td>" + shipments.getPriceInINR() + "</td>\n"
                        + "                                <td>" + shipments.getFOB() + "</td>\n"
                        + "                                <td>" + unitPrice + "</td>\n"
                        + "\n"
                        + "                            </tr>";
                cnt++;
            }

            //  }
            table += "</tbody>\n"
                    + "                    <!--Table body-->\n"
                    + "                </table>";
            shipmentRecords.add(shipments);
            //  System.out.println("SZIE **&&& " + table);
            //   System.out.println("SZIE **&&& " +shipmentRecords.toString());

            eximBean.setShipmentsRecord(shipmentRecords);
//            System.out.println("SZIE **&&& " + eximBean.getShipmentsRecord().size());
            //  session.close();
            response.setBufferSize(32 * 1024); // 8K buffer
            response.setContentType("text/html");
            int size = response.getBufferSize();
            //  System.out.println("The default buffer size is " + size);

//            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = null;

            writer = response.getWriter();

//            writer.println(generateTable(eximBean.getShipmentsRecord()));
            writer.println(table);
        } catch (HibernateException | NumberFormatException | IOException e) {
            System.out.println("****** Exception *****");
            e.printStackTrace();
            System.out.println("****** Exception *****");

        }
        // return "detailReport";
    }

    public String updateConsigneeInfo() {
        try {
            System.out.println("+eximBean.isBlackListed()**** " + eximBean.getIsBlackListed());
            System.out.println("+eximBean.getConsigneeName()**** " + eximBean.getConsigneeName());
            System.out.println("+eximBean.getPageNo()**** " + eximBean.getPageNo());
            System.out.println("+eximBean.getPhone()**** " + eximBean.getPhone());
            System.out.println("getEmail**** " + eximBean.getEmail());
            System.out.println("getWeb**** " + eximBean.getWeb());
            System.out.println("getComment**** " + eximBean.getComment());
            System.out.println("getIs_contact_info_found**** " + eximBean.getIs_contact_info_found());
            System.out.println("hasTooManyShipments**** " + eximBean.getHasTooManyShipments());
            //  Session session = NewHibernateUtil.getSessionFactory().openSession();
            String query = "UPDATE OnionExport SET \n"
                    + "phone ='" + eximBean.getPhone() + "' ,\n"
                    + "comment = '" + eximBean.getComment() + "' ,\n"
                    + "web = '" + eximBean.getWeb() + "' ,\n"
                    + "email = '" + eximBean.getEmail() + "' ,\n"
                    + "is_contact_info_found = '" + eximBean.getIs_contact_info_found() + "' \n";
            if (!eximBean.getHasTooManyShipments().equalsIgnoreCase("-1")) {
                query += ",hasTooManyShipments = '" + eximBean.getHasTooManyShipments() + "' \n";
            }
            query += " WHERE UPPER(consigneeName) like UPPER('%" + eximBean.getConsigneeName() + "%')";
            System.out.println(" $$queryquery$$$$ " + query);
            Transaction t = session.beginTransaction();

            Query querys = session.createQuery(query);
            int rr = querys.executeUpdate();

          //  t.commit();
            //  session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "updated";
    }

    public EximBean getEximBean() {
        return eximBean;
    }

    public void setEximBean(EximBean eximBean) {
        this.eximBean = eximBean;
    }

    public ShipmentRecord getShipments() {
        return shipments;
    }

    public void setShipments(ShipmentRecord shipments) {
        this.shipments = shipments;
    }

    public String generateTable(List<ShipmentRecord> shipments) {
        String table = "<table id=\"tablePreview\" class=\"table table-striped table-hover table-sm table-bordered\">\n"
                + "                    <!--Table head-->\n"
                + "                    <thead>\n"
                + "                        <tr>\n"
                + "                            <th>#</th>\n"
                + "                            <th>Date</th>\n"
                + "                            <!--<th>Last Name</th>-->\n"
                + "                            <th>Exporter</th>\n"
                + "                            <th>City</th>\n"
                + "                            <th>Description</th>\n"
                + "                            <th>Destination</th>\n"
                + "                            <!--<th>Destination</th>-->\n"
                + "                            <th>Quantity</th>\n"
                + "                            <th>Price in Inr</th>\n"
                + "                            <th>FOB</th>\n"
                + "\n"
                + "                        </tr>\n"
                + "                    </thead>\n"
                + "                    <!--Table head-->\n"
                + "                    <!--Table body-->\n"
                + "                    <tbody>";
        int i = 1;
        for (ShipmentRecord shipment : shipments) {
            table += "<tr>\n"
                    + "                                <th scope=\"row\"> " + i++ + "</th>\n"
                    + "                                <td>" + shipment.getDate() + "</td>\n"
                    + "                                <td>" + shipment.getExporterName() + "</td>\n"
                    + "                                <td>" + shipment.getExportersCity() + "</td>\n"
                    + "                                <td>" + shipment.getDescription() + "</td>\n"
                    + "                                <td>" + shipment.getPortOfDestination() + "</td>\n"
                    + "                                <td>" + shipment.getQuantity() + "</td>\n"
                    + "                                <td>" + shipment.getPriceInINR() + "</td>\n"
                    + "                                <td>" + shipment.getFOB() + "</td>\n"
                    + "\n"
                    + "                            </tr>";
        }
        table += "</tbody>\n"
                + "                    <!--Table body-->\n"
                + "                </table>";
        return table;
    }

    public void getPriceInfos() {
        try {
            if (null != request.getParameter("consigneeName")) {
                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("consigneeName"));
                String consigneeName = (request.getParameter("consigneeName"));
                eximBean.setConsigneeName(consigneeName);
            }
            System.out.println("PRice Info");
            String query = "select \n"
                    + "round(max(unit_price_usd),2) max_price\n"
                    + ",round(avg(unit_price_usd),2) avg_price,\n"
                    + "round(min(unit_price_usd),2) min_price,\n"
                    + "sum(quantity) total_qty "
                    + "from onion_export \n"
                    + "where UPPER(consignee_name) like UPPER('%" + eximBean.getConsigneeName() + "%')\n"
                    + "and UPPER(unit) like 'MTS'";
            System.out.println("queryqueryqueryqueryquery " + query);

//            setMaxPrice("12344");
//            maxPrice = "12";
//            setAvgPrice("12344");
//            setMinPrice("12344");
            List priceInfo = (List) session.createSQLQuery(query).list();
            JSONObject obj = new JSONObject();
            System.out.println("priceInfo.size()priceInfo.size() " + priceInfo.size());

            for (int i = 0; i < priceInfo.size(); i++) {
                System.out.println("***&  " + i);

                Object[] o = (Object[]) priceInfo.get(i);
                System.out.println("***22222&  " + o[0]);

                if (null != o[0]) {
                    System.out.println("Max Price" + o[0]);
                    obj.put("maxPrice", (String.valueOf(o[0])));
                }
                if (null != o[1]) {
                    System.out.println("Avg Price" + o[1]);
                    obj.put("avgPrice", (String.valueOf(o[1])));

//                setAvgPrice(123d);
                }
                if (null != o[2]) {
                    System.out.println("Min Price" + o[2]);
                    obj.put("minPrice", (String.valueOf(o[2])));

                }
                if (null != o[3]) {
                    System.out.println("Total Qty " + o[3]);
                    obj.put("totalQty", (String.valueOf(o[3])) + " MTS");

                }
            }
            System.out.println("PRICES " + obj.toJSONString());

            PrintWriter writer = null;

            writer = response.getWriter();

//            writer.println(generateTable(eximBean.getShipmentsRecord()));
            writer.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "";
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getTotalQtyShipment() {
        return totalQtyShipment;
    }

    public void setTotalQtyShipment(String totalQtyShipment) {
        this.totalQtyShipment = totalQtyShipment;
    }

    public void autocomplete() throws IOException {
        try {
            String phrase = "";
            System.out.println("autcomplete");
            if (null != request.getParameter("phrase")) {
                System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("phrase"));
                phrase = (request.getParameter("phrase"));

            }
            String query = "select distinct(consignee_name) \n"
                    + "from onion_export \n"
                    + "where UPPER(consignee_name) like UPPER('" + phrase + "%')\n"
                    + "order by consignee_name;";

            System.out.println("queryqueryqueryqueryquery " + query);

//            setMaxPrice("12344");
//            maxPrice = "12";
//            setAvgPrice("12344");
//            setMinPrice("12344");
            List priceInfo = (List) session.createSQLQuery(query).list();
            JSONObject obj = new JSONObject();
            System.out.println("priceInfo.size()priceInfo.size() " + priceInfo.size());
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < priceInfo.size(); i++) {
                System.out.println("^^^&&& " + priceInfo.get(i));

//            Object[] o = (Object[]) priceInfo.get(i);
//            System.out.println("***22222&  " + String.valueOf(o[0]));
                JSONObject studentJSON = new JSONObject();
                studentJSON.put("name", String.valueOf(priceInfo.get(i)));
//            studentJSON.put("age", o[1]);
                jsonArray.add(studentJSON);
            }
            // obj.put("StudentList", jsonArray);
            //  obj.put(obj, NONE);
           // System.out.println("^^^^^^^ " + jsonArray.toString());
            PrintWriter writer = null;

            writer = response.getWriter();

            writer.println(jsonArray.toString());
//            writer.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

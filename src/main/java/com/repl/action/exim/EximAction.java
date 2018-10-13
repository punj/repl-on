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

/**
 *
 * @author Punj
 */
public class EximAction extends EximBean {// implements SessionAware, ModelDriven<User>{

    private EximBean eximBean = new EximBean();
    private ShipmentRecord shipments = new ShipmentRecord();

    public EximAction() {
        eximBean.setIgnoreSearchOnWebsite(" -panjiva -52wmb");
    }

    @Override
    public String execute() throws Exception {

        if (null != request.getParameter("pageNo")) {
            System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("pageNo"));
            Integer pageno = Integer.parseInt(request.getParameter("pageNo"));
            eximBean.setPageNo(pageno);
        }
        System.out.println("");
//        loadPageNos();
//        loadCountries();
        loadDistinctConsigneeData();
        //  loadConsigneeDetailReport();

        return "view";
    }

    public void loadCountries() {
        System.out.println("%%%%%% loadCountries %%%%%%%");
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String query = "select distinct(destination_country) from onion_export \n"
                + "order by destination_country desc";
        System.out.println("loadDistinctConsigneeData SGN Query \n" + query);
        List<String> countryList = (List) session.createSQLQuery(query).list();
        eximBean.setCountries(countryList);
        session.close();
        // NewHibernateUtil.getSessionFactory().close();
    }

    public void loadPageNos() {
        System.out.println("%%%%%% loadPageNos %%%%%%%");
        List<Integer> pages = new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
        session.close();
        // NewHibernateUtil.getSessionFactory().close();
    }

    public void loadDistinctConsigneeData() {

//        System.out.println("SGN");
//        System.out.println("***************");
        // exim.setConsigneeCountry("SGN Country");
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String query = "select \n"
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
                + ";";
        System.out.println("loadDistinctConsigneeData SGN Query \n" + query);
        List onions = (List) session.createSQLQuery(query).list();
        for (int i = 0; i < onions.size(); i++) {
            System.out.println("" + onions.get(i));
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

        }
        session.close();
    }

    public void loadConsigneeDetailReport() {
 try {
        if (null != request.getParameter("consigneeName")) {
            System.out.println("**PAGE NO PARAMETER VALUE****  " + request.getParameter("consigneeName"));
            String consigneeName = (request.getParameter("consigneeName"));
            eximBean.setConsigneeName(consigneeName);
        }
        System.out.println("SGN");
        List<ShipmentRecord> shipmentRecords = new ArrayList<>();
        System.out.println("***************");
        // exim.setConsigneeCountry("SGN Country");
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
                + "fob_value_usd 'FOB in USD' \n" //
                + "from onion_export\n"
                + "where UPPER(consignee_name) like UPPER('" + eximBean.getConsigneeName().trim() + "')\n"
                //                + "where UPPER(consignee_name) like UPPER('%ABDUL LATIF FOODSTUFF TRADING LLC%')\n"
                + "-- group by consignee_name, exporter_name \n"
                + "order by consignee_name \n"
                + " ";
        System.out.println("loadConsigneeDetailReport SGN Query \n" + query);
        List onions = (List) session.createSQLQuery(query).list();
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
            shipmentRecords.add(shipments);

        }
        eximBean.setShipmentsRecord(shipmentRecords);
        System.out.println("SZIE **&&& " + eximBean.getShipmentsRecord().size());
        session.close();

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = null;
       
            writer = response.getWriter();

            writer.println(generateTable(eximBean.getShipmentsRecord()));
        } catch (HibernateException | NumberFormatException | IOException e) {
            System.out.println("****** Exception *****");
            e.printStackTrace();
            System.out.println("****** Exception *****");

        }
        // return "detailReport";
    }

    public String updateConsigneeInfo() {
        try {
            System.out.println("+eximBean.getConsigneeName()**** " + eximBean.getConsigneeName());
            System.out.println("+eximBean.getPageNo()**** " + eximBean.getPageNo());
            System.out.println("+eximBean.getPhone()**** " + eximBean.getPhone());
            System.out.println("getEmail**** " + eximBean.getEmail());
            System.out.println("getWeb**** " + eximBean.getWeb());
            System.out.println("getComment**** " + eximBean.getComment());
            System.out.println("getIs_contact_info_found**** " + eximBean.getIs_contact_info_found());
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            String query = "UPDATE OnionExport SET \n"
                    + "phone ='" + eximBean.getPhone() + "' ,\n"
                    + "comment = '" + eximBean.getComment() + "' ,\n"
                    + "web = '" + eximBean.getWeb() + "' ,\n"
                    + "email = '" + eximBean.getEmail() + "' ,\n"
                    + "is_contact_info_found = '" + eximBean.getIs_contact_info_found() + "' \n"
                    + " WHERE UPPER(consigneeName) like UPPER('%" + eximBean.getConsigneeName() + "%')";
            System.out.println(" $$queryquery$$$$ " + query);
            Transaction t = session.beginTransaction();

            Query querys = session.createQuery(query);
            int rr = querys.executeUpdate();

            t.commit();
            session.close();
        } catch (HibernateException e) {
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

}

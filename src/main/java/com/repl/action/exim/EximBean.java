/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action.exim;

import com.repl.bean.ShipmentRecord;
import com.repl.common.ReplPagingBeanAll;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Punj
 */
public class EximBean extends ReplPagingBeanAll{
    
    private String consigneeName;
    private Integer total;
    private String consigneeCountry;
    private String consigneeAddress;
    private String is_contact_info_found;
    private String isBlackListed;
    private String phone;
    private String email;
    private String web;
    private String comment;
    private Integer pageNo;
    private List<ShipmentRecord> shipmentsRecord = new ArrayList<>(); 
    private List<String> countries = new ArrayList<>();
    private String searchCountry;
    
//    private String searchCountryWise;
    private String searchConsigneeName;
    private String orderByTotal;
    
    private List<Integer> totalPageNos;
    
    private String ignoreSearchOnWebsite;
    
    private String hasTooManyShipments;
    
   // private Double minPrice=0d, maxPrice=0d, avgPrice=0d;
    //private String totalQty="0 MTS";
    
    /* + "is_contact_info_found,\n" //4
                + "phone,\n" //4
                + "email,\n" //4
                + "web,\n" //4
                + "comment\n" //4 */

    public EximBean() {
        if(null==pageNo || pageNo <0){
           pageNo=0;
        }

    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getConsigneeCountry() {
        return consigneeCountry;
    }

    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getIs_contact_info_found() {
        return is_contact_info_found;
    }

    public void setIs_contact_info_found(String is_contact_info_found) {
        this.is_contact_info_found = is_contact_info_found;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<ShipmentRecord> getShipmentsRecord() {
        return shipmentsRecord;
    }

    public void setShipmentsRecord(List<ShipmentRecord> shipmentsRecord) {
        this.shipmentsRecord = shipmentsRecord;
    }

    public String getSearchConsigneeName() {
        return searchConsigneeName;
    }

    public void setSearchConsigneeName(String searchConsigneeName) {
        this.searchConsigneeName = searchConsigneeName;
    }

    

    public String getOrderByTotal() {
        return orderByTotal;
    }

    public void setOrderByTotal(String orderByTotal) {
        this.orderByTotal = orderByTotal;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getSearchCountry() {
        return searchCountry;
    }

    public void setSearchCountry(String searchCountry) {
        this.searchCountry = searchCountry;
    }

    public List getCmbPages() {
        return cmbPages;
    }

    public void setCmbPages(List cmbPages) {
        this.cmbPages = cmbPages;
    }

    public int getDisplayRecords() {
        return displayRecords;
    }

    public void setDisplayRecords(int displayRecords) {
        this.displayRecords = displayRecords;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getFromRecord() {
        return fromRecord;
    }

    public void setFromRecord(int fromRecord) {
        this.fromRecord = fromRecord;
    }

    public List getTableData() {
        return tableData;
    }

    public void setTableData(List tableData) {
        this.tableData = tableData;
    }

    public int getToRecord() {
        return toRecord;
    }

    public void setToRecord(int toRecord) {
        this.toRecord = toRecord;
    }

    public int getCmbPage() {
        return cmbPage;
    }

    public void setCmbPage(int cmbPage) {
        this.cmbPage = cmbPage;
    }

    public int getDispRecords() {
        return dispRecords;
    }

    public void setDispRecords(int dispRecords) {
        this.dispRecords = dispRecords;
    }

    
    

    public List<Integer> getTotalPageNos() {
        return totalPageNos;
    }

    public void setTotalPageNos(List<Integer> totalPageNos) {
        this.totalPageNos = totalPageNos;
    }

    public String getIgnoreSearchOnWebsite() {
        return ignoreSearchOnWebsite;
    }

    public void setIgnoreSearchOnWebsite(String ignoreSearchOnWebsite) {
        this.ignoreSearchOnWebsite = ignoreSearchOnWebsite;
    }

    public String getHasTooManyShipments() {
        return hasTooManyShipments;
    }

    public void setHasTooManyShipments(String hasTooManyShipments) {
        this.hasTooManyShipments = hasTooManyShipments;
    }

    public String getIsBlackListed() {
        return isBlackListed;
    }

    public void setIsBlackListed(String isBlackListed) {
        this.isBlackListed = isBlackListed;
    }

    @Override
    public String toString() {
        return "EximBean{" + "consigneeName=" + consigneeName + ", total=" + total + ", consigneeCountry=" + consigneeCountry + ", consigneeAddress=" + consigneeAddress + ", is_contact_info_found=" + is_contact_info_found + ", isBlackListed=" + isBlackListed + ", phone=" + phone + ", email=" + email + ", web=" + web + ", comment=" + comment + ", pageNo=" + pageNo + ", shipmentsRecord=" + shipmentsRecord + ", countries=" + countries + ", searchCountry=" + searchCountry + ", searchConsigneeName=" + searchConsigneeName + ", orderByTotal=" + orderByTotal + ", totalPageNos=" + totalPageNos + ", ignoreSearchOnWebsite=" + ignoreSearchOnWebsite + ", hasTooManyShipments=" + hasTooManyShipments + '}';
    }
    
}

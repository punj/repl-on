package com.repl.hibernate;
// Generated Oct 5, 2018 5:00:22 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * OnionExport generated by hbm2java
 */
public class OnionExport  implements java.io.Serializable {


     private Integer id;
     private Integer sbNo;
     private Date sbDate;
     private Integer hsCode;
     private String productDescription;
     private Double quantity;
     private String unit;
     private Double fobValueTotalInr;
     private Double amtInForeignCurrency;
     private Double fobValueInr;
     private Double unitPriceInr;
     private Double fobValueUsd;
     private Double unitPriceUsd;
     private Double exchangerRateUsd;
     private Double valueInvCurr;
     private Double itemRateInvCurr;
     private String invoiceCurrency;
     private String exporterName;
     private String exporterAddress;
     private String exporterCity;
     private String exporterPin;
     private String exporterState;
     private String consigneeName;
     private String consigneeAddress;
     private String consigneeCountry;
     private String destinationPort;
     private String destinationCountry;
     private String foreignRegion;
     private String invoiceNumber;
     private Integer itemNumber;
     private String chaName;
     private String chaPan;
     private String iec;
     private Integer hsCode2Digit;
     private Integer hsCode4Digit;
     private String indianPort;
     private String shipmentMode;
     private String indianRegions;
     private String contactInfo;
     private String comment;
     private String commuincatedEver;
     private String isContactInfoFound;
     private String phone;
     private String web;
     private String email;
     private String isIrrelevant;

    public OnionExport() {
    }

    public OnionExport(Integer sbNo, Date sbDate, Integer hsCode, String productDescription, Double quantity, String unit, Double fobValueTotalInr, Double amtInForeignCurrency, Double fobValueInr, Double unitPriceInr, Double fobValueUsd, Double unitPriceUsd, Double exchangerRateUsd, Double valueInvCurr, Double itemRateInvCurr, String invoiceCurrency, String exporterName, String exporterAddress, String exporterCity, String exporterPin, String exporterState, String consigneeName, String consigneeAddress, String consigneeCountry, String destinationPort, String destinationCountry, String foreignRegion, String invoiceNumber, Integer itemNumber, String chaName, String chaPan, String iec, Integer hsCode2Digit, Integer hsCode4Digit, String indianPort, String shipmentMode, String indianRegions, String contactInfo, String comment, String commuincatedEver, String isContactInfoFound, String phone, String web, String email, String isIrrelevant) {
       this.sbNo = sbNo;
       this.sbDate = sbDate;
       this.hsCode = hsCode;
       this.productDescription = productDescription;
       this.quantity = quantity;
       this.unit = unit;
       this.fobValueTotalInr = fobValueTotalInr;
       this.amtInForeignCurrency = amtInForeignCurrency;
       this.fobValueInr = fobValueInr;
       this.unitPriceInr = unitPriceInr;
       this.fobValueUsd = fobValueUsd;
       this.unitPriceUsd = unitPriceUsd;
       this.exchangerRateUsd = exchangerRateUsd;
       this.valueInvCurr = valueInvCurr;
       this.itemRateInvCurr = itemRateInvCurr;
       this.invoiceCurrency = invoiceCurrency;
       this.exporterName = exporterName;
       this.exporterAddress = exporterAddress;
       this.exporterCity = exporterCity;
       this.exporterPin = exporterPin;
       this.exporterState = exporterState;
       this.consigneeName = consigneeName;
       this.consigneeAddress = consigneeAddress;
       this.consigneeCountry = consigneeCountry;
       this.destinationPort = destinationPort;
       this.destinationCountry = destinationCountry;
       this.foreignRegion = foreignRegion;
       this.invoiceNumber = invoiceNumber;
       this.itemNumber = itemNumber;
       this.chaName = chaName;
       this.chaPan = chaPan;
       this.iec = iec;
       this.hsCode2Digit = hsCode2Digit;
       this.hsCode4Digit = hsCode4Digit;
       this.indianPort = indianPort;
       this.shipmentMode = shipmentMode;
       this.indianRegions = indianRegions;
       this.contactInfo = contactInfo;
       this.comment = comment;
       this.commuincatedEver = commuincatedEver;
       this.isContactInfoFound = isContactInfoFound;
       this.phone = phone;
       this.web = web;
       this.email = email;
       this.isIrrelevant = isIrrelevant;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSbNo() {
        return this.sbNo;
    }
    
    public void setSbNo(Integer sbNo) {
        this.sbNo = sbNo;
    }
    public Date getSbDate() {
        return this.sbDate;
    }
    
    public void setSbDate(Date sbDate) {
        this.sbDate = sbDate;
    }
    public Integer getHsCode() {
        return this.hsCode;
    }
    
    public void setHsCode(Integer hsCode) {
        this.hsCode = hsCode;
    }
    public String getProductDescription() {
        return this.productDescription;
    }
    
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public Double getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Double getFobValueTotalInr() {
        return this.fobValueTotalInr;
    }
    
    public void setFobValueTotalInr(Double fobValueTotalInr) {
        this.fobValueTotalInr = fobValueTotalInr;
    }
    public Double getAmtInForeignCurrency() {
        return this.amtInForeignCurrency;
    }
    
    public void setAmtInForeignCurrency(Double amtInForeignCurrency) {
        this.amtInForeignCurrency = amtInForeignCurrency;
    }
    public Double getFobValueInr() {
        return this.fobValueInr;
    }
    
    public void setFobValueInr(Double fobValueInr) {
        this.fobValueInr = fobValueInr;
    }
    public Double getUnitPriceInr() {
        return this.unitPriceInr;
    }
    
    public void setUnitPriceInr(Double unitPriceInr) {
        this.unitPriceInr = unitPriceInr;
    }
    public Double getFobValueUsd() {
        return this.fobValueUsd;
    }
    
    public void setFobValueUsd(Double fobValueUsd) {
        this.fobValueUsd = fobValueUsd;
    }
    public Double getUnitPriceUsd() {
        return this.unitPriceUsd;
    }
    
    public void setUnitPriceUsd(Double unitPriceUsd) {
        this.unitPriceUsd = unitPriceUsd;
    }
    public Double getExchangerRateUsd() {
        return this.exchangerRateUsd;
    }
    
    public void setExchangerRateUsd(Double exchangerRateUsd) {
        this.exchangerRateUsd = exchangerRateUsd;
    }
    public Double getValueInvCurr() {
        return this.valueInvCurr;
    }
    
    public void setValueInvCurr(Double valueInvCurr) {
        this.valueInvCurr = valueInvCurr;
    }
    public Double getItemRateInvCurr() {
        return this.itemRateInvCurr;
    }
    
    public void setItemRateInvCurr(Double itemRateInvCurr) {
        this.itemRateInvCurr = itemRateInvCurr;
    }
    public String getInvoiceCurrency() {
        return this.invoiceCurrency;
    }
    
    public void setInvoiceCurrency(String invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }
    public String getExporterName() {
        return this.exporterName;
    }
    
    public void setExporterName(String exporterName) {
        this.exporterName = exporterName;
    }
    public String getExporterAddress() {
        return this.exporterAddress;
    }
    
    public void setExporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
    }
    public String getExporterCity() {
        return this.exporterCity;
    }
    
    public void setExporterCity(String exporterCity) {
        this.exporterCity = exporterCity;
    }
    public String getExporterPin() {
        return this.exporterPin;
    }
    
    public void setExporterPin(String exporterPin) {
        this.exporterPin = exporterPin;
    }
    public String getExporterState() {
        return this.exporterState;
    }
    
    public void setExporterState(String exporterState) {
        this.exporterState = exporterState;
    }
    public String getConsigneeName() {
        return this.consigneeName;
    }
    
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }
    public String getConsigneeAddress() {
        return this.consigneeAddress;
    }
    
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }
    public String getConsigneeCountry() {
        return this.consigneeCountry;
    }
    
    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }
    public String getDestinationPort() {
        return this.destinationPort;
    }
    
    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }
    public String getDestinationCountry() {
        return this.destinationCountry;
    }
    
    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }
    public String getForeignRegion() {
        return this.foreignRegion;
    }
    
    public void setForeignRegion(String foreignRegion) {
        this.foreignRegion = foreignRegion;
    }
    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }
    
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public Integer getItemNumber() {
        return this.itemNumber;
    }
    
    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }
    public String getChaName() {
        return this.chaName;
    }
    
    public void setChaName(String chaName) {
        this.chaName = chaName;
    }
    public String getChaPan() {
        return this.chaPan;
    }
    
    public void setChaPan(String chaPan) {
        this.chaPan = chaPan;
    }
    public String getIec() {
        return this.iec;
    }
    
    public void setIec(String iec) {
        this.iec = iec;
    }
    public Integer getHsCode2Digit() {
        return this.hsCode2Digit;
    }
    
    public void setHsCode2Digit(Integer hsCode2Digit) {
        this.hsCode2Digit = hsCode2Digit;
    }
    public Integer getHsCode4Digit() {
        return this.hsCode4Digit;
    }
    
    public void setHsCode4Digit(Integer hsCode4Digit) {
        this.hsCode4Digit = hsCode4Digit;
    }
    public String getIndianPort() {
        return this.indianPort;
    }
    
    public void setIndianPort(String indianPort) {
        this.indianPort = indianPort;
    }
    public String getShipmentMode() {
        return this.shipmentMode;
    }
    
    public void setShipmentMode(String shipmentMode) {
        this.shipmentMode = shipmentMode;
    }
    public String getIndianRegions() {
        return this.indianRegions;
    }
    
    public void setIndianRegions(String indianRegions) {
        this.indianRegions = indianRegions;
    }
    public String getContactInfo() {
        return this.contactInfo;
    }
    
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getCommuincatedEver() {
        return this.commuincatedEver;
    }
    
    public void setCommuincatedEver(String commuincatedEver) {
        this.commuincatedEver = commuincatedEver;
    }
    public String getIsContactInfoFound() {
        return this.isContactInfoFound;
    }
    
    public void setIsContactInfoFound(String isContactInfoFound) {
        this.isContactInfoFound = isContactInfoFound;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getWeb() {
        return this.web;
    }
    
    public void setWeb(String web) {
        this.web = web;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIsIrrelevant() {
        return this.isIrrelevant;
    }
    
    public void setIsIrrelevant(String isIrrelevant) {
        this.isIrrelevant = isIrrelevant;
    }




}



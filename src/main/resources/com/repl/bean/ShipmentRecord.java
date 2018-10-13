/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.bean;

/**
 *
 * @author Punj
 */

public class ShipmentRecord{
/* 
    
#	Date	Last Name	Description	Exporter	POL	POD	Destination	Quantity	Price in Inr	FOB
    */
    String rowNumber;
    String date;
    String exporterName;
    String description;
    String exportersCity;
    String portOfDestination; //POD
    String destination;
    String quantity;
    Double priceInINR;
    Double FOB;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExporterName() {
        return exporterName;
    }

    public void setExporterName(String exporterName) {
        this.exporterName = exporterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

  
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getPriceInINR() {
        return priceInINR;
    }

    public void setPriceInINR(Double priceInINR) {
        this.priceInINR = priceInINR;
    }

    public Double getFOB() {
        return FOB;
    }

    public void setFOB(Double FOB) {
        this.FOB = FOB;
    }

    public String getExportersCity() {
        return exportersCity;
    }

    public void setExportersCity(String exportersCity) {
        this.exportersCity = exportersCity;
    }

    public String getPortOfDestination() {
        return portOfDestination;
    }

    public void setPortOfDestination(String portOfDestination) {
        this.portOfDestination = portOfDestination;
    }

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    public String toString() {
        return "ShipmentRecord{" + "rowNumber=" + rowNumber + ", date=" + date + ", exporterName=" + exporterName + ", description=" + description + ", exportersCity=" + exportersCity + ", portOfDestination=" + portOfDestination + ", destination=" + destination + ", quantity=" + quantity + ", priceInINR=" + priceInINR + ", FOB=" + FOB + '}';
    }

    
     
    
    
            
}
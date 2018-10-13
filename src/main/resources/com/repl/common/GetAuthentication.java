/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.common;

/**
 *
 * @author admin
 */
public class GetAuthentication {
 
     private static final String ALPHA_NUM =  
               "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
       
       public static void main(String[] args) {  
          GetAuthentication grs = new GetAuthentication();  
          System.out.println(grs.getAlphaNumeric(7));  
          System.out.println(grs.getAlphaNumeric(16));  
       }  
       public String getAlphaNumeric(int len) {
           int k=3;
          StringBuffer sb = new StringBuffer(len);  
          
          for (int i=0;  i<len;  i++) {  
             int ndx = (int)(Math.random()*ALPHA_NUM.length()); 
              if (len == 16) {
                  if (i < (len - 4 * k)) {
                      sb.append(ALPHA_NUM.charAt(ndx));
                  } else {
                      sb.append("-");
                      i = i - 1;
                      k = k - 1;
                  }
              }
              if (len == 7) {
                  sb.append(ALPHA_NUM.charAt(ndx));
              }
            
          }  
          return sb.toString();  
       }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 *
 * @author Punj
 */
public class ValidationAction extends ActionSupport {
    private String email;
    private String password;
 
    @Override
    public String execute() {
        if (email != null && email.equals("admin@codejava.net")) {
            return SUCCESS;        
        } else {
            return INPUT;
        }
    }
     
    @RequiredStringValidator(message = "Please enter your e-mail address.")
    @EmailValidator(message = "Please enter a valid e-mail address.")
    public void setEmail(String email) {
        this.email = email;
    }
     
    @RequiredStringValidator(message = "Please enter your password.")
    public void setPassword(String password) {
        this.password = password;
    }
     
    public String getEmail() {
        return email;
    }
     
    public String getPassword() {
        return password;
    }
}

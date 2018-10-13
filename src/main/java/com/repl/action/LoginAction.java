/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action;

/**
 *
 * @author Punj
 */
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.repl.bean.User;
import com.repl.common.ReplPagingBeanAll;
import java.util.Locale;

public class LoginAction extends ReplPagingBeanAll  {

    private static final long serialVersionUID = -3369875299120377549L;

    @Override
    public String execute() {

//        System.out.println("inside execute " + user.getPassword() + " " + user.getUser() + " " + user.getUserName());
        Locale locale = new Locale("DE");
        ActionContext.getContext().setLocale(locale);
        
            return "success";
        
    }

   
  
}

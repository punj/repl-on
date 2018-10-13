/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.repl.bean.User;
import com.repl.interceptor.UserAware;

/**
 *
 * @author Punj
 */
public class TestAction extends ActionSupport implements UserAware //, ModelDriven<User>
{

    public String sgn() throws Exception
    {
        System.out.println("sgn  ");
        // messageStore = new MessageStore() ;
        return SUCCESS;
    }

    public String customerForm() throws Exception
    {
        System.out.println("sgn customerForm ");
        // messageStore = new MessageStore() ;
        return "customerForm";
    }

    private User user;

    @Override
    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUser(User user)
    {
        return this.user;
    }

  /*  @Override
    public User getModel()
    {
        return this.user;
    }*/

}

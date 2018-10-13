/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.repl.bean.User;
import java.util.Map;

/**
 *
 * @author Punj
 */
public class AuthenticationInterceptor implements Interceptor
{

    private static final long serialVersionUID = -5011962009065225959L;

    @Override
    public void destroy()
    {
        //release resources here
    }

    @Override
    public void init()
    {
        // create resources here
    }

    /*@Override
     public String intercept(ActionInvocation actionInvocation)
     throws Exception {
     System.out.println("inside auth interceptor");
     Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
		
     User user = (User) sessionAttributes.get("USER");
		
     if(user == null){
     return Action.LOGIN;
     }else{
     Action action = (Action) actionInvocation.getAction();
     if(action instanceof UserAware){
     ((UserAware) action).setUser(user);
     }
     return actionInvocation.invoke();
     }
     }*/
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
        System.out.println("inside auth interceptor");
        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();

        User user = (User) sessionAttributes.get("USER");

        if (user == null)
        {
            System.out.println("sgn user is null");
            return "login";
        } else
        {            System.out.println("sgn user is not null");

            Action action = (Action) actionInvocation.getAction();
            if (action instanceof UserAware)
            {
                ((UserAware) action).setUser(user);
            }
            return actionInvocation.invoke();
        }
    }

}

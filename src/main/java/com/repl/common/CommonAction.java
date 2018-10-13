package com.repl.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class CommonAction extends ActionSupport implements ServletRequestAware,
        ServletResponseAware {

//    protected static final Logger logger = Logger.getLogger("CommonAction");
    protected GenericDAO genericDAO = GenericDAO.getInstance();
    public HttpServletRequest request ;
    public HttpServletResponse response;

    public CommonAction()
    {
         request = ServletActionContext.getRequest();
         System.out.println("sgn request **&&** "+getServletRequest());
    }

    
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public final HttpServletRequest getServletRequest() {
        return request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getServletResponse() {
        return response;
    }
}

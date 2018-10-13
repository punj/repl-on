package com.repl.common;

import javax.servlet.http.HttpSession;

public class ReplAction extends CommonAction  {

	public String execute() {
		HttpSession session = request.getSession();
		//session.setMaxInactiveInterval(120);
	//	System.out.println("max inactive  time " +session.getMaxInactiveInterval() + "  \n last accesd time " + session.getLastAccessedTime());
		
		if (session.isNew() || session.getMaxInactiveInterval() > 30 ) {
		//	System.out.println("seesion is **********NEW**********");
			return "welcome";
		}
		else
		{
			//System.out.println("`````````````````else``````````````````````````````");
			return null;
		}
	}

}

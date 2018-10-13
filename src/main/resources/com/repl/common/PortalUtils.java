package com.repl.common;

import freemarker.core.ReturnInstruction;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

public class PortalUtils extends CommonAction {

    private static final ThreadLocal<PortalUtils> threadLocal = new ThreadLocal<PortalUtils>();
    private static PortalUtils portalUtils = null;

    public PortalUtils() {
    }

    public static PortalUtils getInstance() {
        portalUtils = threadLocal.get();

        if (null == portalUtils) {
            portalUtils = new PortalUtils();
            threadLocal.set(portalUtils);
        }

        return portalUtils;
    }

    public void initUserInfo(HttpSession session, GenericDAO genericDAO) {
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        String strQuery = "select userid,themeid from usermaster where empid="
                + userBean.empid + " and nvl(isactive,'Y')='Y'";
        List<Object[]> dataList = genericDAO.getDataFromQuery(strQuery);

        strQuery = "select themename,backgroundimage,cssname,mudralogo from thememaster where themeid="
                + userBean.themeid + " and nvl(isactive,'Y')='Y'";
        UserBean newuserBean = new UserBean();
        if (dataList.size() > 0) {
            Object[] objData = dataList.get(0);
            newuserBean.userid = userBean.userid;
            newuserBean.themeid = ((BigDecimal) objData[1]).longValue();
            newuserBean.empid = userBean.empid;
        }
        dataList = genericDAO.getDataFromQuery(strQuery);
        if (dataList.size() > 0) {
            Object[] objData = dataList.get(0);
            newuserBean.themename = objData[0].toString();
            newuserBean.backgroundimage = objData[1].toString();
            newuserBean.cssname = objData[2].toString();
            newuserBean.mudralogo = objData[3].toString();
        }
        strQuery = "select menuid,menuname,url from menumaster where nvl(isactive,'Y')='Y' and (menutype = 'C' or menuid = 6) and menuid "
                + "in(select distinct menuid from userpermissiondetail where userid="
                + newuserBean.userid + ") order by sortorder";
        newuserBean.menuList = genericDAO.getDataFromQuery(strQuery);
        session.setAttribute("userBean", newuserBean);
    }

    public void initUserInfoA(HttpSession session, GenericDAO genericDAO) {

        UserBean userBean = (UserBean) session.getAttribute("userBean");
        String strQuery = "select userid,themeid from usermaster where empid="
                + userBean.empid + " and nvl(isactive,'Y')='Y'";
        List<Object[]> dataList = genericDAO.getDataFromQuery(strQuery);

        strQuery = "select themename,backgroundimage,cssname,mudralogo from thememaster where themeid="
                + userBean.themeid + " and nvl(isactive,'Y')='Y'";
        UserBean newuserBean = new UserBean();
        if (dataList.size() > 0) {
            Object[] objData = dataList.get(0);
            newuserBean.userid = userBean.userid;
            newuserBean.themeid = ((BigDecimal) objData[1]).longValue();
            newuserBean.empid = userBean.empid;
        }
        dataList = genericDAO.getDataFromQuery(strQuery);
        if (dataList.size() > 0) {
            Object[] objData = dataList.get(0);
            newuserBean.themename = objData[0].toString();
            newuserBean.backgroundimage = objData[1].toString();
            newuserBean.cssname = objData[2].toString();
            newuserBean.mudralogo = objData[3].toString();
        }
        strQuery = "select menuid,menuname,url from menumaster where nvl(isactive,'Y')='Y' and (menutype = 'A' and menuid <> 6 and menuid "
                + "in(select distinct menuid from userpermissiondetail where userid="
                + newuserBean.userid + ")) order by sortorder";
        newuserBean.menuList = genericDAO.getDataFromQuery(strQuery);
        session.setAttribute("userBean", newuserBean);
    }

    public String getTxnnumber() {
        Calendar calendar = Calendar.getInstance();
        String year = "" + calendar.get(Calendar.YEAR);
        String month = null;
        int month1 = calendar.get(Calendar.MONTH);
        ;
        String day = "" + calendar.get(Calendar.DATE);
        String hour = "" + calendar.get(Calendar.HOUR);
        String minutes = "" + calendar.get(Calendar.MINUTE);
        String second = "" + calendar.get(Calendar.SECOND);
        System.out.println("month1 (1) ---> " + month1);
        StringBuilder sbNumber = new StringBuilder(year);
        // if (month1 == 1)
        month1 = month1 + 1;
        System.out.println("month1 (2) ---> " + month1);
        month = "0" + month1;
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (second.length() == 1) {
            second = "0" + second;
        }
        sbNumber.append(month);
        sbNumber.append(day);
        sbNumber.append(hour);
        sbNumber.append(minutes);
        sbNumber.append(second);

        return (sbNumber.toString());
    }

    public String getEmpIdAuto() {
        int yr;
        Calendar calendar = Calendar.getInstance();
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("username");
        String minutes = "" + calendar.get(Calendar.MINUTE);
        String second = "" + calendar.get(Calendar.SECOND);
        yr = calendar.get(Calendar.YEAR);
        yr = yr - 2000;
        String empid;
        String month = null;
        int month1 = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        StringBuilder sbNumber = new StringBuilder(yr);
        System.out.println(yr);
        // if (month1 == 1)
        month1 = month1 + 1;
        month = "0" + month1;
        empid = "" + yr + month + date + minutes + second;

        sbNumber.append(month);
        System.out.println(empid);
        return (empid);
    }
}

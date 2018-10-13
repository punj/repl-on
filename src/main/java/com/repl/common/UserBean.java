package com.repl.common;

import java.util.List;

public class UserBean {

	public String themename, backgroundimage, cssname, mudralogo, empid;
	public Long themeid, userid;
	public List<Object[]> menuList;

	public Long getThemeid() {
		return themeid;
	}

	public void setThemeid(Long themeid) {
		this.themeid = themeid;
	}

	public String getThemename() {
		return themename;
	}

	public void setThemename(String themename) {
		this.themename = themename;
	}

	public String getBackgroundimage() {
		return backgroundimage;
	}

	public void setBackgroundimage(String backgroundimage) {
		this.backgroundimage = backgroundimage;
	}

	public String getCssname() {
		return cssname;
	}

	public void setCssname(String cssname) {
		this.cssname = cssname;
	}

	public String getMudralogo() {
		return mudralogo;
	}

	public void setMudralogo(String mudralogo) {
		this.mudralogo = mudralogo;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public List<Object[]> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Object[]> menuList) {
		this.menuList = menuList;
	}

}

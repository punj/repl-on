package com.repl.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplPagingBean extends ReplAction {

	protected List cmbSerachList;

	protected List activeList;

	protected List cmbPages = new ArrayList();

	protected String[] tableheaders;

	protected List tableData;

	protected String cmbWith;

	protected String cmbSearch;

	protected String searchText;

	protected static int displayRecords;

	protected int totalRecords;

	protected static int currentPage;

	protected int totalPages;

	protected int fromRecord;

	protected int toRecord;

	protected int cmbPage;

	protected int sortField;

	protected String orderBy;

	protected String isduplicate;

	private String linkid;

	protected String addright;

	protected String editright;

	protected String deleteright;

	protected String viewright;

	public String getAddright() {
		return addright;
	}

	public void setAddright(String addright) {
		this.addright = addright;
	}

	public String getDeleteright() {
		return deleteright;
	}

	public void setDeleteright(String deleteright) {
		this.deleteright = deleteright;
	}

	public String getEditright() {
		return editright;
	}

	public void setEditright(String editright) {
		this.editright = editright;
	}

	public String getViewright() {
		return viewright;
	}

	public void setViewright(String viewright) {
		this.viewright = viewright;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getIsduplicate() {
		return isduplicate;
	}

	public void setIsduplicate(String isduplicate) {
		this.isduplicate = isduplicate;
	}

	public String getOrderBy() {
		if (null == orderBy || orderBy.length() == 0)
			orderBy = "asc";
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getSortField() {
		return sortField;
	}

	public void setSortField(int sortField) {
		this.sortField = sortField;
	}

	public int getCmbPage() {
		return cmbPage;
	}

	public void setCmbPage(int cmbPage) {
		this.cmbPage = cmbPage;
	}

	public List getCmbPages() {
		return cmbPages;
	}

	public void setCmbPages(List cmbPages) {
		this.cmbPages = cmbPages;
	}

	public ReplPagingBean() {
		activeList = new ArrayList();
		activeList.add(new String[] { "Y", "Yes" });
		activeList.add(new String[] { "N", "No" });

	}

	public String getCmbSearch() {
		return cmbSearch;
	}

	public void setCmbSearch(String cmbSearch) {
		this.cmbSearch = cmbSearch;
	}

	public List getCmbSerachList() {
		return cmbSerachList;
	}

	public void setCmbSerachList(List cmbSerachList) {
		this.cmbSerachList = cmbSerachList;
	}

	public String getCmbWith() {
		return cmbWith;
	}

	public void setCmbWith(String cmbWith) {
		this.cmbWith = cmbWith;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getDisplayRecords() {
		return displayRecords;
	}

	public void setDisplayRecords(int displayRecords) {
		this.displayRecords = displayRecords;
	}

	public int getFromRecord() {
		return fromRecord;
	}

	public void setFromRecord(int fromRecord) {
		this.fromRecord = fromRecord;
	}

	public String[] getTableheaders() {
		return tableheaders;
	}

	public void setTableheaders(String[] tableheaders) {
		this.tableheaders = tableheaders;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List getTableData() {
		return tableData;
	}

	public void setTableData(List tableData) {
		this.tableData = tableData;
	}

	public int getToRecord() {
		return toRecord;
	}

	public void setToRecord(int toRecord) {
		this.toRecord = toRecord;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public void calculatePageStatus() {

		totalRecords = null == tableData ? 0 : tableData.size();
		if (displayRecords == 0)
			displayRecords = 20;
		int nopage = totalRecords / displayRecords;
		double pages = ((double) totalRecords) / displayRecords;
		if (pages - nopage > 0)
			nopage++;

		fromRecord = (currentPage - 1) * displayRecords + 1;
		if (displayRecords > totalRecords)
			fromRecord = 1;
		toRecord = fromRecord + displayRecords - 1;
		totalPages = nopage;
		if (totalPages == 1)
			currentPage = 1;
		if (totalPages > 1) {
			cmbPages.clear();
			for (int i = 1; i <= totalPages; i++) {
				cmbPages.add(new String[] { "" + i,
						" Page " + i + " of " + nopage });

			}
		}
		cmbPage = currentPage;

	}

	public static void copyData(ReplPagingBean sourceForm) {
		displayRecords = sourceForm.getDisplayRecords();
		currentPage = sourceForm.getCurrentPage();
	}

	public List getActiveList() {
		return activeList;
	}

	public void setActiveList(List activeList) {
		this.activeList = activeList;
	}

	public String sortData() {
		if (null != super.execute())
			return super.execute();
		int orderby = -1;
		if (getOrderBy().equals("asc")) {
			orderby = 1;
		}
		ReplListComparator mudraListComparator = new ReplListComparator(
				getSortField(), orderby);
		ReplPagingBean mudraPagingBean = (ReplPagingBean) request
				.getSession().getAttribute("mainform");
		tableData = mudraPagingBean.tableData;
		tableheaders = mudraPagingBean.tableheaders;
		Collections.sort(tableData, mudraListComparator);
		copyData(mudraPagingBean);
		calculatePageStatus();
		return "input";
	}
}

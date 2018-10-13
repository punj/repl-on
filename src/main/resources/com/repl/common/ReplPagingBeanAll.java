package com.repl.common;

import java.util.ArrayList;
import java.util.List;

public class ReplPagingBeanAll extends PortalUtils {

	protected List cmbPages = new ArrayList();

	protected int displayRecords;

	protected int totalRecords;

	protected int currentPage;

	protected int totalPages;

	protected int fromRecord;
	
	protected List tableData;

	protected int toRecord;

	protected int cmbPage; 
	
	public int dispRecords = 10;

	public void calculatePageStatus(List<Object[]> tableData) {
		if(currentPage==0)
			currentPage=1;
		totalRecords = null == tableData ? 0 : tableData.size();
		if (displayRecords == 0)
			displayRecords = dispRecords;
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
	
	public void calculatePageStatus(List<Object[]> tableData,int disp) {
		if(currentPage==0)
			currentPage=1;
		totalRecords = null == tableData ? 0 : tableData.size();
		if (displayRecords == 0)
			displayRecords = disp;
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

	public List getCmbPages() {
		return cmbPages;
	}

	public void setCmbPages(List cmbPages) {
		this.cmbPages = cmbPages;
	}

	public int getDisplayRecords() {
		return displayRecords;
	}

	public void setDisplayRecords(int displayRecords) {
		this.displayRecords = displayRecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getFromRecord() {
		return fromRecord;
	}

	public void setFromRecord(int fromRecord) {
		this.fromRecord = fromRecord;
	}

	public int getToRecord() {
		return toRecord;
	}

	public void setToRecord(int toRecord) {
		this.toRecord = toRecord;
	}

	public int getCmbPage() {
		return cmbPage;
	}

	public void setCmbPage(int cmbPage) {
		this.cmbPage = cmbPage;
	}

	public List getTableData() {
		return tableData;
	}

	public void setTableData(List tableData) {
		this.tableData = tableData;
	}

}

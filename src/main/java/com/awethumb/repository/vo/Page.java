package com.awethumb.repository.vo;

public class Page {
	private int pageIndex;
	private int pageCount;
	
	// 아무것도 없는 페이징
	public Page() {
		this.pageCount = 10;
		this.pageIndex = 0;
	}
	

	// default 페이징 10개씩
	public Page(int pageIndex) {
		this.pageCount = 10;
		this.pageIndex = (pageIndex - 1) * pageCount;
	}
	
	// 받은 수 만큼 페이징
	public Page(int pageIndex, int pageCount) {
		this.pageCount = pageCount;
		this.pageIndex = (pageIndex - 1) * pageCount;
	}
	


	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = (pageIndex - 1) * this.pageCount;
	}



	@Override
	public String toString() {
		return "Page [pageIndex=" + pageIndex + ", pageCount=" + pageCount + "]";
	}
	
	
}

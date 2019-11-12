package com.awethumb.util;

public class PageResult {
	private int pageNo;
	private int count;
	
	private int beginPage;
	private int endPage;
	
	private boolean prev;
	private boolean next;
	
	public PageResult(int pageNo, int count) {
		this(pageNo, count, 10, 5);
	}
	public PageResult(int pageNo, int count, int listSize, int tabSize) {
		this.pageNo = pageNo;
		this.count = count;
		
		int lastPage = (int)Math.ceil(count / (double)listSize);
		int currTab = (pageNo - 1) / tabSize + 1;
		beginPage = (currTab - 1) * tabSize + 1;
		endPage = currTab * tabSize;
		if (endPage > lastPage) endPage = lastPage;
		prev =  beginPage != 1;
		next =  endPage != lastPage;

		
	}
	
	public int getPageNo() { return pageNo; }
	public int getcount() { return count; }
	public int getBeginPage() { return beginPage; }
	public int getendPage() { return endPage; }
	public boolean getprev() { return prev; }
	public boolean getNext() { return next; }
}










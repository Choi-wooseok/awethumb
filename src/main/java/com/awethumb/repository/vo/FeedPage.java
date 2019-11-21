package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class FeedPage {
	private int pageIndex;
	private int pageCount = 5;
}

package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class VisitCount {
	 private int visit_id;
     private String visit_ip;
     private String visit_time;
     private String visit_refer;
     private String visit_agent;
}

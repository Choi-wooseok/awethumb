package com.awethumb.admin.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.admin.service.AdminService;
import com.awethumb.repository.vo.Block;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Criteria;
import com.awethumb.repository.vo.PageMaker;
import com.awethumb.repository.vo.Report;

@Controller("com.awethumb.admin.controller.AdminController")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@RequestMapping("/adminMain.do")
	public void adminMain() {
	};

	@RequestMapping("/reportList.do")
	public void reportList() {
	};

	@RequestMapping("/reportListAjax.do")
	@ResponseBody
	public List<Report> reportListAjax() {
		List<Report> rList = service.getReport();
		/* 스크립트 단에서 편리한 출력을 위해 리스트에 데이터를 가공해서 주입. */
		for (Report r : rList) {
			r.setUserId(service.selectUserId(r.getUserNo()));
			r.setReportReason(service.selectReportReason(r.getBlockCode()));
			if (r.getBlockCode() == 5) {
				r.setReportReason(r.getReportContent());
			}
			String reportTitle = service.selectPostContent(r.getPostNo());
			reportTitle = (reportTitle.length() > 20) ? reportTitle.substring(0, 19) : reportTitle;
			r.setReportTitle(reportTitle);
			List<Block> blist = service.selectBlock(r.getUserNo());
			if (blist.size() != 0) {
				r.setBlockEnabled("Y");
			} else {
				r.setBlockEnabled("N");
			}
		}
		return rList;
	}

	@RequestMapping("/originPostAjax.do")
	@ResponseBody
	public Report originPostAjax(String reportNo) {
		// 유저, 보드, 코멘트를 포함한 리포트를 넘겨버리기.
		Report report = service.selectOneReport(Integer.parseInt(reportNo));
		if (report.getCmtNo() != 0) {
			Comment comm = service.selectOneComment(report);
			System.out.println("리포트로 커멘트셀렉 찍어봄" + comm);
			report.setComment(comm);
			report.setCommentUser(service.selectOneUserByComment(comm));
		}
		report.setBoard(service.selectOneBoard(report));
		report.setUserVO(service.selectOneUser(report));

//		String originContent = service.selectPostContent(report.getPostNo());
//		System.out.println(originContent);
		List<Block> blist = service.selectBlock(report.getUserNo());
		if (blist.size() != 0) {
			report.setBlockEnabled("Y");
		} else {
			report.setBlockEnabled("N");
		}
		return report;
	}

	@RequestMapping("/blockUser.do")
	@ResponseBody
	public List<Report> blockUser(@RequestBody Map<String, Object> map) {
		Map<String, String> rmap = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		rmap.put("blockDate", map.get("blockDate").toString());
		rmap.put("reportNo", map.get("reportNo").toString());
		int reportNo = Integer.parseInt(map.get("reportNo").toString());
		Report report = service.selectOneReport(reportNo);
		int userNo = report.getUserNo();
		rmap.put("userNo", Integer.toString(userNo));
		int blockCode = report.getBlockCode();
		rmap.put("blockCode", Integer.toString(blockCode));
		return insertAndUpdateBlock(rmap);
	}

	@RequestMapping("/denyblock.do")
	public void denyBlock(String reportNo) {
		service.denyReportStatus(Integer.parseInt(reportNo));
	}

	@RequestMapping("/cancelBlock.do")
	@ResponseBody
	public List<Report> cancelBlock(String reportNo) {
		Report report = service.selectOneReport(Integer.parseInt(reportNo));
		int userNo = report.getUserNo();
		return service.deleteBlock(userNo);
	}

	@Transactional
	@ResponseBody
	public List<Report> insertAndUpdateBlock(Map<String, String> rmap) {
		service.updateReportStatus(rmap);
		return service.insertBlock(rmap);
	}
	
	@RequestMapping("/reportListAjaxPaging.do")
	@ResponseBody
	public Map<String, Object> reportListAjaxPaging(Criteria cri) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.reportCount());
		
		List<Report> rList = service.selectReportPaging(cri);
		/* 스크립트 단에서 편리한 출력을 위해 리스트에 데이터를 가공해서 주입. */
		for (Report r : rList) {
			r.setUserId(service.selectUserId(r.getUserNo()));
			r.setReportReason(service.selectReportReason(r.getBlockCode()));
			if (r.getBlockCode() == 5) {
				r.setReportReason(r.getReportContent());
			}
			String reportTitle = service.selectPostContent(r.getPostNo());
			reportTitle = (reportTitle.length() > 20) ? reportTitle.substring(0, 19) : reportTitle;
			r.setReportTitle(reportTitle);
			List<Block> blist = service.selectBlock(r.getUserNo());
			if (blist.size() != 0) {
				r.setBlockEnabled("Y");
			} else {
				r.setBlockEnabled("N");
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("rList", rList);
		map.put("pageMaker", pageMaker);
		return map;
	}
	 

}

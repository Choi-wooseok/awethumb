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
import com.awethumb.repository.vo.UserVO;
import com.awethumb.stats.service.StatsService;

@Controller("com.awethumb.admin.controller.AdminController")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private StatsService statsService;

	@RequestMapping("/adminMain.do")
	public ModelAndView adminMain() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("totalVisitToday", statsService.totalVisitToday());
		mav.addObject("totalPostToday", statsService.totalPostToday());
		mav.addObject("totalUserToday", statsService.totalUserToday());
		
		//30일 통계에 필요한 데이터 삽입
		mav.addObject("monthlyStats", statsService.selectDailyLogOneMonth());
//		System.out.println(statsService.selectDailyLogOneMonth().get("logDtList"));
		mav.setViewName("admin/adminMain");
		return mav;
	};

	@RequestMapping("/userManageAjax.do")
	@ResponseBody
	public Map<String, Object> userManageAjax(int userNo) {
		// 여기서 리절트에 json으로 담아줘야 하는 정보는
		// 번호에 해당하는 user객체
		// 그 유저가 정지중인지의 여부와 정지중이라면 정지마감날짜
		// 번호로 유저를 뽑아야하고, 블락테이블에 유저no로 접근하는 메서드 사용해야함.

		Map<String, Object> result = new HashMap<String, Object>();

		UserVO user = service.selectOneUserUsingUserNo(userNo);
		result.put("user", user);
		Block block = service.selectBlock(userNo);
		if (block != null) {
			result.put("block", block);
		}
		;
		return result;
	}

	@RequestMapping("/manageUser.do")
	public void manageUser() {

	};

	@RequestMapping("/userListAjaxPaging.do")
	@ResponseBody
	public Map<String, Object> userListAjaxPaging(Criteria cri) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.userCount());

		// 유저하나하나마다 그 자신과 연관된 정지상태와 정지기한이 있다. 편의를 위해 UserVO에 이 정보를 연결하고 출력할때 같이 뽑아준다.

		List<UserVO> uList = service.selectUserPaging(cri);
		/* 스크립트 단에서 편리한 출력을 위해 리스트에 데이터를 가공해서 주입. */
		// 필요한 정보 :회원번호/회원아이디/정지상태/정지 기한
		// 회원번호를 이용해 Block테이블에서 Block을 골라서 각각의 UserVO에 해당하는 Block을 세팅해준다.
		for (UserVO u : uList) {
			if (service.selectBlock(u.getUserNo()) != null) {
				u.setBlock(service.selectBlock(u.getUserNo()));
				u.setBlockEnabled("Y");
			} else {
				u.setBlockEnabled("N");
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("uList", uList);
		map.put("pageMaker", pageMaker);
		return map;
	}

	@RequestMapping("/updateBlock.do")
	@ResponseBody
	public Map<String, String> updateBlock(@RequestBody Map<String, Object> map) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		System.out.println(map.get("userNo"));
		System.out.println(map.get("userNo").toString());
		System.out.println(Integer.parseInt(map.get("userNo").toString()));

		Block block = service.selectBlock(Integer.parseInt(map.get("userNo").toString()));
		System.out.println("block :" + block);
		String endDt = map.get("blockDate").toString();
		if (block != null) {
			rmap.put("userNo", Integer.parseInt(map.get("userNo").toString()));
			rmap.put("blockDate", endDt);
			service.updateBlcok(rmap);
			Map<String, String> result = new HashMap<>();
			result.put("action", "이용정지 기간 정정 처리되었습니다.");
			return result;
		} else {
			rmap.put("userNo", Integer.parseInt(map.get("userNo").toString()));
			rmap.put("blockDate", endDt);
			service.insertBlockByAdmin(rmap);
			Map<String, String> result = new HashMap<>();
			result.put("action", "이용정지 처리되었습니다.");
			return result;
		}
	};

	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public void deleteUser(int userNo) {
		service.deleteUser(userNo);
	}

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
			if (reportTitle != null) {
				reportTitle = (reportTitle.length() > 20) ? reportTitle.substring(0, 19) : reportTitle;
			} else {
				reportTitle = "삭제된 게시물 입니다.";
			}
			r.setReportTitle(reportTitle);
			Block b = service.selectBlock(r.getUserNo());
			if (b != null) {
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

		Block b = service.selectBlock(report.getUserNo());
		if (b != null) {
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

	@RequestMapping("/denyBlock.do")
	@ResponseBody
	public String denyBlock(String reportNo) {
		int realNo = Integer.parseInt(reportNo);
		service.denyReportStatus(realNo);
		Report report = service.selectOneReport(realNo);
		int userNo = report.getUserNo();
		String userId = service.selectUserId(userNo);
		return userId;
	}

	@RequestMapping("/cancelBlock.do")
	@ResponseBody
	public List<Report> cancelBlock(String reportNo) {
		Report report = service.selectOneReport(Integer.parseInt(reportNo));
		int userNo = report.getUserNo();
		return service.deleteBlock(userNo);
	}

	@RequestMapping("/cancelBlockByUserNo.do")
	@ResponseBody
	public List<Report> cancelBlockByUserNo(String userNo) {
		return service.deleteBlock(Integer.parseInt(userNo));
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
			if (reportTitle == null) {
				reportTitle = "삭제된 게시물 입니다.";
			} else {
				reportTitle = (reportTitle.length() > 20) ? reportTitle.substring(0, 19) : reportTitle;
			}
			r.setReportTitle(reportTitle);
			Block b = service.selectBlock(r.getUserNo());
			if (b != null) {
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

	@RequestMapping("/deleteReport.do")
	@ResponseBody
	public void deleteReport(int reportNo) {
		service.deleteReport(reportNo);
	}

}

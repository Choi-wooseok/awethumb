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

import com.awethumb.admin.service.AdminService;
import com.awethumb.repository.vo.Block;
import com.awethumb.repository.vo.Comment;
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

	/* 페이징 관련 */

	
	/*
	 * @RequestMapping("/list") public String list(HttpServletRequest request) {
	 * PageMaker pagemaker = new PageMaker(); String pagenum =
	 * request.getParameter("pagenum");//페이지 값을 입력 받는다. String contentnum =
	 * request.getParameter("contentnum");//한 페이지에 몇개 보일지
	 * System.out.println(contentnum); int cpagenum =
	 * Integer.parseInt(pagenum);//int 형으로 몇 번 페이지인지 페이지 값 형변환 int ccontentnum =
	 * Integer.parseInt(contentnum);//int 형으로 한 페이지에 몇개 보일지 값 형변환
	 * 
	 * ---------페이지 객체에 새로운 정보 다시 지정해주는 부분------------------
	 * pagemaker.setTotalcount(mapper.testcount());//전체 게시글 개수 지정한다
	 * pagemaker.setPagenum(cpagenum-1);//현재 페이지를 페이지 객체에 다시 지정해준다//몇번 페이지인지
	 * PageMaker에 세팅한다 pagemaker.setContentnum(ccontentnum);//한 페이지에 몇개씩 보여줄지 세팅한다
	 * pagemaker.setCurrentblock(cpagenum);//현재 페이지블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
	 * pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서
	 * 정한다 ---------페이지 객체에 새로운 정보 다시 지정해주는 부분------------------
	 * 
	 * pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표 나타낼지 결정한다
	 * pagemaker.setStartPage(pagemaker.getCurrentblock());//시작페이지 번호를 현재 페이지 블록으로
	 * 정한다
	 * pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
	 * //현재 블록 번호와 마지막 블록 번호를 보내서 대조하고 페이지 블록의 마지막 번호를 지정한다
	 * 
	 * List<Test> testlist = new ArrayList<Test>();//게시글 담을 리스트 선언 testlist =
	 * mapper.testlist(pagemaker.getPagenum()*10, pagemaker.getContentnum());//리스트에
	 * 저장 //매퍼로 한 페이지에 몇개 보일지 ,몇번 페이지 인지 전달//매퍼.xml 에서 사용하기 위해서 곱하기 10을 한다
	 * 
	 * request.setAttribute("list", testlist);//sql로 얻은 리스트를 .jsp페이지로 전달
	 * request.setAttribute("page", pagemaker);//페이지 번호 객체 .jsp페이지로 전달 return
	 * "list";
	 */
	  
	  
	 

}

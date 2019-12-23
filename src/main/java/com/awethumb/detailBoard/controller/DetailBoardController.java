package com.awethumb.detailBoard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.detailBoard.service.DetailBoardService;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectSubscribe;
import com.awethumb.repository.vo.UserVO;
import com.awethumb.util.FileUtil;

@Controller("com.awethumb.detailBoard.controller.DetailBoardController")
@RequestMapping("/detailProject")
public class DetailBoardController {

	@Autowired
	private DetailBoardService service;

	@GetMapping("/{projectNo}")
	public ModelAndView DetailBoardList(@PathVariable int projectNo, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("detailProject/detailBoardList");
		// 페이지 들어 왔을 경우 공유 권한을 가진 유저인지 확인
		List<Integer> shUsers = service.selectProjectShared(projectNo);
		
		// jsp에 projectVO를 넘기기 위함
		Project project = service.selectProject(projectNo);

		// 세션에 이미지 임시 등록을 위함
		HttpSession session = req.getSession();
		List<BoardFile> bfList = new ArrayList<>();
		session.setAttribute("bfList", bfList);
		
		// jsp에 BoardListVO를 넘기기 위함
		List<Board> bList = service.selectBoardList(projectNo);
		for (int i = 0; i < bList.size(); i++) {
			List<BoardFile> fList = service.selectImgList(bList.get(i).getPostNo());
			bList.get(i).setListFile(fList);
		}
		mav.addObject("shardUsers", shUsers);
		mav.addObject("project", project);
		mav.addObject("list", bList);
		return mav;
	}

	@RequestMapping("write.do")
	public String insertBoard(Board board, HttpServletRequest req) {
		int pjtNo = board.getProjectNo();
		HttpSession session = req.getSession();
		List<BoardFile> bfList = (List<BoardFile>) session.getAttribute("bfList");
		board.setListFile(bfList);
		service.insertBoard(board);
		int postNo = service.postNoSelect(); 
		for (int i = 0; i < bfList.size(); i++ ) {
			BoardFile bfile = bfList.get(i);
			bfile.setPostNo(postNo);
			service.insertImage(bfile);
		}
		session.removeAttribute("bfList");
		return "redirect:" + pjtNo;
	}

	@PostMapping("delete.do")
	public String deleteBoard(int postNo, int pjtNo) {
		service.deleteBoard(postNo);
		return "redirect:" + pjtNo;
	}

	@RequestMapping("updateListForm.do")
	public void updateListForm(@RequestParam("projectNo") int projectNo, Model model, HttpServletRequest req) {
		// jsp에 projectVO를 넘기기 위함
		Project project = service.selectProject(projectNo);

		List<Board> bList = service.selectBoardList(projectNo);
		for (int i = 0; i < bList.size(); i++) {
			List<BoardFile> fList = service.selectImgList(bList.get(i).getPostNo());
			bList.get(i).setListFile(fList);
		}
		model.addAttribute("project", project);
		model.addAttribute("list", bList);
	}
	
	@RequestMapping("updateSelectOneBoard.do")
	@ResponseBody
	public Board updateSelectOneBoard(@RequestParam("postNo") int postNo) {
		return service.selectOneBoard(postNo);
	}
	
	@RequestMapping("update.do")
	public String updateBoard(Board board) {
		int pjtNo = board.getProjectNo();
		service.updateBoard(board);
		return "redirect:updateListForm.do?projectNo=" + pjtNo;
	}

	@RequestMapping("updateList.do")
	@ResponseBody
	public void updateList(int postNo, int x_coord, int y_coord, int width, int hight) {
		service.updateList(postNo, x_coord, y_coord, width, hight);
	}

	@RequestMapping("selectOneBoard.do")
	@ResponseBody
	public Board selectOneBoard(int postNo) {
		service.viewCount(postNo);
		Board board = service.selectOneBoard(postNo);
		UserVO writer = service.selectWriter(postNo);
		board.setWriter(writer.getUserName());
		return board;
	}

//	@SuppressWarnings("unchecked")
	@PostMapping("imageUpload.do")
	@ResponseBody
	public BoardFile imageUpload (
			@RequestParam("file") MultipartFile file,
			HttpServletResponse res,
			HttpServletRequest req) throws Exception {

		FileUtil fu = new FileUtil();
		BoardFile bfile = fu.UploadImage(file, res);			
		bfile.setUrl(req.getContextPath() + "/image/" + bfile.getBoardFilePath() + bfile.getBoardFileSysName());
		HttpSession session = req.getSession();
		List<BoardFile> bfList = (List<BoardFile>)session.getAttribute("bfList");
		bfList.add(bfile);
		session.setAttribute("bfList", bfList);
		return bfile;
	};
	
	@RequestMapping("imageDownload.do")
	@ResponseBody
	public List<String> imageDownload(@RequestParam("postNo") int postNo,
			HttpServletRequest req,
			HttpServletResponse res
			) throws Exception  {
		List<BoardFile> bfList = service.selectImages(postNo);
		List<String> sArr = new ArrayList<>();;
		for (int i = 0; i < bfList.size(); i++) {
			String path = bfList.get(i).getBoardFilePath();
			String sysName = bfList.get(i).getBoardFileSysName();
			String realPath = req.getContextPath()  + "/image/" + path + sysName;
			sArr.add(realPath);
		}
		return sArr;
	}

	@PostMapping("updateProjectName.do")
	public String updateProjectName(int pjtNo, String pjtName) {
		Project pjt = service.selectProjectName(pjtNo);
		pjt.setProjectTitle(pjtName);
		service.updateProjectName(pjt);
		return "redirect:updateListForm.do?projectNo=" + pjtNo;
	}
	
	@RequestMapping("selectProjectName.do")
	@ResponseBody
	public Project selectProjectName(@RequestParam("projectNo") int projectNo) {
		return service.selectProject(projectNo);
	}

	@RequestMapping("selectCommentList.do")
	@ResponseBody
	public List<Comment> commentList(@RequestParam("postNo") int postNo) {
		List<Comment> comments = service.commentList(postNo);
		for (int i = 0; i < comments.size(); i++) {
			Comment uVo = comments.get(i);
			uVo.setCmtUserNickname(service.selectUser(uVo.getUserNo()));
		}
		return comments;
	}

	@RequestMapping("insertComment.do")
	@ResponseBody
	public void insertComment(Comment comment) {
		service.insertComment(comment);
	}

	@RequestMapping("deleteComment.do")
	@ResponseBody
	public void deleteComment(@RequestParam("cmtNo") int cmtNo) {
		service.deleteComment(cmtNo);
	}

	@RequestMapping("updateComment.do")
	@ResponseBody
	public void updateComment(Comment comment) {
		service.updateComment(comment);
	}
	
	@RequestMapping("selectSavedProject.do")
	@ResponseBody
	public int selectSavedProject(ProjectSubscribe pSub) {
		return service.selectSavedProject(pSub);
	}
	
	@RequestMapping("savedProject.do")
	@ResponseBody
	public void insertSavedProject(ProjectSubscribe pSub) {
		service.insertSavedProject(pSub);
	}
	
	@RequestMapping("deletesavedProject.do")
	@ResponseBody
	public void deleteSavedProject(ProjectSubscribe pSub) {
		service.deleteSavedProject(pSub);
	}
}

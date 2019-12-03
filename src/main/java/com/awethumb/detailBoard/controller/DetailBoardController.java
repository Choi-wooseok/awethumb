package com.awethumb.detailBoard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.awethumb.repository.vo.UserVO;
import com.awethumb.util.FileUploadUtil;

@Controller("com.awethumb.detailBoard.controller.DetailBoardController")
@RequestMapping("/detailProject")
public class DetailBoardController {
	
	@Autowired
	private DetailBoardService service;
	
	@GetMapping("/{projectNo}")
	public ModelAndView DetailBoardList(@PathVariable int projectNo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("detailProject/detailBoardList");
		
		List<Board> bList = service.selectBoardList(projectNo);
		for (int i = 0; i < bList.size(); i++) {
			List<BoardFile> fList = service.selectImgList(bList.get(i).getPostNo());
			bList.get(i).setListFile(fList);
		}
		mav.addObject("list", bList);
		return mav;
	}
	
	@RequestMapping("write.do")
	public String insertBoard(Board board) {
		int pjtNo = board.getProjectNo();
		service.insertBoard(board);
		return "redirect:" + pjtNo;
	}
	
	@PostMapping("delete.do")
	public String deleteBoard(int postNo, int pjtNo) {
		service.deleteBoard(postNo);
		return "redirect:"+pjtNo;
	}
	
	@RequestMapping("updateListForm.do")
	public void updateListForm(@RequestParam("projectNo") int projectNo, Model model) {
		List<Board> bList = service.selectBoardList(projectNo);
		for (int i = 0; i < bList.size(); i++) {
			List<BoardFile> fList = service.selectImgList(bList.get(i).getPostNo());
			bList.get(i).setListFile(fList);
		}
		model.addAttribute("list", bList);
		model.addAttribute("projectNo", projectNo);
	}

	@RequestMapping("update.do")
	public String updateBoard(Board board) {
		int pjtNo = board.getProjectNo();
		service.updateBoard(board);
		return "redirect:updateListForm.do?projectNo="+pjtNo;
	}
	
	@RequestMapping("updateList.do")
	@ResponseBody
	public void updateList(int postNo, int x_coord, int y_coord, int width, int hight) {
		service.updateList(postNo, x_coord, y_coord, width, hight);
	}
	
	@RequestMapping("selectOneBoard.do")
	@ResponseBody
	public Board selectOneBoard(int postNo) {
		Board board = service.selectOneBoard(postNo);
		UserVO writer = service.selectWriter(postNo);
		board.setWriter(writer.getUserName());
		return board;
	}
	
	@PostMapping("imageUpload.do")
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		try {
			int postNo = service.postNoSelect();
			System.out.println("postNo = " + postNo);
			BoardFile uploadedFile = FileUploadUtil.store(file, postNo, req);
			service.insertImage(uploadedFile);
			return ResponseEntity.ok().body(uploadedFile.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("selectProjectName.do")
	@ResponseBody
	public Project selectProjectName(@RequestParam("pjtNo") int pjtNo) {
		return service.selectProjectName(pjtNo);
	}
	
	@PostMapping("updateProjectName.do")
	public String updateProjectName(int pjtNo, String pjtName) {
		Project pjt = service.selectProjectName(pjtNo);
		pjt.setProjectTitle(pjtName);
		service.updateProjectName(pjt);
		return "redirect:updateListForm.do?projectNo="+pjtNo;
	}
	
	@RequestMapping("selectCommentList.do")
	@ResponseBody
	public List<Comment> commentList(@RequestParam("postNo") int postNo) {
		return service.commentList(postNo);
	}
	
	@RequestMapping("insertComment.do")
	@ResponseBody
	public void insertComment(Comment comment) {
		comment.setUserNo(service.selectWriter(comment.getPostNo()).getUserNo());
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
}

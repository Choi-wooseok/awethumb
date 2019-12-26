package com.awethumb.api.like.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awethumb.api.like.service.APILikeService;
import com.awethumb.repository.vo.Like;

@RequestMapping("/api/like")
@RestController
public class APILikeController {
	@Autowired
	private APILikeService service;
	
	
	@PostMapping
	public void LikeInsert(Like like){ 
		service.insertLike(like);
	}
	@DeleteMapping
	public void LikeDelete(Like like){ 
		service.deleteLike(like);
	}
	
	@GetMapping
	public int LikeCheck(Like like){
		return service.likeCheck(like);
	}
	
	@GetMapping("/count")
	public int LikeCount(Like like){
		return service.likeCount(like);
	}
	
}






















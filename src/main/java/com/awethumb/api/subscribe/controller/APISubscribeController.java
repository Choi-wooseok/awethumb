package com.awethumb.api.subscribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.awethumb.api.subscribe.service.APISubscribeService;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserVO;

@RequestMapping("/api/subscribe")
@RestController
public class APISubscribeController {
	@Autowired
	private APISubscribeService service;
	
	// 구독 여부 확인
	@GetMapping
	public int checkSub(Subscribe sub) {
		return service.selectSubscribe(sub);
	}
	
	// 구독 취소
	@DeleteMapping
	public void deleteSub(Subscribe sub) {
		service.deleteSub(sub);
	}
	
	// 구독
	@PostMapping
	public void insertSub(@RequestBody Subscribe sub) {
		service.insertSub(sub);
	}
	
	// 유저가 팔로잉 하는 유저들 리스트
	@GetMapping("/{userNo}/following")
	@ResponseBody
	public List<UserVO> getFollowingList(@PathVariable int userNo, Follow fol) {
		fol.setUserNo(userNo);
		return service.getFollowingList(fol);
	}
	
	// 유저를 팔로잉 하는 유저들 리스트
	@GetMapping("/{userNo}/follower")
	@ResponseBody
	public List<UserVO> getFollowerList(@PathVariable int userNo, Follow fol) {
		fol.setUserNo(userNo);
		return service.getFollowerList(fol);
	}
	
	// 유저를 팔로잉 하는 유저들의 수
	@GetMapping("/{userNo}/follower/count")
	@ResponseBody
	public int getFollwerCount(int userNo) {
		return service.selectFollowerCount(userNo);
	}
}

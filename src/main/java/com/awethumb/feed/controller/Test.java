package com.awethumb.feed.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test {
	public static void main(String[] args) 	{
		String aa = "1:2:3:4:7:11:12";
		String bb = "1:7:11:14";
		String[] s1 = aa.split(":");
		String[] s2 = bb.split(":");
		List<Integer> user1 = new ArrayList<>();
		List<Integer> user2 = new ArrayList<>();
		
		for(String a : s1) {
			int b = Integer.parseInt(a);
			user1.add(b);
		}
		for (String c : s2) {
			int d = Integer.parseInt(c);
			user2.add(d);
		}
		int a = 1;
		for(int i = 0; i < user1.size(); i++) {
			for(int j = 0; j < user2.size(); j++) {
				if (user1.get(i) == user2.get(j)) {
					System.out.println("중복" + a + "카테고리 번호 : " +  user1.get(i));
					a++;
				}
			}
		}
		
		
		
//		String aa = "1:2:3:4:7:11:12";
//		String bb = "1:7:11:14";
//		String[] s1 = aa.split(":");
//		String[] s2 = bb.split(":");
//		List<Integer> user1 = new ArrayList<>();
//		List<Integer> user2 = new ArrayList<>();
//		
//		for(String a : s1) {
//			int b = Integer.parseInt(a);
//			user1.add(b);
//		}
//		for (String c : s2) {
//			int d = Integer.parseInt(c);
//			user2.add(d);
//		}
//		int a = 1;
//		for(int i = 0; i < user1.size(); i++) {
//			for(int j = 0; j < user2.size(); j++) {
//				if (user1.get(i) == user2.get(j)) {
//					System.out.println("중복" + a + "카테고리 번호 : " +  user1.get(i));
//					a++;
//				}
//			}
//		}
		
		
		
		
		
		
		
		
	} // main

}

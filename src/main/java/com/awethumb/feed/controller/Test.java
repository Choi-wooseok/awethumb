package com.awethumb.feed.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
	public static void main(String[] args) 	{
		List<Integer> a = new ArrayList<>();
		for (int i = 5; i < 9; i++) {
			a.add(i);
		}
		Random r = new Random();
		System.out.println("qwe : " + a.size());
		List<Integer> al = new ArrayList<>();
		for(int i = 0; i < a.size(); i++) {
			System.out.println("i : " + i);
			int b = r.nextInt(a.size());
			System.out.println("b : " + b);
			int c = a.get(b);
			System.out.println("c : " + c);
			for(int j = 0; j <= i; j++) {
				int d = a.get(j);
				System.out.println("d : " + d);
				if(d != c) {
					System.out.println("중복안됨");
					System.out.println("-----");
					al.add(c);
					break;
				}
				System.out.println("중복됨");
				System.out.println("-----");
				i--;
				break;
			}
			System.out.println("ii : " + i);
		}
		
		for(int v : al) {
			System.out.println("al : " + v);
		}
		
		
		
		
	} // main

}

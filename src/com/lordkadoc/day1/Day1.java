package com.lordkadoc.day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
	
	public static void main(String[] args) throws IOException {
		
		File f = new File("resources/input1.txt");
		List<Integer> nbs = Files.readAllLines(f.toPath()).stream().map(Integer::valueOf).collect(Collectors.toList());
		
		Integer result1 = star1(nbs);
		System.out.println("Result star1 = " + result1);
		
		Integer result2 = star2(nbs);
		System.out.println("Result star2 = " + result2);
		
	}
	
	private static int star1(List<Integer> nbs) {
		for(int i=0;i<nbs.size();i++) {
			int current = nbs.get(i);
			for(int j=0;j<nbs.size();j++) {
				if(i==j)
					continue;
				int other = nbs.get(j);
				if(current + other == 2020) {
					return current * other;
				}
			}
		}
		return 0;
	}
	
	private static int star2(List<Integer> nbs) {
		for(int i=0;i<nbs.size();i++) {
			int a = nbs.get(i);
			for(int j=0;j<nbs.size();j++) {
				if(i==j)
					continue;
				int b = nbs.get(j);
				for(int k=0;k<nbs.size();k++) {
					if(i==k || j == k)
						continue;
					int c = nbs.get(k);
					if(a + b + c == 2020) {
						return a * b * c;
					}
				}
			}
		}
		return 0;
	}

}

package com.lordkadoc.day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
	
	public static void main(String[] args) throws IOException {
		
		List<Integer> nbs = Files.readAllLines(new File("resources/input10.txt").toPath()).stream().map(Integer::valueOf).sorted().collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		
		int last = 0, step1 = 0, step3 = 1;
		
		for(int i=0;i<nbs.size();i++) {
			int current = nbs.get(i);
			if(current - last == 1)
				step1++;
			else if(current - last == 3)
				step3++;
			builder.append(current-last);
			last = current;
		}
		
		String r = builder.toString().replace("1111", " 7").replace("111", " 4").replace("11", " 2").replace("1", "").replace("3", "");
		long i = Arrays.asList(r.strip().split(" ")).stream().mapToLong(s -> Long.parseLong(s.strip())).reduce(1, (a, b) -> a * b);
		System.out.println("Result for star 19 is " + (step1 * step3));
		System.out.println("Result for star 20 is " + i);
	}

}
package com.lordkadoc.day3;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class Day3 {
	
	public static void main(String[] args) throws Exception {
		List<String> map = Files.readAllLines(new File("resources/input3.txt").toPath());
		System.out.println("Result for star 5 is " + count(map, 3, 1));
		System.out.println("Result for star 6 is " + count(map, 1, 1) * count(map, 3, 1) * count(map, 5, 1) * count(map, 7, 1) * count(map, 1, 2));
	}

	private static long count(List<String> map, int dx, int dy) {
		int trees = 0, x = 0, y = 0;
		while(y < (map.size() - 1)) {
			y += dy;
			String line = map.get(y);
			x = (x + dx) % line.length();
			if(line.charAt(x) == '#')
				trees++;
		}
		return trees;
	}

}
package com.lordkadoc.day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day2 {
	
	public static void main(String[] args) throws IOException {
		List<String> passwords = Files.readAllLines(new File("resources/input2.txt").toPath());
		System.out.println("Result for star 3 is " + passwords.stream().filter(Day2::isValidPassword).count());
		System.out.println("Result for star 4 is " + passwords.stream().filter(Day2::isValidWithNewPolicy).count());
	}
	
	private static boolean isValidPassword(String password) {
		String[] split = password.split(" ");
		String[] interval = split[0].split("-");
		int min = Integer.parseInt(interval[0]);
		int max = Integer.parseInt(interval[1]);
		char c = split[1].charAt(0);
		long count = split[2].chars().filter(cc -> cc == c).count();
		return count >= min && count <= max;
	}
	
	private static boolean isValidWithNewPolicy(String password) {
		String[] split = password.split(" ");
		String[] interval = split[0].split("-");
		String s = split[2];
		char a = s.charAt(Integer.parseInt(interval[0]) - 1);
		int b = s.charAt(Integer.parseInt(interval[1]) - 1);
		char c = split[1].charAt(0);
		return a == c ^ b == c;
	}
	
}
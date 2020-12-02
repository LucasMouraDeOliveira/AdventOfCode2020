package com.lordkadoc.day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day2 {
	
	public static void main(String[] args) throws IOException {
		File f = new File("resources/input2.txt");
		List<String> passwords = Files.readAllLines(f.toPath());
		printStar3Result(passwords);
		printStar4Result(passwords);
	}
	
	private static void printStar3Result(List<String> passwords) {
		long validCount = passwords.stream().filter(Day2::isValidPassword).count();
		System.out.println("Result for star 3 is " + validCount);
	}
	
	private static void printStar4Result(List<String> passwords) {
		long validCount = passwords.stream().filter(Day2::isValidWithNewPolicy).count();
		System.out.println("Result for star 4 is " + validCount);
	}

	private static boolean isValidPassword(String password) {
		String[] split = password.split(" ");
		String[] interval = split[0].split("-");
		int min = Integer.parseInt(interval[0]);
		int max = Integer.parseInt(interval[1]);
		char c = split[1].charAt(0);
		int count = count(c, split[2]);
		return count >= min && count <= max;
	}

	private static int count(char c, String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == c)
				count++;
		}
		return count;
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

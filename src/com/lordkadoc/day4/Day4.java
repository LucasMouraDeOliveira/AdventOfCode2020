package com.lordkadoc.day4;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day4 {
	
	public static void main(String[] args) throws Exception {
		String s = Files.readString(new File("resources/input4.txt").toPath());
		List<String> lines = Arrays.asList(s.split("\n\n"));
		System.out.println("Result for star 7 is " + lines.stream().filter(line -> validPassword1(line.replace("\n", " "))).count());
		System.out.println("Result for star 8 is " + lines.stream().filter(line -> validPassword2(line.replace("\n", " "))).count());
	}
	
	private static boolean validPassword1(String line) {
		return Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").stream().allMatch(line::contains);
	}

	private static boolean validPassword2(String line) {
		return between(1920, 2002, find("byr", line)) 
				&& between(2010, 2020, find("iyr", line)) && between(2020, 2030, find("eyr", line)) 
				&& isValidHgt(find("hgt", line)) && isValidHcl(find("hcl", line)) 
				&& isValidEcl(find("ecl", line)) && isValidPid(find("pid", line));
	}
	
	private static boolean isValidPid(String pid) {
		return Pattern.compile("[0-9]{9}").matcher(pid).matches();
	}
	
	private static boolean isValidEcl(String ecl) {
		return Pattern.compile("amb|blu|brn|gry|grn|hzl|oth").matcher(ecl).matches();
	}

	private static boolean isValidHcl(String hcl) {
		return Pattern.compile("#[0-9a-f]{6}").matcher(hcl).matches();
	}

	private static boolean isValidHgt(String hgt) {
		return Pattern.compile("((59|6[0-9]|7[0-6])in)|((1[5-8][0-9]|19[0-3])cm)").matcher(hgt).matches();
	}
	
	private static boolean between(int min, int max, String string) {
		try {
			Integer number = Integer.parseInt(string);
			return number >= min && number <= max;
		} catch(NumberFormatException e) {
			return false;
		}
	}

	private static String find(String s, String line) {
		int index = line.indexOf(s);
		if(index == -1)
			return "";
		String sub = line.substring(index);
		sub = sub.contains(" ") ? sub.substring(0, sub.indexOf(" ")) : sub;
		return sub.split(":")[1];
	}

}
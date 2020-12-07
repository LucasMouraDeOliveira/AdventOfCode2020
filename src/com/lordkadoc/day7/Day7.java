package com.lordkadoc.day7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {
	
	public static void main(String[] args) throws IOException {
		
		List<String> lines = Files.readAllLines(new File("resources/input7.txt").toPath());
		lines = lines.stream().map(l -> l.replace("bags", "").replace("bag", "")).collect(Collectors.toList());
		
		Map<String, Bag> bags = new HashMap<>();
		lines.stream().forEach(line -> bags.put(line.split("contain")[0].trim(), new Bag()));
		
		lines.forEach(l -> {
			
			String[] ll = l.split("contain");
			Bag containerBag = bags.get(ll[0].trim());
			String contained = ll[1].trim();
			
			for(String c : contained.split(",")) {
				c = c.replace(".", "").trim();
				if(!c.equals("no other")) {
					Integer count = Integer.valueOf(c.split(" ")[0]);
					String bagName = c.replace(count + " ", "");
					Bag containedBag = bags.get(bagName);
					if(containedBag == null) {
						containedBag = new Bag();
						bags.put(bagName, containedBag);
					}
					containerBag.add(bagName, containedBag, count);
				}
			}
		});
		
		System.out.println("Result for star 13 is " + bags.values().stream().filter(b -> b.contains("shiny gold")).count());
		System.out.println("Result for star 14 is " + bags.get("shiny gold").count());
	}

}
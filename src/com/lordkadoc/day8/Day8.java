package com.lordkadoc.day8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
	
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(new File("resources/input8.txt").toPath());
		List<Instruction> instructions = lines.stream().map(line -> {
			String[] vals = line.split(" ");
			return new Instruction(vals[0].strip(), Integer.valueOf(vals[1].strip()));
		}).collect(Collectors.toList());
		System.out.println("Result for star 15 is " + execute(instructions, true));
		List<List<Instruction>> permutations = createPermutations(instructions);
		System.out.println("Result for star 16 is " + permutations.stream().map(perm -> execute(perm, false)).filter(r -> r > 0).findAny().orElse(0));
	}
	
	private static int execute(List<Instruction> instructions, boolean sendValueOnLoop) {
		int index = 0;
		int val = 0;
		while(index < instructions.size()) {
			Instruction i = instructions.get(index);
			if(i.isVisited()) {
				instructions.stream().forEach(p -> p.setVisited(false));
				return sendValueOnLoop ? val : 0;
			}
			i.setVisited(true);
			switch(i.getType()) {
				case "jmp":
					index += i.getValue();
					break;
				case "acc":
					val += i.getValue();
				default:
					index++;
			}
		}
		return val;
	}

	private static List<List<Instruction>> createPermutations(List<Instruction> instructions) {
		List<List<Instruction>> permutations = new ArrayList<>();
		for(int i=0; i<instructions.size(); i++) {
			Instruction instruction = instructions.get(i);
			if(instruction.getType().equals("jmp")) {
				List<Instruction> permutation = new ArrayList<>();
				permutation.addAll(instructions);
				permutation.set(i, new Instruction("nop", instruction.getValue()));
				permutations.add(permutation);
			}
		}
		return permutations;
	}

}
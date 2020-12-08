package com.lordkadoc.day8;

public class Instruction {
	
	private final String type;
	
	private final int value;
	
	private boolean visited;
	
	public Instruction(String type, int value) {
		this.type = type;
		this.value = value;
		this.visited = false;
	}
	
	public String getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}

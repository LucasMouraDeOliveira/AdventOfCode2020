package com.lordkadoc.day7;

import java.util.HashMap;
import java.util.Map;

public class Bag {

	private Map<Bag, Integer> bagAmount = new HashMap<>();
	
	private Map<String, Bag> bags = new HashMap<>();
	
	public void add(String name, Bag bag) {
		this.bags.put(name, bag);
	}
	
	public void add(String name, Bag bag, int amount) {
		this.add(name, bag);
		bagAmount.put(bag, amount);
	}
	
	public boolean contains(String bagName) {
		if(this.bags.containsKey(bagName)) {
			return true;
		}
		for(Bag b : this.bags.values()) {
			if(b.contains(bagName)) {
				return true;
			}
		}
		return false;
	}
	
	public int count() {
		int c = 0;
		for(Bag b : bagAmount.keySet()) {
			int amount = bagAmount.get(b);
			c += amount;
			c += amount * b.count();
		}
		return c;
	}
	
}

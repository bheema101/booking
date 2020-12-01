package com.hotel.booking;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapTest {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Geeks", "FOR", "GEEKSQUIZ", 
                "Computer", "Science", "gfg"); 
		
		list.stream().map(str -> str.length()).forEach(System.out::println);
		
		System.out.println();
		Stream<char[]> flatMap = list.stream().flatMap(str -> Stream.of(str.toCharArray()));
		
		System.out.println(flatMap);

	}
}

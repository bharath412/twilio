package com.srishti.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
public class CheckNull {
	public String generateresID(String registered_restaurant) {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
		String datetime = ft.format(dNow);
		System.out.println(datetime);

		int lenght = registered_restaurant.length(); // Note this should be
														// function.
		String numbers = null;
		if (lenght >= 3) {
			numbers = registered_restaurant.substring(0, 3);
		} else if (lenght < 3) {
			numbers = registered_restaurant + 0;
		}
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		int rand_int1 = rand.nextInt(1000);
		// System.out.println("Random Integers: "+rand_int1);
		Random random = new Random();

		// generate a random integer from 0 to 899, then add 100
		int x = random.nextInt(900) + 100;
		String res = numbers.toUpperCase() + datetime + x;

		System.out.println(res);
		return res;
	}

	
}

/**
 * IO is an abstract parent class to all of the specific implementations of IO 
 * needed to run a Legends game. Here some generally helpful IO functions are 
 * defined for its children. IO classes are the primary way to get a user's
 * input or display important information to a user.
 */

import java.util.ArrayList;
import java.util.Scanner;

public abstract class IO {
	public static boolean continuePlaying = true;
	
	public IO() {
		
	}

	protected int safeGetInt(String prompt, Scanner scanner) {
		boolean notInt = true;
		int rhet = 0;
		
		while(notInt) {
			System.out.print(prompt);
			String input = scanner.next();
			
			try {
				rhet = Integer.parseInt(input);
				notInt = false;
			} catch (Exception e) {
				System.out.print("Invalid input, must be an integer. ");
				this.retry();
			}
		}
		
		return rhet;
	}
	
	protected void retry() {
		System.out.println("Please try again...");
	}
}

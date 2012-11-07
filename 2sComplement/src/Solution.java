import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Problem: 2's complement, from interviewStreet.
 * 
 * The solution is from the link, which reached a log(n) time complexity on this
 * one.
 */

public class Solution {
	
	/*
	 * Dynamic programming approach - for some reason is slower than the recursive approach,
	 * but should be more efficient for similar inputs again and again
     */
	static HashMap<Integer, Long> memo = new HashMap<Integer, Long>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			System.out.println(solve(a, b));
		}
		br.close();
	}

	/**
	 * http://stackoverflow.com/questions/7942732/number-of-1s-in-the-twos-
	 * complement-binary-representations-of-integers-in-a-ran
	 * 
	 * The explanation greatly explans everything.
	 * 
	 * */
	public static long solve(int a, int b) {
		if (a >= 0) {
			long ret = counter(b);
			if (a > 0)
				ret -= counter(a - 1);
			return ret;
		}
		long ret = ((long) 32 * (-(long) a)) - counter(-a - 1);
		if (b > 0)
			ret += counter(b);
		else if (b < -1) {
			b++;
			ret -= ((long) 32 * (-(long) b)) - counter(-b - 1);
		}
		return ret;
	}

	/**
	 * Calculate the number of 1s from number 0 to number num;
	 * 
	 * */
	public static long counter(int num) {
		if (num == 0)
			return 0L;
		if (num % 2 == 0){
			if (memo.containsKey(num - 1))
				return memo.get(num - 1) + ones(num);
			else {
				long a = counter(num - 1);
				memo.put(num - 1, a);
				return a + ones(num);
			}
			
		}
		if (memo.containsKey(num / 2)){
			return (((long) num + 1) / 2) + 2 * memo.get(num / 2);
		}
		else{
			long a = counter(num / 2);
			memo.put(num / 2, a);
			return (((long) num + 1) / 2) + 2 * a;
		}
	}

	/**
	 * Calculate the number of 1s in the number
	 */
	public static long ones(int a) {
		int i = 0;
		while (a != 0){
			a = a & (a - 1);
			i++;
		}
		return i;
	}
}
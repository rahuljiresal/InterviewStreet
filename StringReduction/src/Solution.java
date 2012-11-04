import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
String Reduction (25 Points)

Given a string consisting of a,b and c's, we can perform the following operation: Take any two adjacent distinct characters and replace it with the third character. For example, if 'a' and 'c' are adjacent, they can replaced with 'b'. What is the smallest string which can result by applying this operation repeatedly?

Input:
The first line contains the number of test cases T. T test cases follow. Each case contains the string you start with.

Output:
Output T lines, one for each test case containing the smallest length of the resultant string after applying the operations optimally.

Constraints:
1 <= T <= 100
The string will have at most 100 characters.

Sample Input:
3
cab
bcab
ccccc

Sample Output:
2
1
5

Explanation:
For the first case, you can either get cab -> cc or cab -> bb, resulting in a string of length 2.
For the second case, one optimal solution is: bcab -> aab -> ac -> b. No more operations can be applied and the resultant string has length 1.
For the third case, no operations can be performed and so the answer is 5.

*/


public class Solution {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int N;

		line = br.readLine();
		N = Integer.parseInt(line);

		for (int i = 0; i < N; i++){
			String str = br.readLine();
			int reduced = reduce(str);
			System.out.println(reduced);
		}
	}

	private static int reduce(String str) {

		int acount = 0, bcount = 0, ccount = 0, result = 0;
		for (int i = 0; i < str.length(); i++){
			if (str.charAt(i) == 'a')
				acount++;
			else if (str.charAt(i) == 'b')
				bcount++;
			else ccount++;
		}
		
		if((acount == 0 && bcount == 0 && ccount == 0) ||
				(acount == 0 && bcount == 0 && ccount != 0) ||
				(acount == 0 && bcount != 0 && ccount == 0) ||
				(acount != 0 && bcount == 0 && ccount == 0))
		{
			result = acount+bcount+ccount;
		}
		else if(acount != 0 && bcount != 0 && ccount != 0)
		{
			if((acount%2 == 0 && bcount%2 == 0 && ccount%2 == 0) ||
					(acount%2 == 1 && bcount%2 == 1 && ccount%2 == 1))
				result = 2;
			else
				result = 1;
		}
		else if((acount == 0 && bcount != 0 && ccount != 0) || 
				(acount != 0 && bcount == 0 && ccount != 0) || 
				(acount != 0 && bcount != 0 && ccount == 0))
		{
			if(acount%2 == 0 && bcount%2 == 0 && ccount%2 == 0)
				result = 2;
			else
				result = 1;
		}
		return result;
	}

}

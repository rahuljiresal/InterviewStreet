import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

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

public class SolutionDynamicTLE {

	
	static HashMap<String, ArrayList<String>> memo = new HashMap<String, ArrayList<String>>();
	static StringLengthComparator comp = new StringLengthComparator();
	
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
		
		init();
		
		for (int i = 0; i < N; i++){
			String str = br.readLine();
			ArrayList<String> reduced = reduce(str);
			System.out.println(reduced.get(0).length());
		}
	}

	private static void init() {
		
		ArrayList<String> value = new ArrayList<String>();
		value.add("c");
		memo.put("ab", value);
		memo.put("ba", value);
		
		value = new ArrayList<String>();
		value.add("b");
		memo.put("ac", value);
		memo.put("ca", value);
		
		value = new ArrayList<String>();
		value.add("a");
		memo.put("bc", value);
		memo.put("cb", value);
		
		value = new ArrayList<String>();
		value.add("aa");
		memo.put("aa", value);

		value = new ArrayList<String>();
		value.add("bb");
		memo.put("bb", value);

		value = new ArrayList<String>();
		value.add("cc");
		memo.put("cc", value);

	}

	private static ArrayList<String> reduce(String str) {
		
		Set<String> keys = memo.keySet();
		
		if (keys.contains(str)){
			return memo.get(str);
		}
		
		else if (notReducible(str)){
			ArrayList<String> reducedStrings = new ArrayList<String>();
			reducedStrings.add(str);
			memo.put(str, reducedStrings);
			return reducedStrings;
		}
		
		else {
			ArrayList<String> reducedStrings = new ArrayList<String>();
			
			for (int i = 1; i < str.length() - 1; i++){
				ArrayList<String> listOne = reduce(str.substring(0, i));
				ArrayList<String> listTwo = reduce(str.substring(i));
				
				for (String s1 : listOne){
					for (String s2 : listTwo){
						if (str.equals(s1 + s2))
							reducedStrings.add(s1 + s2);
						else reducedStrings.addAll(reduce(s1 + s2));
						
					}
				}
			}
			Collections.sort(reducedStrings, comp);
			int len = reducedStrings.get(0).length();
			for (int i = 0; i < reducedStrings.size(); ){
				if (reducedStrings.get(i).length() > len)
					reducedStrings.remove(i);
				else i++;
			}
			memo.put(str, reducedStrings);
			return reducedStrings;
		}
	}

	private static boolean notReducible(String str) {
		
		int len = str.length();
		return str.matches("a{" + len + "}|b{" + len + "}|c{" + len + "}");
		
	}
	
	static class StringLengthComparator implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			return o1.length() - o2.length();
		}
		
	}

}

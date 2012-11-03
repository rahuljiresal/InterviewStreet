import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
String Similarity (25 Points)

For two strings A and B, we define the similarity of the strings to be the length of the longest prefix common to both strings. For example, the similarity of strings "abc" and "abd" is 2, while the similarity of strings "aaa" and "aaab" is 3.

Calculate the sum of similarities of a string S with each of it's suffixes.

Input:
The first line contains the number of test cases T. Each of the next T lines contains a string each.

Output:
Output T lines containing the answer for the corresponding test case.

Constraints:
1 <= T <= 10
The length of each string is at most 100000 and contains only lower case characters.

Sample Input:
2
ababaa
aa

Sample Output:
11
3

Explanation:
For the first case, the suffixes of the string are "ababaa", "babaa", "abaa", "baa", "aa" and "a". The similarities of each of these strings with the string "ababaa" are 6,0,3,0,1,1 respectively. Thus the answer is 6 + 0 + 3 + 0 + 1 + 1 = 11.

For the second case, the answer is 2 + 1 = 3.

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
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			int sum = 0;
			char charArray[] = s.toCharArray();
			for (int j = 0; j < charArray.length; j++){
				sum += calculateSimilarity(charArray, j);
			}
			System.out.println(sum);
		}
		
		
	}

	private static int calculateSimilarity(char[] charArray, int offset) {
		
		int i;
		for (i = 0; i < charArray.length - offset; i++){
			 if (charArray[i] != charArray[i + offset])
				 break;
		}
		return i;
	}

	

}

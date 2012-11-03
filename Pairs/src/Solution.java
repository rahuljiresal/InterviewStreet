import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;



/**
Given N numbers , [N<=10^5] we need to count the total pairs of numbers that have a difference of K. [K>0 and K<1e9]

Input Format:
1st line contains N & K (integers).
2nd line contains N numbers of the set. All the N numbers are assured to be distinct.
Output Format:
One integer saying the no of pairs of numbers that have a diff K.

Sample Input #00:
5 2
1 5 3 4 2

Sample Output #00:
3

 
Sample Input #01:
10 1
363374326 364147530 61825163 1073065718 1281246024 1399469912 428047635 491595254 879792181 1069262793 
 
Sample Output #01:
0
 
Note: Java/C# code should be in a class named "Solution"
Read input from STDIN and write output to STDOUT. 
*/ 

public class Solution {

	static TreeSet<Long> numbers = new TreeSet<Long>();

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		int count = 0;
		
		line = br.readLine();
		String[] firstLine = line.split(" ");
		//Long num = Long.parseLong(firstLine[0]);
		
		Long k = Long.parseLong(firstLine[1]);
		
		line = br.readLine();
		String[] secondLine = line.split(" ");
		
		for (String s : secondLine){
			numbers.add(Long.parseLong(s));
		}
		
		while (!numbers.isEmpty()){
			Long num = numbers.pollFirst();
			if (numbers.contains(num + k)) {
				count++;
			}
			numbers.remove(num);
		}
		System.out.println(count);
	}

}

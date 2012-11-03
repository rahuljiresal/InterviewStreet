import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**

Save Humanity(30points)

Oh!! Mankind is in trouble again.This time its a deadly disease spreading at a rate never seen before. Efficient detectors for the virus responsible is the need of the hour. You are the lead at Central Hospital and need to find a fast and reliable way to detect the 'foot-prints' of the virus DNA in that of patient.

The DNA of the patient as well as of the virus consists of lower case letters. Since the data collected is raw there may be some errors. You will need to find all substrings in the patient DNA that exactly matches the virus DNA with the exception of at most one mismatch.

For example tolerating at most one mismatch, "aa" and "aa" are matching, "ab" and "aa" are matching, while "ab" and "ba" are not.

Input:
The first line contains the number of test cases T. T cases follow. Each case contains two lines containing strings P(Patient DNA) and V(Virus DNA) . Each case is followed by a blank line.

Output:
Output T lines, one corresponding to each case. For each case, output a space delimited list of starting indices (0 indexed) of substrings of P which are matching with V according to the condition mentioned above . The indices has to be in increasing order.

Constraints:
1 <= T <= 10
P and V contain at most 100000 characters each.
All characters in P and V are lowercase letters.

Sample Input:
3
abbab
ba



hello

world



banana

nan


Sample Output:
1 2



0 2

Explanation:
For the first case, the substrings of P starting at indices 1 and 2 are "bb" and "ba" and they are matching with the string V which is "ba".
For the second case, there are no matching substrings so the output is a blank line.

 */


/**
The solution is a modified version of Knuth-Morris-Prott algorithm
http://www.geeksforgeeks.org/archives/11902
*/

public class Solution {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int N;

		line = br.readLine();
		N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			String patient = br.readLine();
			String virus = br.readLine();
			br.readLine();

			char patientCharArray[] = patient.toCharArray();
			char virusCharArray[] = virus.toCharArray();

			if (patientCharArray.length < virusCharArray.length){
				System.out.println();
				continue;
			}

			int[] lps = computeLPSArray(virusCharArray);
			int[] lpsWithError = computeLPSArrayWithError(virusCharArray);

			System.out.println();
		}
	}

	public static int[] computeLPSArrayWithError(char pat[]){

		int len = 0;
		int M = pat.length;
		int i;
		int[] lps = new int[M];

		int errorcount = 0;

		lps[0] = 0;
		i = 1;

		while (i < M){
			if (pat[len] == pat[i]){
				len++;
				lps[i] = len;
				i++;
			}
			else {
				errorcount++;
				if (errorcount < 2){
					len++;
					i++;
					lps[i] = len;
				}
				else {
					if (len != 0){
						len = lps[len-1];
					}
					else{
						lps[i] = 0;
						i++;
					}
					errorcount = 0;
				}
			}
		}

		return lps;
	}


	public static int[] computeLPSArray(char pat[]) {
		int len = 0;  // lenght of the previous longest prefix suffix
		int i;
		int M = pat.length;
		int[] lps = new int[M] ;

		lps[0] = 0; // lps[0] is always 0
		i = 1;

		// the loop calculates lps[i] for i = 1 to M-1
		while(i < M)
		{
			if(pat[i] == pat[len])
			{
				len++;
				lps[i] = len;
				i++;
			}
			else // (pat[i] != pat[len])
			{
				if( len != 0 )
				{
					// This is tricky. Consider the example AAACAAAA and i = 7.
					len = lps[len-1];

					// Also, note that we do not increment i here
				}
				else // if (len == 0)
				{
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

}

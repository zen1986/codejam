import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ReverseWord {
	
	private static void print(String sth) {
		System.out.println(sth);
	}
	
	private static String reverse(String s) {
		String ret = "";
		StringTokenizer st = new StringTokenizer(s);
		while (st.hasMoreTokens()) {
			String nextToken = st.nextToken();
			ret  = nextToken +" "+ret ;
		}
		return ret;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		int numOfLines = Integer.parseInt(in.readLine());
		
		for (int i=0;i<numOfLines;i++) {
			print("Case #"+(1+i)+": "+reverse(in.readLine()));
		}
		
	}
}

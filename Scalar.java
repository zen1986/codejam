import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Scalar {
	private static void print(String sth) {
		System.out.println(sth);
	}
	
	private static String getScalar(int num, String v1, String v2) {
		StringTokenizer st = new StringTokenizer(v1);
		ArrayList<Long> l1 = new ArrayList<Long>(num);
		
		while (st.hasMoreTokens()) {
			String nextToken = st.nextToken();
			l1.add(Long.parseLong(nextToken));
		}
		
		st = new StringTokenizer(v2);
		ArrayList<Long> l2 = new ArrayList<Long>(num);
		
		while (st.hasMoreTokens()) {
			String nextToken = st.nextToken();
			l2.add(Long.parseLong(nextToken));
		}

		Collections.sort(l1);
		Collections.sort(l2);
		
		long scalar = 0;
		for (int i=0;i<num;i++) {
			scalar += l1.get(i) * l2.get(num-i-1);
		}
		
		return scalar+"";
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		int num = Integer.parseInt(in.readLine());
		
		for (int i=0;i<num;i++) {
			int len = Integer.parseInt(in.readLine());
			String v1 = in.readLine();
			String v2 = in.readLine();
				
			print("Case #"+(1+i)+": "+getScalar(len, v1, v2));
		}
		
	}
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Credit {
	private static void print(String sth) {
		System.out.println(sth);
	}
	
	private static String getCredits(int c, int num, String items) {
		StringTokenizer st = new StringTokenizer(items);
		ArrayList<Integer> ll = new ArrayList<Integer>(num);
		
		while (st.hasMoreTokens()) {
			String nextToken = st.nextToken();
			ll.add(Integer.parseInt(nextToken));
		}
		
		
		for (int i=0;i<num;i++) {
			int left = c - ll.get(i);
			for (int j=i+1;j<num;j++) {
				if (left == ll.get(j)) return (1+i)+" "+(1+j);
			}
		}
		
		return "";
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		int num = Integer.parseInt(in.readLine());
		
		for (int i=0;i<num;i++) {
			int credit = Integer.parseInt(in.readLine());
			int itemNum = Integer.parseInt(in.readLine());
			String items = in.readLine();
			
			print("Case #"+(1+i)+": "+getCredits(credit, itemNum, items));
		}
		
	}
}

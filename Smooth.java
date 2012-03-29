import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Action {
	int type; // delete 1; insert 2; change 3; root 0
	int cost;
	Action children[];
	
	Action(int _type, int _cost) {
		type = _type;
		cost = _cost;
		children = new Action[3];
	}
	
	int getType() {return type;}
	int getCost() {return cost;}
	
	void setChildren (Action a, int idx) {
		children[idx-1] = a;
	}
	
	Action [] getChildren() {
		return children;
	}
}

public class Smooth {

	private static final int DELETE = 1;
	private static final int INSERT = 2;
	private static final int CHANGE = 3;


	private static void print(String sth) {
		System.out.println(sth);
	}
	
	private static int getSmooth(LinkedList<Integer> arr, int D, int I, int M) {
		int ptr = 0;
		int cost = 0;
		Action costTree = new Action(0, 0);
		
		while (ptr!=arr.size() - 1) {
			int A = arr.get(ptr);
			int B = arr.get(ptr+1);
			
			
			
			
			ptr++;
		}
		
		return cost;
	}
	
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		int num = Integer.parseInt(in.readLine());
		
		for (int i=0;i<num;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int D = Integer.parseInt(st.nextToken());
			int I = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> arr = new LinkedList<Integer>();
			st = new StringTokenizer(in.readLine());
			for (int j=0;j<N;j++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			print("Case #"+(1+i)+": "+getSmooth(arr, D, I, M));	
		}
		
	}
}

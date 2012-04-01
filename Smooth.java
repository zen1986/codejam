import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Smooth {

    private static int DELETE = 0;
    private static int CHANGE = 1;
    private static int INSERT = 2;

	private static void print(String sth) {
		System.out.println(sth);
	}

    private static int [] getBestMethod(LinkedList<Integer> arr, int D, int I, int M, int current) {
        int first = arr.get(current);
        int second = arr.get(current+1);

        // choose whether use insert, delete or change

        // consider delete
        LinkedList<Integer> deleteArr = new LinkedList<Integer>(arr);
        deleteArr.remove(current+1);
        int deleteCost = D + getSmooth(deleteArr, D, I, M, current);

        // consider change
        LinkedList<Integer> changeArr = new LinkedList<Integer>(arr);
        int toChange = first > second? first - M : first + M;
        changeArr.set(current+1, toChange);
        int changeCost = Math.abs(second - toChange) + getSmooth(changeArr, D, I, M, current+1);

        int insertCost = 10000000;
        // consider insert
        if (M != 0) {
            LinkedList<Integer> insertArr = new LinkedList<Integer>(arr);
            insertArr.add(current+1, toChange); 
            insertCost = I + getSmooth(insertArr, D, I, M, current+1);
        }

        if (M != 0) {
            if (deleteCost>changeCost) {
                if (changeCost>insertCost) {
                    // choose insert
                	print("insert");
                    arr.add(current+1, toChange);
                    return new int[]{INSERT, insertCost};
                } else {
                    // choose change
                	print("change"); 
                    arr.set(current+1, toChange);
                    return new int[]{CHANGE, changeCost};
                }
            } else {
                if (deleteCost>insertCost) {
                    // choose insert
                	print("insert");
                    arr.add(current+1, toChange);
                    return new int[]{INSERT, insertCost};
                } else {
                    // choose delete 
                    arr.remove(current+1);
                	print("delete");
                    return new int[]{DELETE, deleteCost};
                }
            }
        } else {
            if (deleteCost>changeCost)  {
                // choose change 
            	print("change");
                arr.set(current+1, toChange);
                return new int[]{CHANGE, changeCost};
            } else {
                // choose delete
            	print("delete"); 
                arr.remove(current+1);
                return new int[]{DELETE, deleteCost};
            }
        }

    }
	
	private static int getSmooth(LinkedList<Integer> arr, int D, int I, int M, int current) {
		print("current: "+current+" arrSize: "+arr.size());
		if (current+1 >= arr.size()) {
            // everything is smooth
			return 0;
		} else {
			int first = arr.get(current);
			int second = arr.get(current+1);
			
            // if they are smooth, then pass to next
			if (Math.abs(first-second)<=M) 
                return getSmooth(arr, D, I, M, current+1);
			
            int bestRet[] = getBestMethod(arr, D, I, M, current);

            if (bestRet[0] == DELETE) return bestRet[1] + getSmooth(arr, D, I, M, current);
            else return bestRet[1] + getSmooth(arr, D, I, M, current+1);
        }
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
			
			print("Case #"+(1+i)+": "+getSmooth(arr, D, I, M, 0));	
		}
		
	}
}

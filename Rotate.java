import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Rotate {
	private static void print(String sth) {
		System.out.println(sth);
	}
	
	private static String getRotate(char [][] map, int k) {	
		
		map = rotate(map);
		
		map = moveDown(map);

		int res = check(map, k);
		
		if (res==1) return "Blue";
		if (res==2) return "Red";
		if (res==3) return "Both";
		
		return "Neither";
	}
	
	private static char [][] rotate(char [][] map) {
		int dim = map.length;
		char rotatedMap[][] = new char[dim][dim];
		for (int r=0;r<dim;r++) {
			for (int c=0;c<dim;c++) {
				rotatedMap[c][dim-r-1] = map[r][c];
			}
		}
		return rotatedMap;
	}
	
	private static int getLowestEmtpyRow(char [][] map, int col, int row) {
		
		int lowestEmptyRow = row;
		for (int i=row+1;i<map.length;i++) {
			if (map[i][col]=='.') lowestEmptyRow++;
			else break;
		}
		
		return lowestEmptyRow;
	}
	
	private static char [][]  moveDown(char [][] map) {
		int dim = map.length;
		
		// start from 2nd last row
		for (int r=dim-2;r>-1;r--) {
			for (int c=0;c<dim;c++) {
				int lowestEmptyRow = getLowestEmtpyRow(map, c, r);
				if (lowestEmptyRow == r) continue;
				map[lowestEmptyRow][c] = map[r][c];
				map[r][c] = '.';
			}
		}
		return map;
	}
	
	private static boolean hasWin(char [][] map, int r ,int c, int k) {
		//print(k+"");
		char me = map[r][c];
		int dim = map.length;
		int counter = 0;
		// check up
		for (int i=r;i>-1;i--) {
			if (i>=dim || map[i][c] != me) break;
			counter ++;
			if (counter==k) {
				//print(me+" up");
				return true;
			}
		}
		
		// check left
		counter = 0;
		for (int i=c;i>-1;i--) {
			if (i<0 || map[r][i]!=me) break;
			counter ++;
			if (counter==k) {
				//print(me+" left");
				return true;
			}
		}
		
		// check diagonal left
		counter =0;
		for (int i=0;i<k;i++) {
			if (r-i<0 || c-i<0 || map[r-i][c-i]!=me) break;
			counter ++;
			if (counter==k) {
				//print(me+" diagonal left");
				return true;
			}
		}
		
		// check diagonal right
		counter = 0;
		for (int i=0;i<k;i++) {
			if (r-i<0 || c+i>=dim || map[r-i][c+i] != me) break;
			counter ++;
			if (counter ==k) {
				//print(me+" diagonal right");
				return true;
			}
		}
		
		return false;
	}
	
	private static int check(char [][] map, int k) {
		int dim = map.length;
		boolean blueWin = false;
		boolean redWin = false;
		
		for (int r=0;r<dim;r++) {
			for (int c=0;c<dim;c++) {
				if (map[r][c]=='.') continue;
				if (hasWin(map, r, c, k)) {
					if (map[r][c] == 'R') redWin  =true;
					if (map[r][c] == 'B') blueWin =true;
				}
			}
		}
		
		if (blueWin&&redWin) return 3;
		if (blueWin) return 1;
		if (redWin) return 2;
		else return 0;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		int num = Integer.parseInt(in.readLine());
		
		for (int i=0;i<num;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int dim = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			char map[][] = new char[dim][dim];
			
			for (int r=0;r<dim;r++) {
				String line = in.readLine();
				for (int c=0;c<dim;c++) {
					map[r][c] = line.charAt(c);
				}
			}
			
			print("Case #"+(1+i)+": "+getRotate(map, k));
			
		}
		
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Room implements Comparable<Room> {
		int start;
		int end;
		
		Room(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(Room r) {
			return this.start == r.start ? (Integer.compare(this.end, r.end)*-1) : (Integer.compare(this.start, r.start)*-1);
		}
		
		public String toString() {
			return start+" "+end;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		Room[] roomList = new Room[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			roomList[i] = new Room(start, end);
		}
		
		Arrays.sort(roomList);
		
		int cur = Integer.MAX_VALUE;
		int cnt = 0;
		for(int i=0;i<N;i++) {
//			System.out.println(roomList[i]);
			if(cur>=roomList[i].end) {
				cnt++;
				cur = roomList[i].start;
			}
		}
		
		System.out.println(cnt);
		
	}
}
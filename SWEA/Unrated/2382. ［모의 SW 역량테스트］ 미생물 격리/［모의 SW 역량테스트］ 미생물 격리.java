import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, time, K;
	
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int d;
		int num;
		
		Node(int r,int c, int d, int num) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + ", num=" + num + "]";
		}

		@Override
		public int compareTo(Node o) {
			return o.num - this.num;
		}
		
		public boolean move() {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			//가장자리
			if(nr==0 || nr==N-1 || nc == 0 || nc == N-1) {
				this.r = nr;
				this.c = nc;
				
				this.num = this.num / 2;
				d = 3-d;
			}
			else {
				this.r = nr;
				this.c = nc;
			}
			
			if(this.num==0) return false;
			
			return true;
			
		}
		
		void merge(Node node) {
			this.num += node.num;
		}
		
	}
	//상 좌 우 하
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	
	static List<Node> list;
	
	static Node[][] graph;
	
	static void move() {
		graph = new Node[N][N];
		//우선 정렬
		Collections.sort(list);
//		for(int i=0; i<list.size();i++) {
//			System.out.println(list.get(i).toString());
//		}
		
		//계속 빠지는 것 방지
		int size = list.size();
//		System.out.println(size+" size");
		
		for(int i=0; i<size;i++) {
			Node cur = list.remove(0);
			
			//이동에 성공했으면
			if(cur.move()) {
				int r = cur.r;
				int c = cur.c;
				
				if(graph[r][c]!=null) {
					graph[r][c].merge(cur);
				}
				
				else {
					graph[r][c] = cur;
					list.add(graph[r][c]);
				}
			}
		}
		
	}
	
	static int result;
	
	static void sum() {
		for(int r=0; r<N;r++) {
			for(int c=0; c<N;c++) {
				if(graph[r][c]==null) continue;
				result += graph[r][c].num;

			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			

			result = 0;
			list = new ArrayList<>();
			
			
			for(int k=0; k<K;k++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				if(d==1) d=0;
				else if(d==2) d=3;
				else if(d==3) d=1;
				else if(d==4) d=2;
				
				Node node = new Node(r, c, d, num);
				list.add(node);
			}
			
			for(int k=0; k<time;k++) {
				move();
			}
			
			sum();
			
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		
	}
}
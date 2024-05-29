import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n;

	static boolean[][] visited = new boolean[2001][2001];

	static class Node {
		int current;
		int copy;

		Node(int current, int copy) {
			this.current = current;
			this.copy = copy;
		}
	}


	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(1, 0));
		visited[1][0] = true;

		int time = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();

			while(--size>=0) {
				Node node = queue.poll();
				int current = node.current;
				int copy = node.copy;

				if(current==n) {
					System.out.println(time);
					return;
				}
				//클립 보드 복사
				if(!visited[current][current]) {
					queue.offer(new Node(current, current));
					visited[current][current] = true;
				}
				//붙여넣기
				if(copy!=0 && (current+copy<=2000) && !visited[current+copy][copy]) {
					queue.offer(new Node(current+copy, copy));
					visited[current+copy][copy] = true;
				}
				//이모티콘 삭제
				if(current!=0 && !visited[current-1][copy]) {
					queue.offer(new Node(current-1, copy));
					visited[current-1][copy] = true;
				}

			}

			time++;
		}


	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();


		BFS();

	}
}
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//11544KB, 76ms
public class Main {

	public static void main(String[] args) throws IOException {
		Queue<Integer> queue = new ArrayDeque<>();
		
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		for(int i=1;i<=num;i++) {
			queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			if(queue.size()==1) {
				System.out.println(queue.peek());
				break;
			}
			queue.poll();
			queue.add(queue.poll());
		}
	}

}
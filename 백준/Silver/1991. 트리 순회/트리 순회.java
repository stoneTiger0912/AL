import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 입력 알파벳의 크기만큼의 배열을 만듦
 * 자식이 2*n 2*n+1인것을 활
 */
public class Main {
	
	static int N;
	
	static class Node {
		char cur;
		Node left;
		Node right;
		
		Node() {}
		
		Node(char cur) {
			this.cur = cur;
		}
		
		public void update(Node left, Node right) {
			this.left = left;
			this.right = right;
		}
		
		public void preOrder() {
			System.out.print(this.cur);
			if(left !=null) {
				left.preOrder();
			}
			if(right != null) {
				right.preOrder();
			}
		}
		
		public void inOrder() {
			if(left !=null) {
				left.inOrder();
			}
			System.out.print(this.cur);
			if(right != null) {
				right.inOrder();
			}
		}
		
		public void postOrder() {
			if(left !=null) {
				left.postOrder();
			}
			if(right != null) {
				right.postOrder();
			}
			System.out.print(this.cur);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		//이미 누군가의 자식 노드인지 체크 
		boolean[] isPlaced = new boolean[N];
		//트리 부분 
		Node[] nodeList = new Node[N];
		for(int i=0; i<N;i++) {
			nodeList[i] = new Node((char)('A'+i));
		}
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			char cur = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			Node curNode = nodeList[cur-'A'];
			Node leftNode = null;
			Node rightNode = null;
			if(left!='.') {
				leftNode = nodeList[left-'A'];
			}
			
			if(right!='.') {
				rightNode = nodeList[right-'A'];
			}

			curNode.update(leftNode, rightNode);
			
		}
		
		//전위
		nodeList[0].preOrder();
		System.out.println();
		
		//중위
		nodeList[0].inOrder();
		System.out.println();
		
		//후위 
		nodeList[0].postOrder();
		System.out.println();
		
	}
}
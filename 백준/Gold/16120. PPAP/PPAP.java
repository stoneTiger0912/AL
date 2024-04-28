import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<input.length();i++) {
			int text = input.charAt(i);

			if(text=='A') {
				stack.push(text);
			}
			//'P' 일 경우
			else {
				int size = stack.size();
				//앞에 'A'인 경우
				if(size>=3 && stack.peek()=='A' ) {
					if(stack.get(size-2) == 'P' && stack.get(size-3)=='P') {
						for(int j=0; j<2;j++) {
							stack.pop();
						}
					}
				}
				else {
					stack.push(text);
				}
			}
		}
		
		//stack.forEach(System.out::println);
		
		if(stack.size()==1 && stack.peek()=='P') {
			System.out.println("PPAP");
		}
		else {
			System.out.println("NP");
		}
	}
}
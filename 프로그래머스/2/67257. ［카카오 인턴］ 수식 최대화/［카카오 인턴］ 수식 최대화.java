/*
일단 연산자의 개수 파악
순열로 순서 정하기
후 계산
*/
import java.util.*;
import java.io.*;
class Solution {
    
    private List<String> inputList = new ArrayList<>();
    
    //+는 1 -는 2 *는 3으로 취급
    private static final int PLUS = 1, MINUS = 2, MULTIPLE = 3;
    
    //경우의 수
    private int[][] permList = {
        {1, 2, 3},
        {1, 3, 2},
        {2, 1, 3},
        {2, 3, 1},
        {3, 1, 2},
        {3, 2, 1}
    };
    
    private static Long res = 0L;
    
    public void calculate(int idx) {
        
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> tmpStack = new ArrayDeque<>();
        
        for(int i=inputList.size()-1; i>=0; i--) {
            tmpStack.push(inputList.get(i));
        }
        
        for(int i=0; i<3;i++) {
            while(!tmpStack.isEmpty()) {
                String tmp = tmpStack.pop();
                
                switch(tmp) {
                    case "-":
                        if(permList[idx][i]==MINUS) {
                            String first = stack.pop();
                            String second = tmpStack.pop();
                            
                            stack.push(String.valueOf(Long.valueOf(first)-Long.valueOf(second)));
                        }
                        else {
                            stack.push(tmp);
                        }
                        break;
                    case "+":
                                                if(permList[idx][i]==PLUS) {
                            String first = stack.pop();
                            String second = tmpStack.pop();
                            
                            stack.push(String.valueOf(Long.valueOf(first)+Long.valueOf(second)));
                        }
                        else {
                            stack.push(tmp);
                        }
                        break;
                    case "*":
                                                if(permList[idx][i]==MULTIPLE) {
                            String first = stack.pop();
                            String second = tmpStack.pop();
                            
                            stack.push(String.valueOf(Long.valueOf(first)*Long.valueOf(second)));
                        }
                        else {
                            stack.push(tmp);
                        }
                        break;
                    default:
                        stack.push(tmp);
                        break;
                }
                
            }
            while(!stack.isEmpty()) {
                tmpStack.push(stack.pop());
            }
        }
        
    
        
        Long tmp = Long.parseLong(tmpStack.pop()); 
        
        res = Math.max(res, Math.abs(tmp));
        
        
    }
    
    
    public long solution(String expression) {
        
        
        long answer = 0;
        
        long tmp = 0;
        //따옴표 없애기 위해 1부터 길이 -1 까지
        for(int i=0; i<expression.length();i++) {
            if(expression.charAt(i) >= '0' && expression.charAt(i) <='9') {
                tmp = tmp * 10 + Long.parseLong(String.valueOf(expression.charAt(i)));
            }
            else {
                inputList.add(String.valueOf(tmp));
                tmp = 0;
                inputList.add(String.valueOf(expression.charAt(i)));
            }
        }
        inputList.add(String.valueOf(tmp));
        
        // for(String s: inputList) {
        //     System.out.println(s);
        // }
        
        for(int i=0; i<permList.length;i++) {
            calculate(i);
        }
        
        answer = res;
        
        return answer;
    }
}
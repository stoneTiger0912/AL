import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] diceList = new int[6];
    
    static int[][] combList = {
//    		{
	    		{0, 1},
	    		{0, 2},
	    		{0, 3},
	    		{0, 4},
	    		{1, 2},
	    		{1, 3},
	    		{1, 5},
	    		{2, 4},
	    		{2, 5},
	    		{3, 4},
	    		{3, 5},
	    		{4, 5}
//    		},
    		
    };

    
    static void comb(int cnt, int idx, int num, int[] list) {
        
        if(cnt==num) {
            //0-5
        	//3-2
        	//4-1의 조합은 안나온다
        	check(list);
            return;
        }
        
        for(int i=idx; i<6;i++) {
            list[cnt] = i;
            comb(cnt+1, i+1, num, list);
        }
    }
    
    static int smallThree = 150;
    
    static void check(int[] list) {
    	int one = list[0];
    	int two = list[1];
    	int three = list[2];
    	List<Integer> findList = new ArrayList<>();
    	findList.add(one);
    	findList.add(two);
    	findList.add(three);
    	
    	if(findList.contains(0)&&findList.contains(5)) return;
    	if(findList.contains(2)&&findList.contains(3)) return;
    	if(findList.contains(4)&&findList.contains(1)) return;
    	
    	smallThree = Math.min(smallThree, diceList[one]+diceList[two]+diceList[three]);
    	
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i=0; i<6;i++) {
            diceList[i] = sc.nextInt();
        }
        
        //1일때 
        if(N==1) {
        	Arrays.sort(diceList);
            System.out.println(Arrays.stream(diceList).sum()-diceList[5]);
        }
        //2이상일때
        else {

          
            long three = 4;
            
            
            long one = (long)Math.pow((N-2), 2)*5+(N-2)*4; 
            
            
//            total -= (long)Math.pow((N-2), 2);
            long two = 8*(N-2)+4;
//            System.out.println(one+" "+two+" "+three);
            long result = 0;
            
            int twoSmall = 101;
            for(int i=0; i<combList.length;i++) {
            	int a = combList[i][0];
            	int b = combList[i][1];
            	twoSmall = Math.min(diceList[a]+diceList[b], twoSmall);
            }
            result += twoSmall*two;
            
            comb(0, 0, 3, new int[3]);
            result += smallThree*three;
            //1개일때 최솟값이므로 최소 수를 더함
            Arrays.sort(diceList);
            result += diceList[0]*one;
            System.out.println(result);
        }
    }
}
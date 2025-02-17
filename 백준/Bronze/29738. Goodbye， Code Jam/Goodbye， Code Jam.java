import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int n=Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++){
            int score=Integer.parseInt(br.readLine());
            if(score<=25){
                bw.write("Case #"+i+": World Finals\n");
            }
            else if(score<=1000){
                bw.write("Case #"+i+": Round 3\n");
            }
            else if(score<=4500){
                bw.write("Case #"+i+": Round 2\n");
            }
            else{
                bw.write("Case #"+i+": Round 1\n");
            }
        }
        bw.flush();
    }
}
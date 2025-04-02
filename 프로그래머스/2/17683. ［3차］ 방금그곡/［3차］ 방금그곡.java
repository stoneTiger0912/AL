import java.util.*;
import java.io.*;

class Solution {
    
    Map<Character, String> map = new HashMap<>(); 
    
    private void init() {
        map.put('C', "A");
        map.put('c', "B");
        map.put('D', "C");
        map.put('d', "D");
        map.put('E', "E");
        map.put('F', "F");
        map.put('f', "G");
        map.put('G', "H");
        map.put('g', "I");
        map.put('A', "J");
        map.put('a', "K");
        map.put('B', "L");
        map.put('b', "L");
    }
    
    private String change(String word) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<word.length();i++) {
            int tmp = 0;
            if(i+1<word.length()) {
                if(word.charAt(i+1)=='#') {
                    tmp = word.charAt(i) - 'A' + 'a';
                    i++;
                }
                else {
                    tmp = word.charAt(i);
                }
            }
            else {
                tmp = word.charAt(i);
            }
            char ch = (char) tmp;
            // System.out.println(ch);
            // System.out.println(map.get(ch));
            
            sb.append(map.get(ch));
        }
        
        String w = sb.toString();
        
        return w;
    }
    
    public String solution(String m, String[] musicinfos) {
        init();
        String result = "";
        int resLen = Integer.MIN_VALUE;
        
        for(int i=0; i<musicinfos.length;i++) {
            //분리
            String[] musicInfo = musicinfos[i].split(",");
            
            //반복 시간 추출
            String[] startList = musicInfo[0].split(":");
            String[] endList = musicInfo[1].split(":");
            
            int start = Integer.parseInt(startList[0]) * 60 + Integer.parseInt(startList[1]);
            int end = Integer.parseInt(endList[0]) * 60 + Integer.parseInt(endList[1]);
            
            int musicLen = end - start;
            
            int len = musicInfo[3].length();
            
            
            
            String word = change(m);
            String music = change(musicInfo[3]);
            
            // System.out.println(word+" "+music);
            
            int multi = musicLen / music.length();
            
            // System.out.println(word+" "+music);
            
            StringBuilder sb = new StringBuilder();
            
            for(int j=0; j<multi+1;j++) {
                sb.append(music);
            }
            
            // System.out.println(sb.toString().length());
            // System.out.println(musicLen);
            
            String repeatMusic = sb.toString().substring(0, musicLen);
            
            if(repeatMusic.contains(word) && (resLen < musicLen)) {
                result = musicInfo[2];
                resLen = musicLen;
            }
            
            
        }
        
        String answer = result.equals("") ? "(None)": result;
        return answer;
    }
}
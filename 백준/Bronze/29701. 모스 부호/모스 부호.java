import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException {
		
		HashMap<String, String> morseMap = new HashMap<>();
		morseMap.put(".-", "A");
		morseMap.put("-...", "B");
		morseMap.put("-.-.", "C");
		morseMap.put("-..", "D");
		morseMap.put(".", "E");
		morseMap.put("..-.", "F");
		morseMap.put("--.", "G");
		morseMap.put("....", "H");
		morseMap.put("..", "I");
	    morseMap.put(".---", "J");
	    morseMap.put("-.-", "K");
	    morseMap.put(".-..", "L");
	    morseMap.put("--", "M");
	    morseMap.put("-.", "N");
	    morseMap.put("---", "O");
	    morseMap.put(".--.", "P");
	    morseMap.put("--.-", "Q");
	    morseMap.put(".-.", "R");
	    morseMap.put("...", "S");
	    morseMap.put("-", "T");
	    morseMap.put("..-", "U");
	    morseMap.put("...-", "V");
	    morseMap.put(".--", "W");
	    morseMap.put("-..-", "X");
	    morseMap.put("-.--", "Y");
	    morseMap.put("--..", "Z");
	    morseMap.put(".----", "1");
	    morseMap.put("..---", "2");
	    morseMap.put("...--", "3");
	    morseMap.put("....-", "4");
	    morseMap.put(".....", "5");
	    morseMap.put("-....", "6");
	    morseMap.put("--...", "7");
	    morseMap.put("---..", "8");
	    morseMap.put("----.", "9");
	    morseMap.put("-----", "0");
	    morseMap.put("--..--", ",");
	    morseMap.put(".-.-.-", ".");
	    morseMap.put("..--..", "?");
	    morseMap.put("---...", ":");
	    morseMap.put("-....-", "-");
	    morseMap.put(".--.-.", "@");
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int count = Integer.parseInt(br.readLine());
	    String[] codes = br.readLine().split(" ");
	    
	    br.close();
	    
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    for (String code : codes) {
	    	bw.write(morseMap.get(code));
	    }
	    
	    bw.flush();
	    bw.close();
		
	}
}
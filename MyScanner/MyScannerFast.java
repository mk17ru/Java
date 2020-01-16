import java.io.*;
import java.util.*;
import java.lang.*;
public class MyScannerFast {
	public static final String UTF_8 = "utf8";
	static String buf;
	static Reader reader;
	static boolean isInt;
	static boolean isRead = false;
	static enum State {
		EOF, EOL, SPACE, SYMBOL;
	}
	static State state = State.SYMBOL;
	MyScannerFast(Reader s){
		reader = s;
		state = State.SYMBOL;
	}
	MyScannerFast(InputStream s){
		reader = new InputStreamReader(s);
		state = State.SYMBOL;
	}
	MyScannerFast(File u){
		try{
			reader = new InputStreamReader(new FileInputStream(u), UTF_8);
			state = State.SYMBOL;
		}	catch (FileNotFoundException e) {
			System.err.println("file not found: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
	public static void close(){
		try {
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("file not found: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
	public static boolean hasNextLine() {
		boolean f = (state != State.EOF);
		if (f) {
			state = State.SYMBOL;
		}
		return f;
	}
	public static boolean hasNextinLine() {
		return state != State.EOF && state != State.EOL;
	}
	public static String readNextLine() throws IOException{
		StringBuilder ans = new StringBuilder();
		state = State.SYMBOL;
		while(state == State.SYMBOL) {
			int h = reader.read();
			if (h != -1 && h != '\n') {
				ans.append((char)h);
			}
			else if (h == -1) {
				state = State.EOF;
			} else if (h == '\n')
			{
				state = State.EOL;
			}
		}
		return ans.toString();
	}
	public static void readNext() throws IOException{
		StringBuilder ans = new StringBuilder();
		state = State.SYMBOL;
		while(state == State.SYMBOL) {
			int h = reader.read();
			if (h != -1 && (Character.isDigit((char)h) || (char)h == '-')) {
				ans.append((char)h);
			}
			else if (h == -1) {
				state = State.EOF;
			} else if (h == '\n')
			{
				state = State.EOL;
			} else {
				state = State.SPACE;
			}
		}
		//System.out.println(ans.length());
		if (ans.length() > 0) {
			isInt = true;
			buf = ans.toString();
			isRead = true;
		} else {
			isInt = false;
			isRead = false;
		}
	}
	public static int readNextInt() throws IOException{
		if (!isRead) {
			readNext();
		}
		isRead = false;
		return Integer.parseInt(buf);
	}
	public static boolean hasNextInt() throws IOException{
		readNext();
		return isInt;
	}
	public static boolean isSymbol(char h) {
		return (Character.isAlphabetic(h) ||
				(h == '\'') ||
				(Character.DASH_PUNCTUATION == Character.getType(h)));
	}
	public static String readNextWord() throws IOException{
		StringBuilder ans = new StringBuilder();
		state = State.SYMBOL;
		while(state == State.SYMBOL) {
			int h = reader.read();
			if (h != -1 && isSymbol((char)h)) {
				ans.append((char)h);
			}
			else if (h == -1) {
				state = State.EOF;
			} else if (h == '\n')
			{
				state = State.EOL;
			} else {
				state = State.SPACE;
			}
		}
		return ans.toString();
	}
}
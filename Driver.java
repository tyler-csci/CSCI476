package TEwald;

import java.util.*;
import java.security.*;
import java.io.*;
import java.math.*;

	

public class Driver {
	public static void getInput(ArrayList<String> input, ArrayList<String> dictionary) {
		Scanner in = null;
		Scanner grammar = null;
		try {
			in = new Scanner(new File("src/TEwald/input.txt"));
			grammar = new Scanner(new File("src/TEwald/dictionary2.txt"));
		}
		catch (Exception error) {
			System.out.println("Error. File Not Found.");
			System.exit(0);
		}
		while (in.hasNext()) {
			input.add(in.next());
		}
		while (grammar.hasNext()) {
			dictionary.add(grammar.next());
		}
		in.close();
		grammar.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> dictionary = new ArrayList<String>();
		PrintWriter pwriter = new PrintWriter("src/TEwald/Output.txt", "UTF-8");
		getInput(input, dictionary);
		long begin = System.currentTimeMillis();
		long end;
		double runtime;
		for (String string : dictionary) {
			String test = getMd5(string);
			for (String in : input) {
				if (in.equals(test)) {
					end = System.currentTimeMillis();
					runtime = (double) (end - begin) / 5000;
					pwriter.print("The password for hash value " + in + " is " + string + ",\n");
					System.out.println("The password for hash value " + in + " is " + string + ",\n");
					pwriter.print("it takes the program " + runtime + " seconds to recover the password\n");
					System.out.println("it takes the program " + runtime + " seconds to recover the password\n");
				} else {
					continue;
				}
			}
		}
		pwriter.close();
		return;
	}
	
	public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hash = no.toString(16);
            
            while (hash.length() < 32) {
                hash = "0" + hash;
            } 
            return hash;
        }
        catch (NoSuchAlgorithmException error) {
            throw new RuntimeException(error);
        }
    }
}
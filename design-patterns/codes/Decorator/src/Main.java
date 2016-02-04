import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{

        String filePath = "src\\a.txt";

        BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(
                        new FileInputStream(
                                new File(filePath))));

        String line = bufferedReader.readLine();
        System.out.println(" " + line );
	}
}

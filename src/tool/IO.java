package tool;

import java.io.*;

public class IO {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static InputStream inputStream = System.in;
    public static String bufferedInput() throws Exception{
        return input.readLine().replaceAll("[\\n\\r\"]","");
    }
}

package tool;

import java.io.*;

public class IO {
    public static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static final InputStream inputStream = System.in;
    public static String bufferedInput() throws Exception{
        return input.readLine().replaceAll("[\\n\\r\"]","");
    }
}

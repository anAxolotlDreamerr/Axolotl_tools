package tool.zip;

import tool.IO;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Unzip {
    private static String path;
    private static ZipInputStream zipInput;
    private static ZipFile zipfile;

    private Unzip(){}
    public static void unzip(){
        try {
            System.out.println("Please enter Zip path:(enter \"stop\" to stop)");
            String zipFile = IO.bufferedInput();
            if (zipFile.equals("stop")||zipFile.equals("Stop")){
                path =null;
                zipFile = null;
                return;
            }
            System.out.println("Please enter path:(enter \"stop\" to stop)");
            path = IO.bufferedInput();
            if (path.equals("stop")||path.equals("Stop")){
                path =null;
                zipFile = null;
                return;
            }
            if(!path.matches("\\$")){
                path = path+"\\";
            }
            zipfile = new ZipFile(zipFile);
            zipInput = new ZipInputStream(new FileInputStream(zipFile));
            IO(zipInput.getNextEntry());
            zipInput.close();
        }catch (Exception e){
            System.out.println("Wrong!Please enter again!");
            System.err.println(e);
            path = null;
            zipfile =null;
            zipInput = null;
            unzip();
        }
        path = null;
        zipfile =null;
        zipInput = null;
    }
    private static void IO(ZipEntry zipEntry) throws Exception{
        if(zipEntry != null) {
            File file =new File(path+zipEntry.getName());
            file.getParentFile().mkdirs();
            OutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = zipfile.getInputStream(zipEntry);
            byte[] b = new byte[1024*1024*100];
            int len;
            while((len = inputStream.read(b)) !=-1){
                outputStream.write(b,0,len);
            }
            outputStream.close();
            inputStream.close();
            IO(zipInput.getNextEntry());
        }
    }

}

package tool.zip;

import tool.IO;
import tool.language.Language;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Unzip {
    private static String path;
    private static ZipInputStream zipInput;
    private static ZipFile zipfile;
    private static int zipFiles;
    private static int finishFiles;
    private Unzip(){}
    public static void unzip(){
        try {
            zipFiles = 0;
            finishFiles= 0;
            path = null;
            zipfile =null;
            zipInput = null;
            System.out.println(Language.getText("UnzipFirst"));
            String zipFile = IO.bufferedInput();
            if (zipFile.equals("stop")||zipFile.equals("Stop")){
                return;
            }
            System.out.println(Language.getText("UnzipSecond"));
            path = IO.bufferedInput();
            if (path.equals("stop")||path.equals("Stop")){
                return;
            }
            if(!path.matches("\\$")){
                path = path+"\\";
            }
            long time1 = System.currentTimeMillis();
            zipfile = new ZipFile(zipFile);
            File zip =new File(zipFile);
            zipInput = new ZipInputStream(new FileInputStream(zip));
            zipFiles = numberOfFiles(zip);
            IO(zipInput.getNextEntry());
            long fileSize = Compress.calculateTheSize(new File(path));
            long time2 = System.currentTimeMillis();
            System.out.printf(Language.getText("UnzipThird")+'\n',(float)fileSize/(1024*1024),(float)(time2-time1)/1000);
            zipInput.close();
            zipfile.close();
        }catch (Exception e){
            System.out.println(Language.getText("EnterWrong"));
            System.err.println(e);
            unzip();
        }
    }
    public static int numberOfFiles(File zip) throws Exception{
        int num=0;
        ZipEntry zipEntry =null;
        ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zip));
        while((zipEntry = zipInput.getNextEntry())!=null){
            num++;
        }
        return num;
    }
    private static void IO(ZipEntry zipEntry) throws Exception{
        if(zipEntry != null) {
            File file =new File(path+zipEntry.getName());
            file.getParentFile().mkdirs();
            OutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = zipfile.getInputStream(zipEntry);
            byte[] b = new byte[64 * 1024];
            int len;
            while((len = inputStream.read(b)) !=-1){
                outputStream.write(b,0,len);
            }
            outputStream.close();
            inputStream.close();
            finishFiles ++;
            if(zipFiles != 0){
                System.out.printf(Language.getText("UnzipFourth")+'\n',zipEntry.getName(),(float)file.length()/(1024),(float)100*finishFiles/zipFiles);
            } else {
                System.out.printf(Language.getText("UnzipFourth")+'\n',zipEntry.getName(),(float)file.length()/(1024),100.00f);
            }
            IO(zipInput.getNextEntry());
        }
    }
}

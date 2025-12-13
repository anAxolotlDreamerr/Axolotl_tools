package tool.zip;
import tool.IO;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compress {
    private static ZipOutputStream zipOutput;
    private static String initialName;
    private static String initialPath;
    private static long totalSize;
    private static long compressedSize;
    private static long useSize;
    private Compress() {
    }
    public static void compress(){
        try {
            totalSize =0;
            useSize =0;
            compressedSize =0;
            initialName =null;
            initialPath = null;
            System.out.println("((enter \"stop\" to stop))Please enter file path:(example:A:\\text\\text\\ or A:\\text\\text.text)");
            String filePath = IO.bufferedInput();
            if (filePath.equals("stop")||filePath.equals("Stop")){
                initialName =null;
                initialPath = null;
                return;
            }
            System.out.println("Please enter zip Path:(enter \"stop\" to stop)");
            String zipPath = IO.bufferedInput();
            if (zipPath.equals("stop")||zipPath.equals("Stop")){
                initialName =null;
                initialPath = null;
                return;
            }
            File file = new File(filePath);
            if(!zipPath.matches(".zip$")){
                if(new File(zipPath).isDirectory()){
                    zipPath = zipPath+file.getName()+".zip";
                }else {
                    zipPath = zipPath + ".zip";
                }
            }
            File zipFile = new File(zipPath);
            zipFile.getParentFile().mkdirs();
            zipOutput = new ZipOutputStream(new FileOutputStream(zipPath));
            System.out.println("Please enter zip comment:");
            String comment = IO.bufferedInput();
            long time1 = System.currentTimeMillis();
            zipOutput.setComment(comment);
            initialName = file.getName();
            initialPath = filePath;
            totalSize = calculateTheSize(file);
            io(file);
            long time2 = System.currentTimeMillis();
            if(totalSize != 0) {
                System.out.printf("Complete!Original Size:%.3fMB; Zip Size:%.3fMB; Compression Ratio:%.2f%% ;Take:%.3fs\n", (float)totalSize/(1024*1024), (float)useSize/(1024*1024), (float) 100 * useSize / totalSize,(float)(time2-time1)/1000);
            }else {
                System.out.printf("Complete!Original Size:%.3fMB; Zip Size:%.3fMB; Compression Ratio:%.2f%%;Take:%.3fs\n", (float)totalSize/(1024*1024), (float)useSize/(1024*1024), 100.00f,(float)(time2-time1)/1000);
            }
            zipOutput.close();
        }catch (Exception e){
            System.out.println("Please enter again");
            System.err.println(e);
            zipOutput = null;
            compress();
        }
        zipOutput = null;
        return;
    }
    public static long calculateTheSize(File file) {
        long temp =0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                temp += calculateTheSize(files[i]);
            }
            return temp;
        } else {
            return file.length();
        }
    }
    private static void io(File file) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                io(files[i]);
            }
        } else {
            InputStream input = new FileInputStream(file);
            String name = file.getPath().substring(initialPath.length()-initialName.length()-1);
            ZipEntry zipEntry = new ZipEntry(name);
            zipOutput.putNextEntry(zipEntry);
            int len;
            byte[] b = new byte[64 * 1024];
            System.out.printf("Adding:%s---original size:%.3fMB",file.getName(),(float)file.length()/(1024*1024));
            while ((len = input.read(b)) != -1) {
                zipOutput.write(b,0,len);
            }
            compressedSize += file.length();
            zipOutput.closeEntry();
            input.close();
            long temp=0;
            temp += zipEntry.getCompressedSize();
            useSize +=zipEntry.getCompressedSize();
            if(file.length() != 0) {
                System.out.printf("; compressed size:%.2fKB  (Compression ratio:%.2f%%; progress:%.2f%%)\n", (float)temp/1024, ((float) 100*temp / file.length()),((float)100*compressedSize/totalSize));
            }else {
                System.out.printf("; compressed size:%.2fkB  (Compression ratio:%.2f%%; progress:%.2f%%)\n", (float)temp/1024, 100.00f,((float)100*compressedSize/totalSize));
            }
        }
    }
}

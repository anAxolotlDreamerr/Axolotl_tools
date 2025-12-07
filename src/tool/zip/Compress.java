package tool.zip;
import tool.IO;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compress {
    private static ZipOutputStream zipOutput;
    private static String initialName;
    private static String initialPath;

    private Compress() {
    }

    public static void compress(){
        try {

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
            zipOutput.setComment(comment);
            initialName = file.getName();
            initialPath = filePath;
            io(file);
            zipOutput.close();
        }catch (Exception e){
            System.out.println("Please enter again");
            System.err.println(e);
            zipOutput = null;
            initialName =null;
            initialPath = null;
            compress();
        }
        zipOutput = null;
        initialName =null;
        initialPath = null;
        return;
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
            byte[] b = new byte[1024*1024*100];
            while ((len = input.read(b)) != -1) {
                zipOutput.write(b,0,len);
            }
            zipOutput.closeEntry();
            input.close();
        }
    }
}

import tool.IO;
import tool.Zip.ZipMenu;


public class Menu {
    private Menu(){};
    public static void start(){

        while(true){
            System.out.println("Please Enter Number:\n"+"1.Compressed/Uncompressed Files\n"+"Enter 2 to exit");
            int num;
            try{
                byte[] b= new byte[50];
                int len = IO.pushinput.read(b);
                if (new String(b,0,len).equals("exit")||new String(b,0,len).equals("Exit")){
                    System.exit(1);
                }
                IO.pushinput.unread(b,0,len);
                num = Integer.parseInt(IO.input.readLine());
            }catch (Exception e){
                System.out.println("Wrong!Please enter again.");
                continue;
            }
            switch (num){
                case 1 :
                    ZipMenu.menu;
                    break;
                default:
                    System.out.println("No this number!Please enter again.");
                    break;
            }
        }
    }
}

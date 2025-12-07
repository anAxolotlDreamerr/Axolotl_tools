import tool.IO;
import tool.menu.Menu;
import tool.zip.ZipMenu;


public class MainMenu {
    private static Menu menu = new Menu("Compressed/Uncompressed","exit");
    private MainMenu(){};
    public static void start() {
        while(true){
            int num=menu.menu();
            switch (num){
                case 1 :
                    ZipMenu.menu();
                    break;
                case 0:
                    System.out.println("Wrong!Please enter again.");
                    break;
                case -1:
                    System.exit(1);
                    break;
                default:
                    System.out.println("No this number!Please enter again.");
                    break;
            }
        }
    }
}

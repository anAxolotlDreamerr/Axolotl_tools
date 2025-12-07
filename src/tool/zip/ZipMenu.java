package tool.zip;
import tool.menu.Menu;

public class ZipMenu {
    public static Menu menu = new Menu("Compress","Unzip","back");
    private ZipMenu(){}
    public static void menu(){
        while(true){
            int num = menu.menu();
            switch (num){
                case 1:
                    Compress.compress();
                    break;
                case 2:
                    Unzip.unzip();
                    break;
                case -1:
                    return;
                case 0:
                    System.out.println("Wrong!Please enter again.");
                    break;
                default:
                    System.out.println("No this number!Please enter again.");
                    break;
            }

        }
    }
}

import tool.IO;
import tool.language.Language;
import tool.menu.Menu;
import tool.zip.ZipMenu;


public class MainMenu {
    private static Menu menu = new Menu("Compress/Extract","language","exit");
    private MainMenu(){};
    public static void start() {
        while(true){
            int num=menu.menu();
            switch (num){
                case 1 :
                    ZipMenu.menu();
                    break;
                case 2:
                    Language.language();
                    break;
                case 0:
                    System.out.println(Language.getText("EnterWrong"));
                    break;
                case -1:
                    return;
                default:
                    System.out.println(Language.getText("NullChoices"));
                    break;
            }
        }
    }
}

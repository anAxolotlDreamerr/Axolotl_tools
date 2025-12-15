package tool.zip;
import tool.language.Language;
import tool.menu.Menu;

public class ZipMenu {

    private ZipMenu(){}
    public static void menu(){
        Menu menu = new Menu(Language.getText("Compress"),Language.getText("Extract"),"back");
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
                    System.out.println(Language.getText("EnterWrong"));
                    break;
                default:
                    System.out.println(Language.getText("NullChoices"));
                    break;
            }

        }
    }
}

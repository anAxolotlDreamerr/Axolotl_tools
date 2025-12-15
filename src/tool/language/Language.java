package tool.language;

import tool.menu.Menu;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    private static final Locale zh_CN = new Locale("zh_CN","CH");
    private static final Locale en_US = new Locale("en","US");
    //private static ResourceBundle Using = ResourceBundle.getBundle("text",Locale.getDefault());
    private static ResourceBundle Using = ResourceBundle.getBundle("text",en_US);
    private Language(){};
    public static void language(){
        while (true) {
            Menu langugeMenu = new Menu("en_US", "zh_CN", "back");
            switch (langugeMenu.menu()) {
                case 1:
                    Using = ResourceBundle.getBundle("text", en_US);
                    break;
                case 2:
                    Using = ResourceBundle.getBundle("text", zh_CN);
                    break;
                case -1:
                    return;
                case 0:
                    System.out.println(getText("EnterWrong"));
                    break;
                default:
                    System.out.println(getText("NullChoices"));
                    break;
            }
        }
    }
    public static String getText(String text){
        return Using.getString(text);
    }
}

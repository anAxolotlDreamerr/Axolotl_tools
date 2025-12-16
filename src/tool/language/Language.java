package tool.language;

import tool.menu.Menu;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    private static final Menu langugeMenu = new Menu("en_US", "zh_CN","zh_TW","ja_JP","fr_FR", "back");
    private static final Locale zh_CN = new Locale("zh_CN","CH");
    private static final Locale en_US = new Locale("en","US");
    private static final Locale zh_TW = new Locale("zh","TW");
    private static final Locale ja_JP = new Locale("ja","JP");
    private static final Locale fr_FR = new Locale("fr","FR");
    //private static ResourceBundle Using = ResourceBundle.getBundle("text",Locale.getDefault());
    private static ResourceBundle Using = ResourceBundle.getBundle("text",Locale.getDefault(),new UTF8Control());
    private Language(){};
    public static void language(){
        while (true) {
            switch (langugeMenu.menu()) {
                case 1:
                    Using = ResourceBundle.getBundle("text", en_US);
                    System.out.printf(getText("changeLanguage")+"\n","en_US");
                    Menu.count();
                    break;
                case 2:
                    Using = ResourceBundle.getBundle("text", zh_CN);
                    System.out.printf(getText("changeLanguage")+"\n","zh_CN");
                    Menu.count();
                    break;
                case 3:
                    Using = ResourceBundle.getBundle("text", zh_TW);
                    System.out.printf(getText("changeLanguage")+"\n","zh_TW");
                    Menu.count();
                    break;
                case 4:
                    Using = ResourceBundle.getBundle("text", ja_JP);
                    System.out.printf(getText("changeLanguage")+"\n","ja_JP");
                    Menu.count();
                    break;
                case 5:
                    Using = ResourceBundle.getBundle("text", fr_FR);
                    System.out.printf(getText("changeLanguage")+"\n","fr_FR");
                    Menu.count();
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

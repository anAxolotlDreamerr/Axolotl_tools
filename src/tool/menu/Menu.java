package tool.menu;

import tool.IO;
import tool.language.Language;

public  class Menu {
    private static int counter = 1;
    private int nativeCounter= 0;
    private static Menu[] menus;
    private int len =0;
    private String[] choices;
    private StringBuffer text = new StringBuffer();
    public Menu(String... choices){
        this.choices = choices;
    }
    public static String capitalInitials(String s){
            if (s == null || s.isEmpty()) {
                return s;
            }
            return s.substring(0, 1).toUpperCase() + s.substring(1);

    }
    public int menu(){
        if(nativeCounter != counter) {
            text.delete(0,text.length());
            text = text.append(Language.getText("MenuFirst") +'\n');
            for (int i = 0; i < choices.length - 1; i++) {
                text = text.append((i + 1) + "." + Language.getText(choices[i]) + "\n");
            }
            text.append(Language.getText("Enter")+"\"" + choices[choices.length - 1] + "\" "+Language.getText("to") + Language.getText(choices[choices.length - 1]));
            nativeCounter = counter;
        }
        System.out.println(text);
            int num =0;
            try{
                byte[] b= new byte[50];
                int len = IO.inputStream.read(b);
                String str = new String(b,0,len).replaceAll("[^\\d\\w]","");
                if (str.equals(choices[choices.length-1])||str.equals(capitalInitials(choices[choices.length-1]))){
                    return -1;
                }
                num = Integer.parseInt(str);
            }catch (Exception e){
                System.err.println(e);
                return num;
            }
            return num;
    }
    public static void count(){
        counter++;
    }
    public boolean countEqual(){
        return this.nativeCounter == counter;
    }
}

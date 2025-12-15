package tool.menu;

import tool.IO;
import tool.language.Language;

public  class Menu {
    private int len =0;
    private String[] choices;
    private StringBuffer text = new StringBuffer();
    private boolean sign =true;
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
        if(sign) {
            text = text.append(Language.getText("MenuFirst") +'\n');
            for (int i = 0; i < choices.length - 1; i++) {
                text = text.append((i + 1) + "." + choices[i] + "\n");
            }
            text.append(Language.getText("Enter")+"\"" + choices[choices.length - 1] + "\" "+Language.getText("to") + choices[choices.length - 1]);
            sign =false;
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
}

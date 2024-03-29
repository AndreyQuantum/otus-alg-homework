package org.azelentsov.otusHw.task29BojerMur;

public class Solution29BojerMur {

    private int[] shift;
    private String template;

    public Solution29BojerMur(String template) {
//        Делаем массив сдвигов. Если совпал последний символ, то сдвиг будет находится в посл. ячейке
        shift = new int[template.length()];
        this.template = template;
        populateShiftArray();
    }

    private void populateShiftArray(){
        for (int i = template.length()-1; i >=0; i--){
//            Достаем подстроку которая совпала
            String templToCompare = template.substring(i);
//            Находим ближайшее совпадение подстроки
            int t = templToCompare.length() -1;

            for (int m = i; m >=0; m--){
//                Если у нас совпадает текущий символ шаблона - переходим к сравнению предыдущего
                if (template.charAt(m) == templToCompare.charAt(t) && i != m){
//                если у нас все символы совпали - записываем сдвиг и выходим из цикла
                    if (t <= 0){
                        shift[i] = i - m ;
                        break;
                    }
                    t--;
                }
            }
//            Если у нас не полное совпадение, то мы выполняем сдвиг на длину шаблона
            if (t > 0 || shift[i] <=0){
                shift[i] = template.length();
            }
        }
    }

    public int find(String text){
        int t = template.length() -1;
        for (int i = text.length() -1; i >=0; i--){
            if (text.charAt(i) == template.charAt(t)){
                t--;
            } else {
                i += shift[t];
                t = template.length() - 1;
            }
            if (t <=0){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        var test = new Solution29BojerMur("BC-ABC-BC-C-ABC");
        System.out.println(test.find("BC-BC-ABC-BC-C-ABC"));
    }
}

package chapter09;

/**
 * Date: 01/06/13
 * Time: 14:41
 */
public class StringsJava {
    public static void main(String[] args) {
        String string1 = "This a normal string";
        String string2 = "This a string with \"quotes in the middle\"";
        String string3 = "This is a string with \\ in the middle";
        String string4 = "This a string " + "divided in two";
        System.out.println(string1 + string2 + string3 + string4);
    }
}

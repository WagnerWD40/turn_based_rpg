package sistema.systems.graphics;

import java.util.HashMap;
import java.util.Map;

public class TextSpriteUrlMap {

    private static TextSpriteUrlMap single = new TextSpriteUrlMap();

    private String[] fileNames = {
            "uA", "uB", "uC", "uD", "uE", "uF", "uG", "uH", "uI", "uJ", "uK", "uL", "uM", "uN", "uO", "uP", "uQ",
            "uR", "uS", "uT", "uU", "uV", "uW", "uX", "uY", "uZ", "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
            "3", "4", "5", "6", "7", "8", "9", "exclamation", "interrogation", "backslash", "colon", "doubleQuotes",
            "hash", "plus", "parenStart", "parenEnd", "percent", "til", "undentified", "blank"
    };

    private String[] charTable = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
            "3", "4", "5", "6", "7", "8", "9", "!", "?", "/", ":", "\"",
            "#", "+", "(", ")", "%", "~", "undentified", " "
    };

    private Map<String, String> charMap = new HashMap<>();

    public TextSpriteUrlMap() {
        for (int i = 0; i < charTable.length; i++) {
            String spriteUrl = charMap.put(charTable[i], "sprites/font/" + fileNames[i] + ".png");
        }
    }

    public static TextSpriteUrlMap get() {
        return single;
    }

    public String getCharFile(String character) {
         if(charMap.get(character) != null) {
             return charMap.get(character);
         } else {
             System.err.println("Sprite for the character > " + character + " < does not exist." );
             System.exit(0);
             return "";
         }
    }
}

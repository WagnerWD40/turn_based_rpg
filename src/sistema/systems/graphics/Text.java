package sistema.systems.graphics;

import java.util.HashMap;
import java.util.Map;

public class Text {

    private CharSprite charSprite = new CharSprite();

    private int x;
    private int y;
    private String textString;

    public Text(String textString, int x, int y) {
        this.textString = textString;
        this.x = x;
        this.y = y;
    }

    public void draw () {

    }


    private class CharSprite {

        private String[] fileNames = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
                "3", "4", "5", "6", "7", "8", "9", "exclamation", "interrogation", "backslash", "colon", "doubleQuotes",
                "hash", "plus", "parenStart", "parenEnd", "percent", "til", "undentified"
        };

        private String[] charTable = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
                "3", "4", "5", "6", "7", "8", "9", "!", "?", "/", ":", "\"",
                "#", "+", "(", ")", "%", "~", "undentified"
        };

        private Map<String, String> charMap = new HashMap<>();

        public CharSprite() {
            for (int i = 0; i < charTable.length; i++) {
                charMap.put(charTable[i], "sprites/font/" + fileNames[i] + ".png");
            }
        }

        public String getCharFile(String character) {
            return charMap.get(character);
        }
    }
}

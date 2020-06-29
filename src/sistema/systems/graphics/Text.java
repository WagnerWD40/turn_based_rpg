package sistema.systems.graphics;

import java.awt.*;
import java.util.ArrayList;

public class Text {

    private CharSprite charSprite;

    private int x;
    private int y;
    private String textString;
    ArrayList<CharSprite> textSprites = new ArrayList<>();

    public Text(String textString, int x, int y) {
        this.textString = textString;
        this.x = x;
        this.y = y;

        for (int i = 0; i < textString.length(); i++) {
            CharSprite charSprite = new CharSprite(Character.toString(textString.charAt(i)));
            this.textSprites.add(charSprite);
        }
    }
    public void draw (Graphics g) {

        for (int i = 0; i < textSprites.size(); i++) {
            textSprites.get(i).getSprite().draw(g, (int) x + (i * 23), (int) y);
        }
    }

}

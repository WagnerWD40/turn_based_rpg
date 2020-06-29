package sistema.systems.graphics;

public class CharSprite {

    private String character;

    private Sprite sprite;

    public CharSprite(String character) {
        String spriteUrl = TextSpriteUrlMap.get().getCharFile(character);
        this.sprite = SpriteStore.get().getSprite(spriteUrl);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
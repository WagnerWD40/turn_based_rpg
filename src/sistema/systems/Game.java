package sistema.systems;

import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;
import sistema.systems.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas {

    private BufferStrategy strategy;

    private boolean gameRunning = true;

    private ArrayList<Entity> entities = new ArrayList<>();

    private ArrayList<Entity> removeList = new ArrayList<>();

    private ArrayList<BackgroundEntity> backgroundEntities = new ArrayList<>();

    private ArrayList<BackgroundEntity> backgroundRemoveList = new ArrayList<>();

    private ArrayList<PlayerCharacter> playerParty;

    private ArrayList<EnemyCharacter> enemyParty;

    private Entity background;

    private Entity battleMenu;

    private ArrayList<BattleSlot> playerSlots = new ArrayList<>();

    private int maxPlayerSlots = 4;

    private ArrayList<BattleSlot> enemySlots = new ArrayList<>();

    private int maxEnemySlots = 5;

    private int totalInitiativeNeededForTurn = 4000;

    public Game(ArrayList<PlayerCharacter> playerParty, ArrayList<EnemyCharacter> enemyParty) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;

        // create the frame
        JFrame container = new JFrame("RPG Battle");

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(800, 700));
        panel.setLayout(null);

        // setup canvas size (drawn portion)
        setBounds(0, 0, 800, 700);
        panel.add(this);

        setIgnoreRepaint(true);

        // make the window visible
        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        container.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // create the buffering strategy which will allow AWT to manage our accelerated graphics
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        initEntities();
    }

    public void gameLoop() {
        long lastLoopTime = System.currentTimeMillis();

        while (gameRunning) {
            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();

            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0, 0, 800, 700);

            // Draw the background, menu, etc
            for (BackgroundEntity background: backgroundEntities) {
                background.draw(g);
            }

            // Draw the sprites
            for (Entity entity: entities) {
                entity.draw(g);
            }

            // END of GRAPHICS logic
            entities.removeAll(removeList);
            removeList.clear();

            g.dispose();
            strategy.show();

            // START of GAME logic
            for (PlayerCharacter playerCharacter: playerParty) {
                int playerSpeed = playerCharacter.getSpeed();
                int playerInitiative = playerCharacter.getInitiative();

                playerCharacter.setInitiative(playerInitiative + playerSpeed);

                if (playerInitiative > totalInitiativeNeededForTurn) {
                    System.out.println(playerCharacter + " TURN TO PLAY");
                    playerCharacter.setInitiative(0);
                }
            }

            for (EnemyCharacter enemyCharacter: enemyParty) {
                int playerSpeed = enemyCharacter.getSpeed();
                int playerInitiative = enemyCharacter.getInitiative();

                enemyCharacter.setInitiative(playerInitiative + playerSpeed);

                if (playerInitiative > totalInitiativeNeededForTurn) {
                    System.out.println(enemyCharacter + " TURN TO PLAY");
                    enemyCharacter.setInitiative(0);
                }
            }

            try { Thread.sleep(10); } catch (Exception e) {}
        }
    }

    private void initEntities() {

        // Creating background and menu
        background = new BackgroundEntity(
                this,
                "sprites/plains_background.png",
                0,
                0
        );

        battleMenu = new BackgroundEntity(
                this,
                "sprites/battle_menu.png",
                0,
                background.getSprite().getHeight()
        );

        backgroundEntities.add((BackgroundEntity) background);
        backgroundEntities.add((BackgroundEntity) battleMenu);

        // Creating players slots in battle
        for (int i = 0; i < maxPlayerSlots; i++) {
            BattleSlot battleSlot = new BattleSlot(
                    i,
                    700 - (i * 40),
                    350 - (i * 50),
                    true
            );

            playerSlots.add(battleSlot);
        }

        // Creating enemy slots
        for (int i = 0; i < maxEnemySlots; i++) {
            BattleSlot battleSlot = new BattleSlot(
                    i,
                    50 + (i % 2 == 0 ? 190 : 0),
                    350 - (i % 2 == 0 ? (i * 50) : ((i - 1) * 50)),
                    false
            );

            enemySlots.add(battleSlot);
        }

        // Populating the player slots with current party members
        for (int i = 0; i < playerParty.size(); i++ ) {
            CharacterEntity playerCharacter = new CharacterEntity(
                    this,
                    playerParty.get(i).getMainBattleSprite(),
                    playerSlots.get(i).getX(),
                    playerSlots.get(i).getY(),
                    playerParty.get(i)
            );

            entities.add(playerCharacter);
        }

        // Populating the enemy slots with current party members
        for (int i = 0; i < enemyParty.size(); i++ ) {
            CharacterEntity enemyCharacter = new CharacterEntity(
                    this,
                    enemyParty.get(i).getMainBattleSprite(),
                    enemySlots.get(i).getX(),
                    enemySlots.get(i).getY(),
                    enemyParty.get(i)
            );

            entities.add(enemyCharacter);
        }
    }

    public void removeEntity(Entity entity) {
        removeList.add(entity);
    }
}

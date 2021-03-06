package sistema.systems.battle;

import sistema.characters.Actor;
import sistema.characters.EnemyCharacter;
import sistema.characters.PlayerCharacter;
import sistema.systems.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Battle extends Canvas {

    private BufferStrategy strategy;

    private boolean gameRunning = true;

    private ArrayList<Actor> actors = new ArrayList<>();
    private ArrayList<Actor> removeList = new ArrayList<>();
    private ArrayList<BackgroundEntity> backgroundEntities = new ArrayList<>();
    private ArrayList<BackgroundEntity> backgroundRemoveList = new ArrayList<>();

    private ArrayList<PlayerCharacter> playerParty;
    private ArrayList<PlayerCharacter> playerPartyRemoveList = new ArrayList<>();;
    private ArrayList<EnemyCharacter> enemyParty;
    private ArrayList<EnemyCharacter> enemyPartyRemoveList = new ArrayList<>();;

    private Entity background;
    private Entity battleMenuBackground;

    // GUI
    private BattleMenu battleMenu;
    private Text text;
    private KeyInputHandler playerController = new KeyInputHandler();

    // positional variables
    private ArrayList<BattleSlot> playerSlots = new ArrayList<>();
    private int maxPlayerSlots = 4;
    private ArrayList<BattleSlot> enemySlots = new ArrayList<>();
    private int maxEnemySlots = 5;

    // Time to take a turn
    private int totalInitiativeNeededForTurn = 4000;

    private Random random = new Random();

    public Battle(ArrayList<PlayerCharacter> playerParty, ArrayList<EnemyCharacter> enemyParty) {
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

        // key input
        addKeyListener(playerController);

        // request the focus so key events come to us
        requestFocus();

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
            for (Actor actor: actors) {
                actor.draw(g);
            }

            battleMenu.draw(g);

            if (battleMenu.isAcceptPlayerCommands()) {
                battleMenu.drawCursor(g);
            }


            // END of GRAPHICS logic
            actors.removeAll(removeList);
            removeList.clear();

            enemyParty.removeAll(enemyPartyRemoveList);
            enemyPartyRemoveList.clear();

            playerParty.removeAll(playerPartyRemoveList);
            playerPartyRemoveList.clear();

            g.dispose();
            strategy.show();


            //System.out.println(enemyParty);
            // START of GAME logic
            turnLogic();

//            if (enemyParty.isEmpty() || playerParty.isEmpty()) {
//                System.exit(0);
//            }

            try { Thread.sleep(10); } catch (Exception e) {}
        }
    }

    private void turnLogic() {
        for (Actor actor: actors) {

            if (actor.getHpCurrent() <= 0 && actor instanceof EnemyCharacter) {
                enemyPartyRemoveList.add((EnemyCharacter) actor);
                continue;
            }

            if (actor.getHpCurrent() <= 0 && actor instanceof PlayerCharacter) {
                playerPartyRemoveList.add((PlayerCharacter) actor);
                continue;
            }

            int playerSpeed = actor.getSpeed();
            int playerInitiative = actor.getInitiative();

            actor.setInitiative(playerInitiative + playerSpeed);

            if (playerInitiative > totalInitiativeNeededForTurn) {

                if (actor instanceof EnemyCharacter) {

                    enemyTurn((EnemyCharacter) actor);
                } else if (actor instanceof PlayerCharacter) {
                    playerTurn((PlayerCharacter) actor);

                }

                actor.setInitiative(0);
            }
        }

        for (Actor actor: actors) {
            if (actor.getHpCurrent() <= 0) {
                removeList.add(actor);
                System.out.println(actor + " has died.");
            }
        }
    }

    private void enemyTurn(EnemyCharacter enemyCharacter) {
        PlayerCharacter playerToBeAttacked = chooseRandomCharacter(playerParty);

        enemyCharacter.attackOtherActor(playerToBeAttacked);
    }

    private void playerTurn(PlayerCharacter playerCharacter) {
        //System.out.println(enemyParty);
        battleMenu.setAcceptPlayerCommands(true);
        if (battleMenu.isCommandWasSelected()) {
            EnemyCharacter enemyToBeAttacked = enemyParty.get(0);
            playerCharacter.attackOtherActor(enemyToBeAttacked);
            battleMenu.setAcceptPlayerCommands(false);
            battleMenu.setCommandWasSelected(false);

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

        battleMenuBackground = new BackgroundEntity(
                this,
                "sprites/battle_menu.png",
                0,
                background.getSprite().getHeight()
        );

        backgroundEntities.add((BackgroundEntity) background);
        backgroundEntities.add((BackgroundEntity) battleMenuBackground);

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
            int playerSpriteX = playerSlots.get(i).getX();
            int playerSpriteY = playerSlots.get(i).getY();

            playerParty.get(i).setX(playerSpriteX);
            playerParty.get(i).setY(playerSpriteY);

            actors.add(playerParty.get(i));
        }

        // Populating the enemy slots with current party members
        for (int i = 0; i < enemyParty.size(); i++ ) {
            int playerSpriteX = enemySlots.get(i).getX();
            int playerSpriteY = enemySlots.get(i).getY();

            enemyParty.get(i).setX(playerSpriteX);
            enemyParty.get(i).setY(playerSpriteY);

            actors.add(enemyParty.get(i));
        }

        battleMenu = new BattleMenu(playerParty, enemyParty);
    }

    public void removeEntity(Actor actor) {
        removeList.add(actor);
    }

    private PlayerCharacter chooseRandomCharacter(ArrayList<PlayerCharacter> party) {

        PlayerCharacter choosenCharacter;

        if (party.size() == 1) {
            choosenCharacter = party.get(0);
        }

        choosenCharacter = party.get(random.nextInt(party.size()));

        if (choosenCharacter.getHpCurrent() <= 0) {
            return chooseRandomCharacter(party);
        } else {
            return choosenCharacter;
        }
    }

    public class KeyInputHandler extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (battleMenu.isCommandWasSelected() &&
                        !battleMenu.isEnemyWasSelected() &&
                        battleMenu.isAcceptPlayerCommands()) {
                    battleMenu.setSelectedEnemy(battleMenu.getSelectedEnemy() - 1);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (battleMenu.isCommandWasSelected() &&
                        !battleMenu.isEnemyWasSelected() &&
                        battleMenu.isAcceptPlayerCommands()) {
                    battleMenu.setSelectedEnemy(battleMenu.getSelectedEnemy() + 1);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (battleMenu.isAcceptPlayerCommands()) {
                    battleMenu.setSelectedCommand(battleMenu.getSelectedCommand() - 1);
                }

            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (battleMenu.isAcceptPlayerCommands()) {
                    battleMenu.setSelectedCommand(battleMenu.getSelectedCommand() + 1);
                }

            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!battleMenu.isCommandWasSelected()) {
                    battleMenu.setCommandWasSelected(true);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if(battleMenu.isCommandWasSelected()) {
                    battleMenu.setCommandWasSelected(false);
                }
            }
        }

    }
}

package main.states;

import main.Config;
import main.Game;
import main.button.Button;
import main.button.Screen;
import main.gfx.AssetManager;
import main.gfx.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MenuState extends State
{
    private Game game;
//     Create Buttons
    private Button storyButton;
    private Button survivalButton;
    private Button exitButton;
    private Button startButton;
    private Button cancelButton;

//    For Blinking Lights
    private Screen defaultBG;
    private Screen dottedBG;

    // TODO Auto-generated method stub


    public MenuState(Game game)
    {
        this.game = game;
//        Coordinate in Frame
        survivalButton = new Button(game, new Point(364, 465), new Point(85, 50), 0, "survival");
        storyButton = new Button(game, new Point(480, 465), new Point(85, 50), 88, "story");
        exitButton = new Button(game, new Point(606, 465), new Point(85, 50), 264, "exit");
        startButton = new Button(game, new Point(435, 385), new Point(85, 50), 176, "start");
        cancelButton = new Button(game, new Point(548, 385), new Point(85, 50), 352, "cancel");

        dottedBG = new Screen(game, new Point(169, 69), new Point(463, 222), Config.DOTTED_BACKGROUND_ASSET_PATH, "dot");
        defaultBG = new Screen(game, new Point(169, 69), new Point(463, 222), Config.MENU_BACKGROUND_ASSET_PATH, "default");
    }
    private double flickerAnimation = 0;
    private double brokenSurvivalAnimation = 0;
    private double brokenStoryAnimation = 0;
    private Random rand = new Random();
    private int random;

    //    Mouse Click
    private boolean survivalIsPressed = false;
    private boolean storyIsPressed = false;

//    For broken screen delay
    private boolean survivalNext = false;
    private boolean storyNext = false;


    @Override
    public void tick() {
        int x = game.getMouseManager().getMouseX();
        int y = game.getMouseManager().getMouseY();

        dottedBG.getCurrentScreen();
        defaultBG.getCurrentScreen();
        startButton.disabledImage();
        cancelButton.disabledImage();

        flickerAnimation++;
        if(flickerAnimation % Config.FLICKER_ANIMATION_DELAY == 0){
            random = rand.nextInt(2);
            flickerAnimation = 0;
        }

//      NEXT STATE
        if(survivalIsPressed){
            brokenSurvivalAnimation++;
            if(brokenSurvivalAnimation % Config.BROKEN_SURVIVAL_ANIMATION_DELAY == 0){
                survivalNext = true;
                brokenSurvivalAnimation = Config.BROKEN_SURVIVAL_ANIMATION_DELAY;
            }
        }

        if(storyIsPressed){
            brokenStoryAnimation++;
            if(brokenStoryAnimation % Config.BROKEN_STORY_ANIMATION_DELAY == 0){
                storyNext = true;
                brokenStoryAnimation = Config.BROKEN_STORY_ANIMATION_DELAY;
            }
        }

        if(survivalButton.isInside(x, y)){
            survivalButton.hoveredImage();
            //If button clicked
            if(game.getMouseManager().getMouseButtonState(MouseEvent.BUTTON1)) {

                //Animate button
                survivalButton.clickedImage();
                survivalIsPressed = true;
            }
        }
        else{
            survivalButton.unhoveredImage();
        }

        if(storyButton.isInside(x, y)){
            storyButton.hoveredImage();
            //If button clicked
            if(game.getMouseManager().getMouseButtonState(MouseEvent.BUTTON1)) {
                //Animate button
                storyButton.clickedImage();
                storyIsPressed = true;
            }
        }
        else{
            storyButton.unhoveredImage();
        }
//        System.out.println(game.getDeltaPlease());
        if(exitButton.isInside(x, y)){
            exitButton.hoveredImage();
            //If button clicked
            if(game.getMouseManager().getMouseButtonState(MouseEvent.BUTTON1)) {
                //Animate button
                exitButton.clickedImage();
            }
        }
        else{
            exitButton.unhoveredImage();
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(AssetManager.getInstance().getDefaultBGImage(), 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
        survivalButton.draw(g);
        storyButton.draw(g);
        exitButton.draw(g);
        startButton.draw(g);
        cancelButton.draw(g);

//      Animation in main menu
        if(random % 2 == 0){
            dottedBG.draw(g);
        }
        else{
            defaultBG.draw(g);
        }

//        Next state
        if(survivalNext){
            setState(new SurvivalMenuState(game));
        }

        if(storyNext){
            setState(new StoryMenuState(game));
        }
    }
}

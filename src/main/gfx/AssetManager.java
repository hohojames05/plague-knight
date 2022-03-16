package main.gfx;

import main.Config;

import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AssetManager
{
    private BufferedImage map;
    private BufferedImage player;
    private BufferedImage HUD;
    private BufferedImage zombie;
    private BufferedImage crate;
    private BufferedImage item;
    private BufferedImage menuButtonImage;
    private BufferedImage difficultyImage;
    private BufferedImage defaultBGImage;
    private BufferedImage dottedBGImage;
    private BufferedImage exitBGImage;
    private BufferedImage survivalBrokenBGImage;
    private BufferedImage storyBrokenBGImage;
    private BufferedImage pausedImage;
    private BufferedImage youDiedImage;
    private BufferedImage treeObstacle;
    private AudioInputStream audio1; // samples
    private Font arcadeClassic;

    private static AssetManager instance = null;

    private AssetManager()
    {
    }

    public static AssetManager getInstance()
    {
        if(instance == null)
        {
            instance = new AssetManager();
        }
        return instance;
    }

    public boolean load()
    {
        try
        {
            map = ImageLoader.loadImage(Config.MAP_PATH);
            player = ImageLoader.loadImage(Config.PLAYER_SPRITE_PATH);
            zombie = ImageLoader.loadImage(Config.ZOMBIE_ASSET_PATH);
            crate = ImageLoader.loadImage(Config.CRATE_ASSET_PATH);
            HUD = ImageLoader.loadImage(Config.HUD_ASSET_PATH);
            item = ImageLoader.loadImage(Config.ITEMS_ASSET_PATH);
            menuButtonImage = ImageLoader.loadImage(Config.MENU_BUTTON_ASSET_PATH);
            difficultyImage = ImageLoader.loadImage(Config.DIFFICULTY_ASSET_PATH);
            defaultBGImage = ImageLoader.loadImage(Config.MENU_BACKGROUND_ASSET_PATH);
            dottedBGImage = ImageLoader.loadImage(Config.DOTTED_BACKGROUND_ASSET_PATH);
            exitBGImage = ImageLoader.loadImage(Config.EXIT_MENU_ASSET_PATH);
            survivalBrokenBGImage = ImageLoader.loadImage(Config.BROKEN_SURVIVAL_BACKGROUND_ASSET_PATH);
            storyBrokenBGImage = ImageLoader.loadImage(Config.BROKEN_STORY_BACKGROUND_ASSET_PATH);
            pausedImage = ImageLoader.loadImage(Config.PAUSED_TEXT_ASSET_PATH);
            youDiedImage = ImageLoader.loadImage(Config.YOU_DIED_ASSET_PATH);

            audio1 = AudioLoader.loadAudio("mainmenumusic.wav");

            arcadeClassic = Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")).deriveFont(72f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")));
//            treeObstacle = ImageLoader.loadImage(Config.TREE_OBSTACLE_ASSET_PATH);
        }
        catch(IllegalArgumentException | IOException | FontFormatException e)
        {
            System.err.println("Failed to load assets.");
            return false;
        }
        return true;
    }

    public BufferedImage getPlayer()
    {
        return player;
    }

    public BufferedImage getZombie()
    {
        return zombie;
    }

    public BufferedImage getCrate()
    {
        return crate;
    }

    public BufferedImage getItem()
    {
        return item;
    }

    public BufferedImage getMenuButtonImage() {
        return menuButtonImage;
    }

    public BufferedImage getDifficultyImage() {
        return difficultyImage;
    }

    public BufferedImage getDefaultBGImage() {
        return defaultBGImage;
    }

    public BufferedImage getDottedBGImage() {
        return dottedBGImage;
    }

    public BufferedImage getSurvivalBrokenBGImage() {
        return survivalBrokenBGImage;
    }

    public BufferedImage getStoryBrokenBGImage() {
        return storyBrokenBGImage;
    }

    public BufferedImage getExitBGImage() {
        return exitBGImage;
    }

    public BufferedImage getPausedImage(){
        return pausedImage;
    }

    public BufferedImage getHUD() {
        return HUD;
    }

    public BufferedImage getYouDiedImage() {
        return youDiedImage;
    }

    public BufferedImage getTreeObstacle()
    {
        return treeObstacle;
    }

    public BufferedImage getMap()
    {
        return map;
    }

    public AudioInputStream getAudio1()
    {
        return audio1;
    }

    public Font getArcadeClassic() {
        return arcadeClassic;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel{
    private int xRows, yColumns;
    
    private Player player;
    
    private Endfield endfield;
    
    private ArrayList<Integer> randomlyGeneratedCodes;
    
    private ArrayList<Entity> entities;
    private ArrayList<Entity> copyOfEntities;
    
    private int barricadeAmount;
    private int keyAndBoxAmount;
    
    public GamePanel(int x, int y, int barricadeAmount, int keyAndBoxAmount){
        this.xRows = x;
        this.yColumns = y;
        this.barricadeAmount = barricadeAmount;
        this.keyAndBoxAmount = keyAndBoxAmount;
        entities = new ArrayList<>();
        copyOfEntities = new ArrayList<>();
        randomlyGeneratedCodes = new ArrayList<>();
        SetPlayer();
        SetEndfield();
        Initialize();
    }
    
    public void Initialize(){
        this.InitializeRandomlyGeneratedCodes(keyAndBoxAmount);
        this.InitializeEntities(barricadeAmount, "barricade");
        this.InitializeEntities(keyAndBoxAmount, "key");
        this.InitializeEntities(keyAndBoxAmount, "box");
        copyOfEntities.addAll(entities);
    }
    
    public void SetPlayer(){
        player = new Player(0, 0, "resources/player.png", this);
        //player = new Player(0, 0, "C:/Memes/player.jpg", this);
        entities.add(player);
    }
    
    public void SetEndfield(){
        //GamePanel.class.getResource("/resources/endfield.png");
        endfield = new Endfield(9, 9, "resources/endfield.jpg");
        //endfield = new Endfield(9, 9, "C:/Memes/endfield.png");
        entities.add(endfield);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.white);
        for(int i = 0; i < xRows; i++){
            for(int j = 0; j < yColumns; j++){
                g2d.fillRect(i*50, j*50, 45, 45);
                for(Entity e : entities){
                    if(i == e.getXCoord() && j == e.getYCoord()){
                        //if(e.getClassString() == "Endfield"){
                            g2d.drawImage(e.getImg(), e.getXCoord() * 50, e.getYCoord() * 50, this);
                            JLabel renderer = new JLabel(e.getCodeString());
                            renderer.setForeground(Color.BLACK);
                            this.add(renderer);
                            CellRendererPane crp = new CellRendererPane();
                            crp.paintComponent(g2d, renderer, this, e.getXCoord() * 50, e.getYCoord() * 50, 10, 10);
//                        }
//                        else{
//                            g2d.drawImage(e.getImg(), e.getXCoord() * 50, e.getYCoord() * 50, this);
//                            JLabel renderer = new JLabel(e.getCodeString());
//                            renderer.setForeground(Color.BLACK);
//                            this.add(renderer);
//                            CellRendererPane crp = new CellRendererPane();
//                            crp.paintComponent(g2d, renderer, this, e.getXCoord() * 50, e.getYCoord() * 50, 10, 10);
//                        }
                    }
                }
            }
        }
    }
    
    public void movingIntoWhichObject(boolean movingIntoObject, String classString){
        for(Entity ent : entities){
            if(ent.getXCoord() == player.getXCoord() && ent.getYCoord() == player.getYCoord() - 1){
                movingIntoObject = true;
                classString = ent.getClassString();
            }
        }
    }
    
    public void InitializeRandomlyGeneratedCodes(int keyAndBoxAmount){
        for(int i = 0; i < keyAndBoxAmount; i++){
            int randomlyGeneratedNumber = (int) (Math.random() * 9) + 1;
            randomlyGeneratedCodes.add(randomlyGeneratedNumber);
        }
    }
    
    public void InitializeEntities(int maxEntityAmount, String type){
//        String barricadeImg = "C:/Memes/brick.png";
//        String keyImg = "C:/Memes/key.png";
//        String boxImg = "C:/Memes/box.png";
        String barricadeImg = "resources/brick.png";
        String keyImg = "resources/key.jpg";
        String boxImg = "resources/box.jpg";
        int currentEntityAmount = 0;
        int index = 0;
        while(currentEntityAmount < maxEntityAmount){
            int randomlyGeneratedX = (int) (Math.random() * 10);
            int randomlyGeneratedY = (int) (Math.random() * 10);
            int sumRandomNumbers = randomlyGeneratedX + randomlyGeneratedY;
            
            int correctlyGeneratedX = 0;
            int correctlyGeneratedY = 0;
            if(sumRandomNumbers > 1 && sumRandomNumbers != 18){
                correctlyGeneratedX = randomlyGeneratedX;
                correctlyGeneratedY = randomlyGeneratedY;
            }
            else{
                continue;
            }
            
            boolean alreadyExists = false;
            
            if(entities.size() > 0){
                for(Entity e : entities){
                    if(e.getXCoord() == correctlyGeneratedX && e.getYCoord() == correctlyGeneratedY){
                        alreadyExists = true;
                    }
                }
            }
            
            if(alreadyExists){
                continue;
            }
            
            Entity newEntity = null;
            
            switch(type){
                case "barricade":
                    newEntity = new Barricade(correctlyGeneratedX, correctlyGeneratedY, barricadeImg);
                    break;
                case "key":
                    newEntity = new Key(correctlyGeneratedX, correctlyGeneratedY, keyImg, randomlyGeneratedCodes.get(index));
                    break;
                case "box":
                    newEntity = new Box(correctlyGeneratedX, correctlyGeneratedY, boxImg, randomlyGeneratedCodes.get(index));
                    break;
            }
            entities.add(newEntity);
            currentEntityAmount++;
            index++;
        }
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }
    
    public Entity getEntity(int x, int y){
        Entity entityOnXY = null;
        for(Entity e : entities){
            if(e.getXCoord() == x && e.getYCoord() == y){
                entityOnXY = e;
            }
        }
        return entityOnXY;
    }
    
    public void restartGame(){
        entities.clear();
        entities.addAll(copyOfEntities);
        player.setXCoord(0);
        player.setYCoord(0);
        player.setKey(null);
        player.setPlayerCanMove(true);
        this.repaint();
    }
    
    public void newGame(){
        entities.clear();
        copyOfEntities.clear();
        randomlyGeneratedCodes.clear();
        player.setXCoord(0);
        player.setYCoord(0);
        player.setKey(null);
        player.setPlayerCanMove(true);
        entities.add(player);
        entities.add(endfield);
        
        this.Initialize();
        this.repaint();
    }
}

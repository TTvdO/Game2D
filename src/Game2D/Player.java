/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity{
    private GamePanel gamePanel;
    private Key key;
    
    public Player(int x, int y, String imgLocation, GamePanel gamePanel){
        super(x, y, imgLocation);
        key = null;
        this.gamePanel = gamePanel;
    }
    
    @Override
    public String getClassString(){
        return "Player";
    }
    
    public boolean hasKey(){
        if(key != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Key getKey(){
        return key;
    }
    
    public void setKey(Key key){
        this.key = key;
    }
    
    public int getCode(){
        if(this.key == null){
            return 0;
        }
        return this.key.getCode();
    }
    
    public String getCodeString(){
        if(this.key == null){
            return "";
        }
        else{
            return this.key.getCodeString();
        }
    }
    
    private ArrayList<Entity> allEntities = null;
    private String classString = null;
    private boolean movingIntoObject = false;
    private Entity entityInTheWay = null;
    private boolean playerCanMove = true;
    
    public void move(KeyEvent e){
        movingIntoObject = false;
        if(playerCanMove){
            String direction = null; 
            if(e.getKeyCode() == KeyEvent.VK_UP){
                direction = "up";
                if(this.getYCoord() > 0){
                    movingIntoObject(direction);
                    if(movingIntoObject){
                        checkObjectAndTakeAction(direction);
                    }
                    else{
                        moveUp();
                    }
                }

            }

            else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                direction = "down";
                if(this.getYCoord() < 9){
                    movingIntoObject(direction);

                    if(movingIntoObject){
                        checkObjectAndTakeAction(direction);
                    }
                    else{
                        moveDown();
                    }
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                direction = "left";
                if(this.getXCoord() > 0){
                    movingIntoObject(direction);

                    if(movingIntoObject){
                        checkObjectAndTakeAction(direction);
                    }
                    else{
                        moveLeft();
                    }
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                direction = "right";
                if(this.getXCoord() < 9){
                    movingIntoObject(direction);

                    if(movingIntoObject){
                        checkObjectAndTakeAction(direction);
                    }
                    else{
                        moveRight();
                    }
                }
            }
            else{
                System.out.println("unrecognized");
            }
            gamePanel.repaint();
        }
        else{
            System.out.println("Je kan niet meer bewegen");
        }
    }
    
    public void moveUp(){
        System.out.println("up");
        int currentY = this.getYCoord();
        int newY = --currentY;
        this.setYCoord(newY);
    }
    
    public void moveDown(){
        System.out.println("down");
        int currentY = this.getYCoord();
        int newY = ++currentY;
        this.setYCoord(newY);
    }
    
    public void moveLeft(){
        System.out.println("left");
        int currentX = this.getXCoord();
        int newX = --currentX;
        this.setXCoord(newX);
    }
    
    public void moveRight(){
        System.out.println("right");
        int currentX = this.getXCoord();
        int newX = ++currentX;
        this.setXCoord(newX);
    }
    
    public void movingIntoObject(String direction){
        allEntities = gamePanel.getEntities();
        
        for(Entity ent : allEntities){
            int playerX = this.getXCoord();
            int playerY = this.getYCoord();
            int entityX = ent.getXCoord();
            int entityY = ent.getYCoord();

            switch(direction){
                case "up":
                    if(entityX == playerX && entityY == playerY - 1){
                        movingIntoObject = true;
                        classString = ent.getClassString();
                        entityInTheWay = gamePanel.getEntity(entityX, entityY);
                    }
                    break;
                case "down":
                    if(ent.getXCoord() == this.getXCoord() && ent.getYCoord() == this.getYCoord() + 1){
                        movingIntoObject = true;
                        classString = ent.getClassString();
                        entityInTheWay = gamePanel.getEntity(entityX, entityY);
                    }
                    break;
                case "left":
                    if(ent.getXCoord() == this.getXCoord() - 1 && ent.getYCoord() == this.getYCoord()){
                        movingIntoObject = true;
                        classString = ent.getClassString();
                        entityInTheWay = gamePanel.getEntity(entityX, entityY);
                    }
                    break;
                case "right":
                    if(ent.getXCoord() == this.getXCoord() + 1 && ent.getYCoord() == this.getYCoord()){
                        movingIntoObject = true;
                        classString = ent.getClassString();
                        entityInTheWay = gamePanel.getEntity(entityX, entityY);
                    }
                    break;
            }
        }
    }
        
    public void checkObjectAndTakeAction(String direction){
        if(classString.equals("Barricade")){
            System.out.println("Hier staat al een object");
        }
        else if(classString.equals("Key")){
            this.setKey((Key) entityInTheWay);
            
            allEntities.remove(entityInTheWay);
            
            moveToDirection(direction);
        }
        else if(classString.equals("Box")){
            int boxCode = entityInTheWay.getCode();

            int playerCode = this.getCode();

            if(boxCode == playerCode){
                allEntities.remove(entityInTheWay);

                moveToDirection(direction);
            }
            else{
                System.out.println("Hier staat al een object");
            }

        }
        else{
            allEntities.remove(entityInTheWay);
            moveToDirection(direction);
            System.out.println("Eindveld bereikt!");
            playerCanMove = false;
            
        }
    }

    public void moveToDirection(String direction){
        switch(direction){
                case "up":
                    moveUp();
                    break;
                case "down":
                    moveDown();
                    break;
                case "right":
                    moveRight();
                    break;
                case "left":
                    moveLeft();
                    break;
            }
    }
    
    public void setPlayerCanMove(boolean trueOrFalse){
        playerCanMove = trueOrFalse;
    }   
}
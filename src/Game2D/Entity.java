/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Entity{
    private int code;
    private int x, y;
    private String imgLocation;
    
    public Entity(int x, int y, String imgLocation){
        this.x = x;
        this.y = y;
        this.imgLocation = imgLocation;
    }
    
    public int getXCoord(){
        return x;
    }
    
    public int getYCoord(){
        return y;
    }
    
    public void setXCoord(int x){
        this.x = x;
    }
    
    public void setYCoord(int y){
        this.y = y;
    }
    
//    public BufferedImage getImg(){
//        BufferedImage inputImage = null;
//        BufferedImage outputImage = null;
//        try{
//            inputImage = ImageIO.read(new File(imgLocation));
//            outputImage = new BufferedImage(45, 45, inputImage.getType());
//            Graphics2D g2d = outputImage.createGraphics();
//            g2d.drawImage(inputImage, 0, 0, 45, 45, null);
//            g2d.dispose();
//        }
//        catch(IOException e){
//            System.out.println("Image did not load correctly");
//        }
//        return outputImage;
//    }
    
    public BufferedImage getImg(){
        BufferedImage inputImage = null;
        BufferedImage outputImage = null;
        InputStream inputStream = Entity.class.getResourceAsStream("../" + imgLocation);
        try{
            inputImage = ImageIO.read(inputStream);
            outputImage = new BufferedImage(45, 45, inputImage.getType());
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, 45, 45, null);
            g2d.dispose();
        }
        catch(IOException e){
            System.out.println("Image did not load correctly");
        }
        return outputImage;
        
    }
    
    public String getClassString(){
        return "Entity";
    }
    
    public int getCode(){
        return this.code;
    }
    
    public String getCodeString(){
        return "";
    }
}

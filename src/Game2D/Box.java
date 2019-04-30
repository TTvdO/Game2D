/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

public class Box extends Entity{
    private int code;
    
    public Box(int x, int y, String imgLocation, int code) {
        super(x, y, imgLocation);
        this.code = code;
    }
    
    public String getClassString(){
        return "Box";
    }
    
    public int getCode(){
        return this.code;
    }
    
    public String getCodeString(){
        return Integer.toString(this.code);
    }
    
}

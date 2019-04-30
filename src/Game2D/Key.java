/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

public class Key extends Entity{
    private int code;
    
    public Key(int x, int y, String imgLocation, int code){
        super(x, y, imgLocation);
        this.code = code;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public String getCodeString(){
        return Integer.toString(this.code);
    }
    
    public String getClassString(){
        return "Key";
    }
}

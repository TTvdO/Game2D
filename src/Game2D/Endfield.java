/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

public class Endfield extends Entity{
    
    public Endfield(int x, int y, String imgLocation){
        super(x, y, imgLocation);
    }
    
    public String getClassString(){
        return "Endfield";
    }
}

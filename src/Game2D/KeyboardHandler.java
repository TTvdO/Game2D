/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener{
    Player player;
    
    KeyboardHandler(Player player){
        this.player = player;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        player.move(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}

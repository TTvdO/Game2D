/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MouseButtonHandler implements ActionListener{
    Setup setup;
    GamePanel gamePanel;
    
    public MouseButtonHandler(Setup setup, GamePanel gamePanel){
        this.setup = setup;
        this.gamePanel = gamePanel;
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == setup.getNewGame()){
            gamePanel.newGame();
        }
                
        if(e.getSource() == setup.getRestartGame()){
            gamePanel.restartGame();
        }
    }
}

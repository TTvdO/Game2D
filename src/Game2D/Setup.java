/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2D;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Setup {
    private JFrame jFrame;
    private JButton restartGame;
    private JButton newGame;
    private MouseButtonHandler mouseButtonHandler;
    
    public Setup(){
        SetupJFrame("Game", 500, 600);
        
        GamePanel mainPanel = new GamePanel(10, 10, 20, 10);
        
        mainPanel.setLayout(null);
        
        mouseButtonHandler = new MouseButtonHandler(this, mainPanel);
        
        restartGame = new JButton("Restart");
        restartGame.setBounds(50, 500, 150, 50);
        restartGame.setFocusable(false);
        restartGame.addActionListener(mouseButtonHandler);
        
        newGame = new JButton("New game");
        newGame.setBounds(250, 500, 150, 50);
        newGame.setFocusable(false);
        newGame.addActionListener(mouseButtonHandler);
        
        mainPanel.add(restartGame, BorderLayout.NORTH);
        mainPanel.add(newGame, BorderLayout.SOUTH);
        
        Player player = mainPanel.getPlayer();
        KeyboardHandler keyHandler = new KeyboardHandler(player);
        mainPanel.addKeyListener(keyHandler);
        
        mainPanel.setFocusable(true);
        jFrame.add(mainPanel);
        
        jFrame.setVisible(true);
    }
    
    public void SetupJFrame(String name, int xSize, int ySize){
        jFrame = new JFrame(name);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setSize(xSize, ySize);
        jFrame.setResizable(false);
    }
    
    public JButton getRestartGame(){
        return this.restartGame;
    }
    
    public JButton getNewGame(){
        return this.newGame;
    }
}

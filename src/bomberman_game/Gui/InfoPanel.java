/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Gui;

import bomberman_game.Game;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Huong
 */
public class InfoPanel extends JPanel {
    
	private JLabel timeLabel;
	private JLabel pointsLabel;
	private JLabel heartLabel;
	private JLabel amountEnemy;
        
	public InfoPanel(Game game) {
		setLayout(new GridLayout(1, 4));
		//setLayout(new BorderLayout());
		timeLabel = new JLabel("Time: " + game.getBoard().getTime());
		timeLabel.setForeground(Color.white);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		pointsLabel = new JLabel("Points: " + game.getBoard().getPoints());
		pointsLabel.setForeground(Color.white);
		pointsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		heartLabel = new JLabel("Heart: " + game.getBomberHeart());
		heartLabel.setForeground(Color.white);
		heartLabel.setHorizontalAlignment(JLabel.CENTER);
                
		amountEnemy = new JLabel("Enemy: " + game.getBoard().getAmountEnemy());
		amountEnemy.setForeground(Color.white);
		amountEnemy.setHorizontalAlignment(JLabel.CENTER);
                
		add(timeLabel);
		add(pointsLabel);
		add(heartLabel); 
		add(amountEnemy);
		setBackground(Color.black);
		setPreferredSize(new Dimension(0, 40));
		setVisible(true);
	}
	
	public void setTime(int t) {
		timeLabel.setText("Time: " + t);
	}

	public void setPoints(int t) {
		pointsLabel.setText("Score: " + t);
	}

	public void setAmountEnemy(int t){
		amountEnemy.setText("Enemy: " + t);
	}
	
	public void setHearts(int t){
                heartLabel.setText("Heart: " + t);
        }

}

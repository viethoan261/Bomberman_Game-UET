package bomberman_game.Gui;

import bomberman_game.Sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start extends JFrame implements ActionListener {

    private JLabel jLabel1 = new JLabel();
    private JButton startPlay;
    private Frame frame;
    // End of variables declaration

    public Start() {
        //initComponents();


        startPlay = new JButton();
        startPlay.getActionListeners();
        //helpButton.getActionListeners();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(640, 640));
        this.getContentPane().setLayout(null);

        startPlay.setBackground(new Color(95, 117, 0));
        startPlay.setIcon(new ImageIcon("C:\\Users\\Huong\\IdeaProjects\\Bomberman_Game\\res\\Button\\StartButton.png")); // NOI18N
        startPlay.setToolTipText("");
        startPlay.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                startPlayMouseClicked(evt);
            }
        });

        this.getContentPane().add(startPlay);
        startPlay.setBounds(170, 400, 260, 60);

        jLabel1.setBackground(new Color(95, 117, 0));
        jLabel1.setIcon(new ImageIcon("C:\\Users\\Huong\\IdeaProjects\\Bomberman_Game\\res\\Button\\Button\\menu.png")); // NOI18N
        this.getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 640, 620);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void startPlayMouseClicked(MouseEvent evt) {
        // TODO add your handling code here:
        //new Frame().setVisible(true);
        //this.dispose();
        Sound.playBackGround();
        new Frame().setVisible(true);
        this.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

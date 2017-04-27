import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

public class SnakeApp implements Runnable {
    public static void main(String[] args) {
        SnakeApp snakeApp = new SnakeApp();
        SwingUtilities.invokeLater(snakeApp);
    }

    final int width = 500;
	final int height = 500;
    
    public void run() {
        
    	// 初始化grid
		Grid grid = new Grid(this.width, this.height);
        JFrame window = new JFrame("Rui Guo自制小游戏");
        Container contentPane = window.getContentPane();
        // 基于Grid初始化gamaView
        GameView gameView = new GameView(grid);
        gameView.init();
        
        // 设置gameView中JPanel的大小
        gameView.getCanvas().setPreferredSize(new Dimension(this.width, this.height));
        
        // 将gameView中JPanel加入到窗口中
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
        GameController gameController = new GameController(grid, gameView);

        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
		
		// 启动线程
		new Thread(gameController).start();
		window.addKeyListener(gameController);
    }
}

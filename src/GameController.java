import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
public class GameController implements Runnable, java.awt.event.KeyListener {
	private final GameView gameView;
	private final Grid grid;
	private boolean running;
	
	public GameController(Grid grid, GameView gameView) {
		this.gameView = gameView;
		this.grid = grid;
		this.running = true;
	}
	
	@Override
	public void run() {	
        while (running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }
            // 进入游戏下一步
            running = grid.nextRound();
            // 如果结束，则退出游戏
            if (running == false) {
            	showGameOverMessage();
            } else {
            	// 如果继续，则绘制新的游戏页面
                gameView.draw();
            }
        }
    }
	
	public void showGameOverMessage() {
	    JOptionPane.showMessageDialog(null, "Game Over", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
    	// 这里处理按键
    	int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
			gameView.getGrid().changeDirection(Direction.UP);
			grid.nextRound();
        } else if (keyCode == KeyEvent.VK_DOWN) {
			gameView.getGrid().changeDirection(Direction.DOWN);
			grid.nextRound();
        } else if (keyCode == KeyEvent.VK_LEFT) {
			gameView.getGrid().changeDirection(Direction.LEFT);
			grid.nextRound();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
			gameView.getGrid().changeDirection(Direction.RIGHT);
			grid.nextRound();
        }
        
        // repaint the canvas
        if (running) {
        	gameView.draw();
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
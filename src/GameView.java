import java.awt.*;
import java.util.LinkedList;
import javax.swing.JPanel;

public class GameView {

    private JPanel canvas;
    private final Grid grid;

    public void init() {
        canvas = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
            public void paintComponent(Graphics graphics) {
                drawGridBackground(graphics);
                drawSnake(graphics, grid.getSnake());
                drawFood(graphics, grid.getFood());
            }
        };
    }

    public GameView(Grid grid) {
        this.grid = grid;
    }

    public void drawSnake(Graphics graphics, Snake snake) {
    	LinkedList<Node> body = snake.getBody();
    	for (int i=0; i<body.size(); i++) {
    		drawSquare(graphics, body.get(i), Color.BLUE);
    	}
    }

    public void drawFood(Graphics graphics, Node squareArea) {
    	drawCircle(graphics, squareArea, Color.GREEN);
    }

    public void drawGridBackground(Graphics graphics) {
    	graphics.fillRect(0, 0, grid.getWidth(), grid.getHeight());
    }
    
    private void drawSquare(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = 10; //Settings.DEFAULT_NODE_SIZE;
        graphics.fillRect(squareArea.getX() * size, squareArea.getY() * size, size - 1, size - 1);
    }

    private void drawCircle(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = 10; //Settings.DEFAULT_NODE_SIZE;
        graphics.fillOval(squareArea.getX() * size, squareArea.getY() * size, size, size);
    }
//    
    public void draw() {
        canvas.repaint();
    }
    
    public JPanel getCanvas() {
        return canvas;
    }
    
    public Grid getGrid() {
    	return this.grid;
    }


}
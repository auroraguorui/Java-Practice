import java.util.Random;

public class Grid {
	public final boolean status[][];
	private final int width;
    private final int height;
    private final int size = 10;

    private Snake snake;
    private Node food;
    private Direction snakeDirection = Direction.LEFT;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        status = new boolean[width][height];
        initSnake();
        createFood();
    }
    
    private Snake initSnake() {
    	snake = new Snake();
    	// 设置Snake的Body
    	snake.addHead(new Node(this.getWidth()/2/size, this.getHeight()/2/size));
    	snake.addHead(new Node((this.getWidth()/2/size-1), this.getHeight()/2/size));
    	snake.addTail(new Node((this.getWidth()/2/size+1), this.getHeight()/2/size));

    	// 更新棋盘覆盖状态
    	this.status[this.getWidth()/2][this.getHeight()/2]   = true;
    	this.status[this.getWidth()/2-1][this.getHeight()/2] = true;
    	this.status[this.getWidth()/2+1][this.getHeight()/2] = true;
    	return snake;
    	}
    
    public Snake getSnake() {
    	return snake;
    }
    
    public Node createFood() {
    	int x, y;
    	// 使用Random设置x和y
    	do {
    		Random rand1 = new Random();
    		Random rand2 = new Random();
    		x = rand1.nextInt(this.getWidth()/size);
    		y = rand2.nextInt(this.getHeight()/size);
    	} while (this.status[x][y] == true);
    		
    	food = new Node(x, y);
    	this.status[x][y] = true;
    	return food;
    	}
    
    public Node getFood() {
    	return food;
    }
    
    public boolean nextRound() {
    	// 按当前方向移动贪吃蛇
    	Node oldtail = snake.move(snakeDirection);
    	Node newhead = snake.getHead();
        if (isValidPosition(newhead)) {
        	if (newhead.getX()==food.getX() && newhead.getY()==food.getY()) {
                snake.addTail(oldtail);
                createFood();
                this.status[food.getX()][food.getY()] = true;
            } else {
            	this.status[oldtail.getX()][oldtail.getY()] = false;
            }
    		// 更新棋盘状态并返回游戏是否结束的标志
            this.status[newhead.getX()][newhead.getY()] = true;
            return true;
    	} else {
    		return false;
    	}
    }
    
    private boolean isValidPosition(Node node) {
    	if (node.getX() < 0 || node.getX() >= getWidth() || node.getY() < 0 || node.getY() >= getHeight()) {
    		return false;
    	}
    	return true;
    }
    
    public void changeDirection(Direction newDirection) {
        if (snakeDirection.compatibleWith(newDirection)) {
            snakeDirection = newDirection;
        } 
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

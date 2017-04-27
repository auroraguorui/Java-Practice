public enum Direction {
	UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    // 成员变量
    private final int directionCode;

    // 成员方法
    public int directionCode() {
        return directionCode;
    }

    // 构造函数
    Direction(int directionCode) {
        this.directionCode = directionCode;
    }
    
    public boolean compatibleWith(Direction newDirection) {
    	if (this == UP && newDirection == DOWN) {
    		return false;
    	} else if (this == DOWN && newDirection == UP) {
    		return false;
    	} else if (this == LEFT && newDirection == RIGHT) {
    		return false;
    	} else if (this == RIGHT && newDirection == LEFT) {
    		return false;
    	} else {
    		return true;
    	}
    }
}

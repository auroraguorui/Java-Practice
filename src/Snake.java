import java.util.LinkedList;

public class Snake {

    private LinkedList<Node> body = new LinkedList<>();
    
    public Node eat(Node food) {
        // 如果food与头部相邻，则将food这个Node加入到body中，返回food
        // 否则不做任何操作，返回null
    	if (isNeighbor(body.getFirst(), food)) {
    		System.out.println("should eat food");
    		body.addFirst(food);
    		return food;
    	} else {
    		return null;
    	}
    }
    
    public Node move(Direction direction) {
        Node currhead = this.getHead();
        Node currtail = this.getTail();
        int hx = currhead.getX();
        int hy = currhead.getY();
    	// 根据方向更新贪吃蛇的body
        // 增加头
        if (direction == Direction.LEFT) {
        	this.addHead(new Node(hx-1, hy));
        } else if (direction == Direction.RIGHT) {
        	this.addHead(new Node(hx+1, hy));
        } else if (direction == Direction.UP) {
        	this.addHead(new Node(hx, hy-1));
        } else if (direction == Direction.DOWN) {
        	this.addHead(new Node(hx, hy+1));
        }
        // 删除尾
        removeTail();
        // 返回移动之前的尾部Node
        return currtail;
    }

    public Node getHead() {
        return body.getFirst();
    }
    
    public Node getTail() {
    	return body.getLast();
    }
    
    public Node addHead(Node area) {
    	this.body.addFirst(area);
    	return area;
    }

    public Node addTail(Node area) {
        this.body.addLast(area);
        return area;
    }
    
    public void removeTail() {
    	this.body.removeLast();
    }

    public LinkedList<Node> getBody() {
        return body;
    }
    
    private boolean isNeighbor(Node a, Node b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) == 1;
    }
}
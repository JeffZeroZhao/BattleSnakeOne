import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.battlesnake.starter.data.Location;
import com.battlesnake.starter.data.Move;
import com.battlesnake.starter.data.SnakeInfo;

public class Test {

	public static void main(String[] args) {
		System.out.println("Jeff");
		Move a = new Move();
		a.setLocation(new Location(2, 0));
		SnakeInfo mySnake = new SnakeInfo();
		mySnake.setHead(new Location(2, 0));
		mySnake.setHeading("down");
		mySnake.setHealth(100);
		mySnake.setLength(7);
		List<Location> snakeBody = new ArrayList<>();
		snakeBody.add(new Location(2, 0));
		snakeBody.add(new Location(2, 1));
		snakeBody.add(new Location(2, 2));
		snakeBody.add(new Location(3, 2));
		snakeBody.add(new Location(4, 2));
		snakeBody.add(new Location(4, 1));
		snakeBody.add(new Location(4, 0));
		snakeBody.add(new Location(5, 0));
		mySnake.setSnakeBody(snakeBody);
		Set<Location> store = new TreeSet<>();
		System.out.println(
				a.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(1, 0), new Location(1, 0), store));
		System.out.println(store);
		store = new TreeSet<>();
		System.out.println(
				a.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(3, 0), new Location(3, 0), store));
		System.out.println(store);
	}

}

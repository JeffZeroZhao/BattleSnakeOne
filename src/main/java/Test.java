import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.battlesnake.starter.data.FoodContainer;
import com.battlesnake.starter.data.Location;
import com.battlesnake.starter.data.Move;
import com.battlesnake.starter.data.SnakeInfo;
import com.battlesnake.starter.snake.SuriveSnake;

public class Test {

	public static void main(String[] args) {
		System.out.println("Jeff");
		Move a = new Move();
		a.setLocation(new Location(2, 0));
		SnakeInfo mySnake = new SnakeInfo();
		mySnake.setHead(new Location(2, 0));
		mySnake.setHeading("down");
		mySnake.setHealth(100);
		mySnake.setLength(8);
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
				Move.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(1, 0), new Location(1, 0), store));
		System.out.println(store);
		store = new TreeSet<>();
		System.out.println(
				Move.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(3, 0), new Location(3, 0), store));
		System.out.println(store);

		mySnake = new SnakeInfo();
		mySnake.setHead(new Location(1, 0));
		mySnake.setHeading("down");
		mySnake.setHealth(100);
		mySnake.setLength(9);
		snakeBody = new ArrayList<>();
		snakeBody.add(new Location(1, 0));
		snakeBody.add(new Location(2, 0));
		snakeBody.add(new Location(2, 1));
		snakeBody.add(new Location(2, 2));
		snakeBody.add(new Location(3, 2));
		snakeBody.add(new Location(4, 2));
		snakeBody.add(new Location(4, 1));
		snakeBody.add(new Location(4, 0));
		snakeBody.add(new Location(5, 0));
		mySnake.setSnakeBody(snakeBody);
		SuriveSnake sn = new SuriveSnake();
		FoodContainer fc = new FoodContainer();
		fc.setFoodList(new ArrayList<Location>());
		fc.getFoodList().add(new Location(0, 0));
		fc.getFoodList().add(new Location(2, 4));
		Set<Location> connectingDots = new TreeSet<>();
		Move.connectingDots(6, 6, null, mySnake, mySnake.getSnakeBody().get(1), mySnake.getSnakeBody().get(0), mySnake.getSnakeBody().get(0), connectingDots);
		System.out.println(connectingDots);
		Location food = sn.findTheCloestFood(fc, mySnake, connectingDots);
		System.out.println("food: " + food);
		Move move = sn.getMove(6, 6, null, mySnake, fc);
		System.out.println("move: " + move.getMove());
		
		
		Location l = new Location(5,5);
		System.out.println("isEmpty: " + l.isEmpty(6, 6, null, mySnake, true));
	}

}

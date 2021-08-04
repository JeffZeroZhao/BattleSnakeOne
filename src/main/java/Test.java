import java.io.IOException;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) throws IOException {
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
				Move.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(1, 0), new Location(1, 0), store, true));
		System.out.println(store);
		store = new TreeSet<>();
		System.out.println(
				Move.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(3, 0), new Location(3, 0), store, true));
		System.out.println(store);

		mySnake = new SnakeInfo();
		snakeBody = new ArrayList<>();
		snakeBody.add(new Location(6, 9));
		snakeBody.add(new Location(6, 8));
		snakeBody.add(new Location(6, 7));
		snakeBody.add(new Location(6, 6));
		snakeBody.add(new Location(6, 5));
		snakeBody.add(new Location(6, 4));
		snakeBody.add(new Location(6, 3));
		snakeBody.add(new Location(6, 2));
		snakeBody.add(new Location(6, 1));
		snakeBody.add(new Location(6, 0));
		snakeBody.add(new Location(5, 0));
		snakeBody.add(new Location(4, 0));
		snakeBody.add(new Location(3, 0));
		snakeBody.add(new Location(2, 0));
		snakeBody.add(new Location(1, 0));
		snakeBody.add(new Location(0, 0));
		snakeBody.add(new Location(0, 1));
		snakeBody.add(new Location(0, 2));
		snakeBody.add(new Location(0, 3));
		snakeBody.add(new Location(0, 4));
		snakeBody.add(new Location(0, 5));
		snakeBody.add(new Location(0, 6));
		snakeBody.add(new Location(0, 7));
		snakeBody.add(new Location(0, 8));
		snakeBody.add(new Location(0, 9));
		snakeBody.add(new Location(0, 10));
		snakeBody.add(new Location(1, 10));
		snakeBody.add(new Location(1, 9));
		snakeBody.add(new Location(2, 9));
		snakeBody.add(new Location(2, 8));
		snakeBody.add(new Location(2, 7));
		snakeBody.add(new Location(2, 6));
		snakeBody.add(new Location(2, 5));
		snakeBody.add(new Location(2, 4));
		snakeBody.add(new Location(2, 3));
		snakeBody.add(new Location(2, 2));
		snakeBody.add(new Location(2, 1));
		snakeBody.add(new Location(3, 1));
		snakeBody.add(new Location(3, 2));
		snakeBody.add(new Location(3, 3));
		snakeBody.add(new Location(3, 4));
		snakeBody.add(new Location(3, 5));
		snakeBody.add(new Location(3, 6));
		snakeBody.add(new Location(3, 7));
		snakeBody.add(new Location(3, 8));
		snakeBody.add(new Location(3, 9));
		snakeBody.add(new Location(4, 9));
		snakeBody.add(new Location(4, 8));
		snakeBody.add(new Location(4, 7));
		snakeBody.add(new Location(4, 6));
		mySnake.setSnakeBody(snakeBody);
		mySnake.setHead(new Location(snakeBody.get(0).getX(), snakeBody.get(0).getY()));
//		mySnake.setHeading("up");
		mySnake.setHealth(100);
		mySnake.setLength(snakeBody.size());
		SuriveSnake sn = new SuriveSnake();
		FoodContainer fc = new FoodContainer();
		fc.setFoodList(new ArrayList<Location>());
		fc.getFoodList().add(new Location(0, 0));
		fc.getFoodList().add(new Location(2, 4));
//		Set<Location> connectingDots = new TreeSet<>();
//		Move.connectingDots(6, 6, null, mySnake, mySnake.getSnakeBody().get(1), mySnake.getSnakeBody().get(0), mySnake.getSnakeBody().get(0), connectingDots, true);
//		System.out.println(connectingDots);
//		Location food = sn.findTheCloestFood(fc, mySnake, connectingDots);
//		System.out.println("food: " + food);
		Move move = sn.getMove(11, 11, null, mySnake, fc, true);
		System.out.println("move: " + move.getMove());
		
		
//		Location l = new Location(5,5);
//		System.out.println("isEmpty: " + l.isEmpty(6, 6, null, mySnake, true));
//
//		sn = new SuriveSnake();
//	    ObjectMapper mapper = new ObjectMapper();
//	    JsonNode actualObj = mapper.readTree("{\"game\":{\"id\":\"ad5cb0e9-5cf8-4c36-a31f-9ca803dba79f\",\"ruleset\":{\"name\":\"standard\",\"version\":\"v1.0.17\"},\"timeout\":500},\"turn\":23,\"board\":{\"height\":11,\"width\":11,\"snakes\":[],\"food\":[{\"x\":0,\"y\":1},{\"x\":3,\"y\":0},{\"x\":3,\"y\":2},{\"x\":3,\"y\":5},{\"x\":5,\"y\":4}],\"hazards\":[]},\"you\":{\"id\":\"gs_RPhyPHhbMHt7QqMQYHy3ght3\",\"name\":\"BattleSnakeSurive\",\"latency\":\"260\",\"health\":99,\"body\":[{\"x\":10,\"y\":1},{\"x\":10,\"y\":2},{\"x\":10,\"y\":3},{\"x\":9,\"y\":3},{\"x\":9,\"y\":4},{\"x\":9,\"y\":5},{\"x\":9,\"y\":6},{\"x\":9,\"y\":7},{\"x\":8,\"y\":7},{\"x\":7,\"y\":7},{\"x\":7,\"y\":8},{\"x\":6,\"y\":8},{\"x\":6,\"y\":9},{\"x\":5,\"y\":9},{\"x\":4,\"y\":9},{\"x\":3,\"y\":9},{\"x\":3,\"y\":10},{\"x\":2,\"y\":10},{\"x\":2,\"y\":9},{\"x\":2,\"y\":8},{\"x\":2,\"y\":7},{\"x\":1,\"y\":7},{\"x\":1,\"y\":8},{\"x\":1,\"y\":9},{\"x\":1,\"y\":10},{\"x\":0,\"y\":10},{\"x\":0,\"y\":9},{\"x\":0,\"y\":8},{\"x\":0,\"y\":7},{\"x\":0,\"y\":6},{\"x\":0,\"y\":5},{\"x\":0,\"y\":4},{\"x\":0,\"y\":3},{\"x\":0,\"y\":2},{\"x\":1,\"y\":2},{\"x\":2,\"y\":2},{\"x\":2,\"y\":3},{\"x\":2,\"y\":4}],\"head\":{\"x\":10,\"y\":1},\"length\":38,\"shout\":\"\"}}");
//		sn.move(actualObj);
//		System.out.println("move: " + sn.move(actualObj));
		
	}

}

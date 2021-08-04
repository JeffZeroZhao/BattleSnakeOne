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
		Move move = sn.getMove(6, 6, null, mySnake, fc, true);
		System.out.println("move: " + move.getMove());
		
		
		Location l = new Location(5,5);
		System.out.println("isEmpty: " + l.isEmpty(6, 6, null, mySnake, true));

		sn = new SuriveSnake();
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree("{\"game\":{\"id\":\"a15aaca0-3740-4d06-a2be-7dfc9ebfb0b9\",\"ruleset\":{\"name\":\"standard\",\"version\":\"v1.0.17\"},\"timeout\":500},\"turn\":128,\"board\":{\"height\":11,\"width\":11,\"snakes\":[{\"id\":\"gs_kD3mS8BtGH9V9RXrMRyyggv6\",\"name\":\"snake30series\",\"latency\":\"108\",\"health\":87,\"body\":[{\"x\":5,\"y\":5},{\"x\":5,\"y\":4},{\"x\":5,\"y\":3},{\"x\":6,\"y\":3},{\"x\":7,\"y\":3},{\"x\":8,\"y\":3},{\"x\":9,\"y\":3},{\"x\":10,\"y\":3},{\"x\":10,\"y\":4},{\"x\":10,\"y\":5},{\"x\":10,\"y\":6},{\"x\":10,\"y\":7},{\"x\":10,\"y\":8},{\"x\":10,\"y\":9},{\"x\":9,\"y\":9},{\"x\":8,\"y\":9},{\"x\":7,\"y\":9}],\"head\":{\"x\":5,\"y\":5},\"length\":17,\"shout\":\"\"},{\"id\":\"gs_TDQbphxjJmc3BVkfRSGMySqc\",\"name\":\"BattleSnakeSurive\",\"latency\":\"220\",\"health\":64,\"body\":[{\"x\":4,\"y\":10},{\"x\":4,\"y\":9},{\"x\":4,\"y\":8},{\"x\":4,\"y\":7},{\"x\":4,\"y\":6},{\"x\":4,\"y\":5},{\"x\":4,\"y\":4},{\"x\":4,\"y\":3},{\"x\":4,\"y\":2},{\"x\":4,\"y\":1}],\"head\":{\"x\":4,\"y\":10},\"length\":10,\"shout\":\"\"}],\"food\":[{\"x\":2,\"y\":3}],\"hazards\":[]},\"you\":{\"id\":\"gs_TDQbphxjJmc3BVkfRSGMySqc\",\"name\":\"BattleSnakeSurive\",\"latency\":\"220\",\"health\":64,\"body\":[{\"x\":4,\"y\":10},{\"x\":4,\"y\":9},{\"x\":4,\"y\":8},{\"x\":4,\"y\":7},{\"x\":4,\"y\":6},{\"x\":4,\"y\":5},{\"x\":4,\"y\":4},{\"x\":4,\"y\":3},{\"x\":4,\"y\":2},{\"x\":4,\"y\":1}],\"head\":{\"x\":4,\"y\":10},\"length\":10,\"shout\":\"\"}}");
		sn.move(actualObj);
		System.out.println("move: " + sn.move(actualObj));
		
	}

}

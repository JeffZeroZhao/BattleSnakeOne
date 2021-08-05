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
//		System.out.println("Jeff");
//		Move a = new Move();
//		a.setLocation(new Location(2, 0));
//		SnakeInfo mySnake = new SnakeInfo();
//		mySnake.setHead(new Location(2, 0));
//		mySnake.setHeading("down");
//		mySnake.setHealth(100);
//		mySnake.setLength(8);
//		List<Location> snakeBody = new ArrayList<>();
//		snakeBody.add(new Location(2, 0));
//		snakeBody.add(new Location(2, 1));
//		snakeBody.add(new Location(2, 2));
//		snakeBody.add(new Location(3, 2));
//		snakeBody.add(new Location(4, 2));
//		snakeBody.add(new Location(4, 1));
//		snakeBody.add(new Location(4, 0));
//		snakeBody.add(new Location(5, 0));
//		mySnake.setSnakeBody(snakeBody);
//		Set<Location> store = new TreeSet<>();
//		System.out.println(
//				Move.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(1, 0), new Location(1, 0), store, true));
//		System.out.println(store);
//		store = new TreeSet<>();
//		System.out.println(
//				Move.connectingDots(6, 6, null, mySnake, mySnake.getHead(), new Location(3, 0), new Location(3, 0), store, true));
//		System.out.println(store);
//
//		mySnake = new SnakeInfo();
//		snakeBody = new ArrayList<>();
//		snakeBody.add(new Location(5, 1));
//		snakeBody.add(new Location(5, 2));
//		snakeBody.add(new Location(5, 3));
//		snakeBody.add(new Location(5, 4));
//		snakeBody.add(new Location(6, 4));
//		snakeBody.add(new Location(7, 4));
//		snakeBody.add(new Location(8, 4));
//		snakeBody.add(new Location(9, 4));
//		snakeBody.add(new Location(9, 3));
//		snakeBody.add(new Location(9, 2));
//		mySnake.setSnakeBody(snakeBody);
//		mySnake.setHead(new Location(snakeBody.get(0).getX(), snakeBody.get(0).getY()));
////		mySnake.setHeading("up");
//		mySnake.setHealth(100);
//		mySnake.setLength(snakeBody.size());
//		
//
//		SnakeInfo otherSnake = new SnakeInfo();
//		snakeBody = new ArrayList<>();
//		snakeBody.add(new Location(4, 8));
//		snakeBody.add(new Location(3, 8));
//		snakeBody.add(new Location(3, 7));
//		snakeBody.add(new Location(4, 7));
//		snakeBody.add(new Location(4, 6));
//		snakeBody.add(new Location(4, 5));
//		snakeBody.add(new Location(4, 4));
//		snakeBody.add(new Location(4, 3));
//		snakeBody.add(new Location(5, 3));
//		snakeBody.add(new Location(5, 4));
//		snakeBody.add(new Location(6, 4));
//		snakeBody.add(new Location(7, 4));
//		snakeBody.add(new Location(7, 3));
//		snakeBody.add(new Location(8, 3));
//		snakeBody.add(new Location(8, 4));
//		snakeBody.add(new Location(8, 5));
//		snakeBody.add(new Location(8, 6));
//		snakeBody.add(new Location(9, 6));
//		snakeBody.add(new Location(10, 6));
//		snakeBody.add(new Location(10, 5));
//		snakeBody.add(new Location(10, 4));
//		snakeBody.add(new Location(10, 3));
//		snakeBody.add(new Location(10, 2));
//		snakeBody.add(new Location(9, 2));
//		otherSnake.setSnakeBody(snakeBody);
//		otherSnake.setHead(new Location(snakeBody.get(0).getX(), snakeBody.get(0).getY()));
////		mySnake.setHeading("up");
//		otherSnake.setHealth(100);
//		otherSnake.setLength(snakeBody.size());
//		List<SnakeInfo> otherSnakes = new ArrayList<>();
////		otherSnakes.add(otherSnake);
//		SuriveSnake sn = new SuriveSnake();
//		FoodContainer fc = new FoodContainer();
//		fc.setFoodList(new ArrayList<Location>());
//		fc.getFoodList().add(new Location(3, 0));
////		Set<Location> connectingDots = new TreeSet<>();
////		Move.connectingDots(6, 6, null, mySnake, mySnake.getSnakeBody().get(1), mySnake.getSnakeBody().get(0), mySnake.getSnakeBody().get(0), connectingDots, true);
////		System.out.println(connectingDots);
////		Location food = sn.findTheCloestFood(fc, mySnake, connectingDots);
////		System.out.println("food: " + food);
//		Move move = sn.getMove(11, 11, null, mySnake, fc, true);
//		System.out.println("move: " + move.getMove());
		
		
//		Location l = new Location(5,5);
//		System.out.println("isEmpty: " + l.isEmpty(6, 6, null, mySnake, true));
//
		SuriveSnake sn = new SuriveSnake();
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree("{\"game\":{\"id\":\"f3d7df62-06f3-4ff9-b26b-cafa28e7094a\",\"ruleset\":{\"name\":\"solo\",\"version\":\"v1.0.17\"},\"timeout\":500},\"turn\":351,\"board\":{\"height\":11,\"width\":11,\"snakes\":[{\"id\":\"gs_DkmFSm6ygdGVp8SGvyhgD37c\",\"name\":\"BattleSnakeSurive\",\"latency\":\"265\",\"health\":62,\"body\":[{\"x\":0,\"y\":1},{\"x\":0,\"y\":2},{\"x\":0,\"y\":3},{\"x\":0,\"y\":4},{\"x\":1,\"y\":4},{\"x\":1,\"y\":3},{\"x\":1,\"y\":2},{\"x\":1,\"y\":1},{\"x\":1,\"y\":0},{\"x\":2,\"y\":0},{\"x\":2,\"y\":1},{\"x\":2,\"y\":2},{\"x\":2,\"y\":3},{\"x\":2,\"y\":4},{\"x\":3,\"y\":4},{\"x\":3,\"y\":3},{\"x\":3,\"y\":2},{\"x\":3,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":0},{\"x\":5,\"y\":0},{\"x\":6,\"y\":0},{\"x\":7,\"y\":0},{\"x\":8,\"y\":0},{\"x\":9,\"y\":0},{\"x\":10,\"y\":0},{\"x\":10,\"y\":1},{\"x\":9,\"y\":1},{\"x\":8,\"y\":1},{\"x\":7,\"y\":1},{\"x\":6,\"y\":1},{\"x\":5,\"y\":1},{\"x\":5,\"y\":2},{\"x\":4,\"y\":2},{\"x\":4,\"y\":3},{\"x\":4,\"y\":4},{\"x\":4,\"y\":5},{\"x\":3,\"y\":5},{\"x\":2,\"y\":5},{\"x\":1,\"y\":5},{\"x\":0,\"y\":5},{\"x\":0,\"y\":6},{\"x\":0,\"y\":7},{\"x\":0,\"y\":8},{\"x\":0,\"y\":9},{\"x\":1,\"y\":9},{\"x\":1,\"y\":10},{\"x\":2,\"y\":10},{\"x\":2,\"y\":9},{\"x\":2,\"y\":8},{\"x\":2,\"y\":7},{\"x\":2,\"y\":6}],\"head\":{\"x\":0,\"y\":1},\"length\":52,\"shout\":\"\"}],\"food\":[{\"x\":10,\"y\":6},{\"x\":3,\"y\":10},{\"x\":10,\"y\":8},{\"x\":1,\"y\":6},{\"x\":10,\"y\":10},{\"x\":8,\"y\":9},{\"x\":5,\"y\":9},{\"x\":1,\"y\":7}],\"hazards\":[]},\"you\":{\"id\":\"gs_DkmFSm6ygdGVp8SGvyhgD37c\",\"name\":\"BattleSnakeSurive\",\"latency\":\"265\",\"health\":62,\"body\":[{\"x\":0,\"y\":1},{\"x\":0,\"y\":2},{\"x\":0,\"y\":3},{\"x\":0,\"y\":4},{\"x\":1,\"y\":4},{\"x\":1,\"y\":3},{\"x\":1,\"y\":2},{\"x\":1,\"y\":1},{\"x\":1,\"y\":0},{\"x\":2,\"y\":0},{\"x\":2,\"y\":1},{\"x\":2,\"y\":2},{\"x\":2,\"y\":3},{\"x\":2,\"y\":4},{\"x\":3,\"y\":4},{\"x\":3,\"y\":3},{\"x\":3,\"y\":2},{\"x\":3,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":0},{\"x\":5,\"y\":0},{\"x\":6,\"y\":0},{\"x\":7,\"y\":0},{\"x\":8,\"y\":0},{\"x\":9,\"y\":0},{\"x\":10,\"y\":0},{\"x\":10,\"y\":1},{\"x\":9,\"y\":1},{\"x\":8,\"y\":1},{\"x\":7,\"y\":1},{\"x\":6,\"y\":1},{\"x\":5,\"y\":1},{\"x\":5,\"y\":2},{\"x\":4,\"y\":2},{\"x\":4,\"y\":3},{\"x\":4,\"y\":4},{\"x\":4,\"y\":5},{\"x\":3,\"y\":5},{\"x\":2,\"y\":5},{\"x\":1,\"y\":5},{\"x\":0,\"y\":5},{\"x\":0,\"y\":6},{\"x\":0,\"y\":7},{\"x\":0,\"y\":8},{\"x\":0,\"y\":9},{\"x\":1,\"y\":9},{\"x\":1,\"y\":10},{\"x\":2,\"y\":10},{\"x\":2,\"y\":9},{\"x\":2,\"y\":8},{\"x\":2,\"y\":7},{\"x\":2,\"y\":6}],\"head\":{\"x\":0,\"y\":1},\"length\":52,\"shout\":\"\"}}");
		sn.move(actualObj);
		System.out.println("move: " + sn.move(actualObj));
		
	}

}

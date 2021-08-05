package com.battlesnake.starter.snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.battlesnake.starter.data.FoodContainer;
import com.battlesnake.starter.data.Location;
import com.battlesnake.starter.data.Move;
import com.battlesnake.starter.data.SnakeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SuriveSnake extends BattleSnaker {
	private static final Logger LOG = LoggerFactory.getLogger(SuriveSnake.class);
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	@Override
	public Map<String, String> move(JsonNode moveRequest) {
		FoodContainer foodContainer = new FoodContainer();
		SnakeInfo mySnakeInfo = new SnakeInfo();
		List<SnakeInfo> otherSnakes = new ArrayList<>();

		try {
			LOG.info("Data: {}", JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(moveRequest));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// process data
		if (!(moveRequest != null && moveRequest.get("board") != null && moveRequest.get("board").get("height") != null
				&& moveRequest.get("board").get("width") != null)) {
			Map<String, String> response = new HashMap<>();
			return response;
		}
		int boardHeight = moveRequest.get("board").get("height").asInt();
		int boardWidth = moveRequest.get("board").get("width").asInt();
		JsonNode food = moveRequest.get("board").get("food");
		foodContainer.processFood(food);
		LOG.info("Food " + foodContainer.toString());
		mySnakeInfo.processSnakeInfo(moveRequest.get("you"));
		LOG.info("mySnakeInfo" + mySnakeInfo.toString());
		JsonNode snakesArray = moveRequest.get("board").get("snakes");
		if (snakesArray != null) {
			for (int i = 0; i < snakesArray.size(); i++) {
				JsonNode snake = snakesArray.get(i);
				SnakeInfo s = new SnakeInfo();
				s.processSnakeInfo(snake);
				if (s.getId() != null && !s.getId().equalsIgnoreCase(mySnakeInfo.getId()))
					otherSnakes.add(s);
			}
		}

		Move move = getMove(boardHeight, boardWidth, otherSnakes, mySnakeInfo, foodContainer, (true && mySnakeInfo.getHealth() < 30));

		Map<String, String> response = new HashMap<>();
		if (move != null)
			response.put("move", move.getMove());
		return response;
//        "up", "down", "left", "right"
//		String move = inDanger(boardHeight, boardWidth, mySnakeInfo);
//		LOG.info("MOVE inDanger{}", move);
//		if (move == null) {
//			move = getFood(foodContainer, mySnakeInfo);
//			LOG.info("MOVE getFood{}", move);
//		}
//		if (move == null && mySnakeInfo.getHeading() != null) {
//			move = mySnakeInfo.getHeading();
//			LOG.info("MOVE continue{}", move);
//		}
//
//		LOG.info("MOVE {}", move);
//
//		Map<String, String> response = new HashMap<>();
//		response.put("move", move);
//		return response;
	}

	public Move getMove(int boardHeight, int boardWidth, List<SnakeInfo> otherSnakes, SnakeInfo mySnake,
			FoodContainer foodContainer, boolean useTopBottpmRule) {
		Location head = mySnake.getHead();
		Move moveUp = new Move();
		moveUp.setLocation(new Location(head.getX(), head.getY() + 1));
		moveUp.setMove("up");

		Move moveDown = new Move();
		moveDown.setLocation(new Location(head.getX(), head.getY() - 1));
		moveDown.setMove("down");

		Move moveRight = new Move();
		moveRight.setLocation(new Location(head.getX() + 1, head.getY()));
		moveRight.setMove("right");

		Move moveLeft = new Move();
		moveLeft.setLocation(new Location(head.getX() - 1, head.getY()));
		moveLeft.setMove("left");

		List<Move> validMove = new ArrayList<>();
		if (moveUp.getLocation().isEmpty(boardHeight, boardWidth, otherSnakes, mySnake, useTopBottpmRule, true)) {
			moveUp.setConnectedDots(Move.connectingDots(boardHeight, boardWidth, otherSnakes, mySnake, head,
					moveUp.getLocation(), moveUp.getLocation(), new HashSet<>(), useTopBottpmRule));
			validMove.add(moveUp);
		}
		if (moveDown.getLocation().isEmpty(boardHeight, boardWidth, otherSnakes, mySnake, useTopBottpmRule, true)) {
			moveDown.setConnectedDots(Move.connectingDots(boardHeight, boardWidth, otherSnakes, mySnake, head,
					moveDown.getLocation(), moveDown.getLocation(), new HashSet<>(), useTopBottpmRule));
			validMove.add(moveDown);
		}
		if (moveRight.getLocation().isEmpty(boardHeight, boardWidth, otherSnakes, mySnake, useTopBottpmRule, true)) {
			moveRight.setConnectedDots(Move.connectingDots(boardHeight, boardWidth, otherSnakes, mySnake, head,
					moveRight.getLocation(), moveRight.getLocation(), new HashSet<>(), useTopBottpmRule));
			validMove.add(moveRight);
		}
		if (moveLeft.getLocation().isEmpty(boardHeight, boardWidth, otherSnakes, mySnake, useTopBottpmRule, true)) {
//			Set<Location> store = new TreeSet<>();
			moveLeft.setConnectedDots(Move.connectingDots(boardHeight, boardWidth, otherSnakes, mySnake, head,
					moveLeft.getLocation(), moveLeft.getLocation(), new HashSet<>(), useTopBottpmRule));
			validMove.add(moveLeft);
		}

		// ok fine at this point frogot the top bottom rule and give it another try
		if (validMove.size() == 0) {
			if(useTopBottpmRule)
				return getMove(boardHeight, boardWidth, otherSnakes, mySnake, foodContainer, false);
			else 
			{
				LOG.info("Shit!!! I am trapped!");
				return null;
			}
		}
		if (validMove.size() == 1 && useTopBottpmRule) {
			int thr = (boardHeight - 6) * 3;
			if(useTopBottpmRule && validMove.get(0).getConnectedDots() < thr)
				return getMove(boardHeight, boardWidth, otherSnakes, mySnake, foodContainer, false);
			else
				return validMove.get(0);
		} else {
			Move safestMove = new Move();
			safestMove.setConnectedDots(-1);
			List<Move> goodMove = new ArrayList<>();
			for (Move move : validMove) {
				if (move.getConnectedDots() > safestMove.getConnectedDots())
					safestMove = move;
				if (move.getConnectedDots() >= boardHeight * 3)
					goodMove.add(move);
			}
			if (safestMove.getConnectedDots() < boardHeight * 3)
				if(useTopBottpmRule)
					return getMove(boardHeight, boardWidth, otherSnakes, mySnake, foodContainer, false);
			if (goodMove.size() == 0) {
				return safestMove;
			} else if (goodMove.size() == 1) {
				return goodMove.get(0);
			} else {
				//if a snake head is close to my head, avoid that snake
				List<SnakeInfo> dangeriousSnakes = new ArrayList<>();
				if(otherSnakes != null)
				{
					for(SnakeInfo s: otherSnakes)
					{
						if(s != null && (getDistance(s.getHead(), mySnake.getHead()) <= 3))
						{
							dangeriousSnakes.add(s);
						}
					}	
				}
				Set<Location> connectingDots = new HashSet<>();
				Move.connectingDots(boardHeight, boardWidth, otherSnakes, mySnake, mySnake.getSnakeBody().get(1), head,
						head, connectingDots, useTopBottpmRule);
				Location cloestFood = findTheCloestFood(foodContainer, mySnake, connectingDots);
				Move closerToFood = null;
				Integer distance = null;
				if (cloestFood == null)
					return goodMove.get(0);
				for (Move move : goodMove) {
					int foodd = getDistance(move.getLocation(), cloestFood);
					int enermyd = 0;
					for(SnakeInfo s: dangeriousSnakes)
					{
						enermyd = enermyd + getDistance(s.getHead(), move.getLocation());
					}
					int totalD = enermyd - foodd;
					if (distance == null || distance < totalD) {
						closerToFood = move;
						distance = totalD;
					}
				}
				return closerToFood;
			}
		}
	}

	public int getDistance(Location location, Location cloestFood) {

		int xDiff = location.getX() - cloestFood.getX();
		int yDiff = location.getY() - cloestFood.getY();
		int localDistance = Math.abs(xDiff) + Math.abs(yDiff);
		return localDistance;
	}

	// TODO: need to also consider snake position (both me and others)
//	private String getFood(FoodContainer foodContainer, SnakeInfo mySnakeInfo) {
//		// direction snake should go
//		boolean left = false;
//		boolean right = false;
//		boolean up = false;
//		boolean down = false;
//		if (foodContainer != null && mySnakeInfo != null && mySnakeInfo.getHead() != null) {
//			Location cloestFood = findTheCloestFood(foodContainer, mySnakeInfo);
//			LOG.info("findTheCloestFood " + cloestFood);
//			if (mySnakeInfo.getHead().getX() > cloestFood.getX())
//				left = true;
//			else if (mySnakeInfo.getHead().getX() < cloestFood.getX())
//				right = true;
//			if (mySnakeInfo.getHead().getY() > cloestFood.getY())
//				down = true;
//			else if (mySnakeInfo.getHead().getY() < cloestFood.getY())
//				up = true;
//
//			LOG.info("findTheCloestFood left " + left);
//			LOG.info("findTheCloestFood right " + right);
//			LOG.info("findTheCloestFood down " + down);
//			LOG.info("findTheCloestFood up " + up);
//			if (mySnakeInfo.getHeading() != null) {
//				if ("up".equalsIgnoreCase(mySnakeInfo.getHeading())
//						|| "down".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//					if (left)
//						return "left";
//					if (right)
//						return "right";
//					if ("up".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//						// same directory
//						if (up)
//							return "up";
//						// need a u turn
//						if (down)
//							return "right";
//					}
//					if ("down".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//						// same directory
//						if (down)
//							return "down";
//						// need a u turn
//						if (up)
//							return "right";
//					}
//				}
//				if ("left".equalsIgnoreCase(mySnakeInfo.getHeading())
//						|| "right".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//					if (up)
//						return "up";
//					if (down)
//						return "down";
//					if ("left".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//						// same directory
//						if (left)
//							return "left";
//						// need a u turn
//						if (right)
//							return "up";
//					}
//					if ("right".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//						// same directory
//						if (right)
//							return "right";
//						// need a u turn
//						if (left)
//							return "up";
//					}
//				}
//			}
//		}
//		return null;
//	}

	public Location findTheCloestFood(FoodContainer foodContainer, SnakeInfo mySnakeInfo,
			Set<Location> connectingDots) {
		// well actually it is distance square but we are comparing so not a problem
		int distance = 10000000;
		Location cloestFood = null;
		if (foodContainer != null && mySnakeInfo != null && mySnakeInfo.getHead() != null) {
			if (foodContainer.getFoodList() != null) {
				for (Location food : foodContainer.getFoodList()) {
					int xDiff = mySnakeInfo.getHead().getX() - food.getX();
					int yDiff = mySnakeInfo.getHead().getY() - food.getY();
					int localDistance = Math.abs(xDiff) + Math.abs(yDiff);
					if (distance > localDistance && connectingDots.contains(food)) {
						distance = localDistance;
						cloestFood = food;
					}
				}
			}
		}
		return cloestFood;
	}

	// TODO:need to also consider snake position (both me and others)
//	private String inDanger(int boardHeight, int boardWidth, SnakeInfo mySnakeInfo) {
//		boolean leftEdge = false;
//		boolean rightEdge = false;
//		boolean topEdge = false;
//		boolean bottomEdge = false;
//		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getX() == 0)
//			leftEdge = true;
//		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getX() == boardWidth - 1)
//			rightEdge = true;
//		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getY() == 0)
//			bottomEdge = true;
//		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getY() == boardHeight - 1)
//			topEdge = true;
//		// heading to border
//		if ("left".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//			if (leftEdge) {
//				if (topEdge)
//					return "down";
//				if (bottomEdge)
//					return "up";
//			}
//		} else if ("right".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//			if (rightEdge) {
//				if (topEdge)
//					return "down";
//				if (bottomEdge)
//					return "up";
//			}
//		}
//		// TODO: here we need method to find up or down
//
//		if ("up".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//			if (topEdge) {
//				if (leftEdge)
//					return "right";
//				if (rightEdge)
//					return "left";
//			}
//		} else if ("down".equalsIgnoreCase(mySnakeInfo.getHeading())) {
//			if (bottomEdge) {
//				if (leftEdge)
//					return "right";
//				if (rightEdge)
//					return "left";
//			}
//		}
//		// TODO: here we need method to find left or right
//		return null;
//
//	}

}

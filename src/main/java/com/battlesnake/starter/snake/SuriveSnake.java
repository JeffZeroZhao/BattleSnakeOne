package com.battlesnake.starter.snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	private FoodContainer foodContainer = new FoodContainer();
	private SnakeInfo mySnakeInfo = new SnakeInfo();

	@Override
	public Map<String, String> move(JsonNode moveRequest) {

		try {
			LOG.info("Data: {}", JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(moveRequest));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// process data
		int boardHeight = moveRequest.get("board").get("height").asInt();
		int boardWidth = moveRequest.get("board").get("width").asInt();
		JsonNode food = moveRequest.get("board").get("food");
		foodContainer.processFood(food);
		LOG.info("Food " + foodContainer.toString());
		mySnakeInfo.processSnakeInfo(moveRequest.get("you"));
		LOG.info("mySnakeInfo" + mySnakeInfo.toString());

		Location head = mySnakeInfo.getHead();
		Move moveUp = new Move();
		moveUp.setLocation(new Location(head.getX(), head.getY() + 1));
		moveUp.setMove("up");
		
		Move moveDown = new Move();
		moveDown.setLocation(new Location(head.getX(), head.getY()- 1));
		moveDown.setMove("down");
		
		Move moveRight = new Move();
		moveRight.setLocation(new Location(head.getX() + 1, head.getY()));
		moveRight.setMove("right");
		
		Move moveLeft = new Move();
		moveLeft.setLocation(new Location(head.getX() -1, head.getY()));
		moveLeft.setMove("left");
		
		List<Move> validMove = new ArrayList<>();
		if(moveUp.getLocation().isEmpty(boardHeight, boardWidth, null, mySnakeInfo))
		{
			moveUp.setConnectedDots(moveUp.connectingDots(boardHeight, boardWidth, null, mySnakeInfo, head, moveUp.getLocation(), moveUp.getLocation(), new HashSet<>()));
			validMove.add(moveUp);	
		}
		if(moveDown.getLocation().isEmpty(boardHeight, boardWidth, null, mySnakeInfo))
		{
			moveDown.setConnectedDots(moveUp.connectingDots(boardHeight, boardWidth, null, mySnakeInfo, head, moveDown.getLocation(), moveDown.getLocation(), new HashSet<>()));
			validMove.add(moveDown);	
		}
		if(moveRight.getLocation().isEmpty(boardHeight, boardWidth, null, mySnakeInfo))
		{
			moveRight.setConnectedDots(moveUp.connectingDots(boardHeight, boardWidth, null, mySnakeInfo, head, moveRight.getLocation(), moveRight.getLocation(), new HashSet<>()));
			validMove.add(moveRight);	
		}
		if(moveLeft.getLocation().isEmpty(boardHeight, boardWidth, null, mySnakeInfo))
		{
			moveLeft.setConnectedDots(moveUp.connectingDots(boardHeight, boardWidth, null, mySnakeInfo, head, moveLeft.getLocation(), moveLeft.getLocation(), new HashSet<>()));
			validMove.add(moveLeft);	
		}
		
		if(validMove.size() == 0)
		{
			LOG.info("Shit!!! I am trapped!");
			Map<String, String> response = new HashMap<>();
			response.put("move", "up");
			return response;
		}
		else if(validMove.size() == 1)
		{
			Map<String, String> response = new HashMap<>();
			response.put("move", validMove.get(0).getMove());
			return response;
		}
		else
		{
			Move safestMove = new Move();
			safestMove.setConnectedDots(-1);
			List<Move> goodMove = new ArrayList<>();
			for(Move move : validMove)
			{
				if(move.getConnectedDots() > safestMove.getConnectedDots())
					safestMove = move;
				if(move.getConnectedDots() >= mySnakeInfo.getLength() * 2)
					goodMove.add(move);
			}
			if(goodMove.size() == 0)
			{
				Map<String, String> response = new HashMap<>();
				response.put("move", safestMove.getMove());
				return response;
			}
			else if(goodMove.size() == 1)
			{
				Map<String, String> response = new HashMap<>();
				response.put("move", goodMove.get(0).getMove());
				return response;
			}
			else
			{
				Location cloestFood = findTheCloestFood(foodContainer, mySnakeInfo);
				Move closerToFood = null;
				int distance = -1;
				for(Move move : goodMove)
				{
					int d = getDistance(move.getLocation(), cloestFood);
					if(distance == -1 || distance > d)
					{
						closerToFood = move;
						distance = d;
					}
				}
				Map<String, String> response = new HashMap<>();
				response.put("move", closerToFood.getMove());
				return response;
			}
		}
			
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

	private int getDistance(Location location, Location cloestFood) {

		int xDiff = mySnakeInfo.getHead().getX() - cloestFood.getX();
		int yDiff = mySnakeInfo.getHead().getY() - cloestFood.getY();
		int localDistance = (xDiff * xDiff + yDiff * yDiff);
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

	// TODO: consider heading and snake positions
	private Location findTheCloestFood(FoodContainer foodContainer, SnakeInfo mySnakeInfo) {
		// well actually it is distance square but we are comparing so not a problem
		int distance = 10000000;
		Location cloestFood = null;
		if (foodContainer != null && mySnakeInfo != null && mySnakeInfo.getHead() != null) {
			if (foodContainer.getFoodList() != null) {
				for (Location food : foodContainer.getFoodList()) {
					int xDiff = mySnakeInfo.getHead().getX() - food.getX();
					int yDiff = mySnakeInfo.getHead().getY() - food.getY();
					int localDistance = (xDiff * xDiff + yDiff * yDiff);
					if (distance > localDistance) {
						distance = localDistance;
						cloestFood = food;
					}
				}
			}
		}
		return cloestFood;
	}

	// TODO:need to also consider snake position (both me and others)
	private String inDanger(int boardHeight, int boardWidth, SnakeInfo mySnakeInfo) {
		boolean leftEdge = false;
		boolean rightEdge = false;
		boolean topEdge = false;
		boolean bottomEdge = false;
		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getX() == 0)
			leftEdge = true;
		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getX() == boardWidth - 1)
			rightEdge = true;
		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getY() == 0)
			bottomEdge = true;
		if (mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getY() == boardHeight - 1)
			topEdge = true;
		// heading to border
		if ("left".equalsIgnoreCase(mySnakeInfo.getHeading())) {
			if (leftEdge) {
				if (topEdge)
					return "down";
				if (bottomEdge)
					return "up";
			}
		} else if ("right".equalsIgnoreCase(mySnakeInfo.getHeading())) {
			if (rightEdge) {
				if (topEdge)
					return "down";
				if (bottomEdge)
					return "up";
			}
		}
		// TODO: here we need method to find up or down

		if ("up".equalsIgnoreCase(mySnakeInfo.getHeading())) {
			if (topEdge) {
				if (leftEdge)
					return "right";
				if (rightEdge)
					return "left";
			}
		} else if ("down".equalsIgnoreCase(mySnakeInfo.getHeading())) {
			if (bottomEdge) {
				if (leftEdge)
					return "right";
				if (rightEdge)
					return "left";
			}
		}
		// TODO: here we need method to find left or right
		return null;

	}

}

package com.battlesnake.starter.snake;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.battlesnake.starter.data.Food;
import com.battlesnake.starter.data.FoodContainer;
import com.battlesnake.starter.data.SnakeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SuriveSnake extends BattleSnaker{
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
        //process data
        int boardHeight = moveRequest.get("board").get("height").asInt();
        int boardWidth = moveRequest.get("board").get("width").asInt();
        JsonNode food = moveRequest.get("board").get("food");
        foodContainer.processFood(food);
        LOG.info("Food " + foodContainer.toString());
        mySnakeInfo.processSnakeInfo(moveRequest.get("you"));
        LOG.info("mySnakeInfo" + mySnakeInfo.toString());
        
//        "up", "down", "left", "right"
        String move = inDanger(boardHeight, boardWidth, mySnakeInfo);
        LOG.info("MOVE inDanger{}", move);
        if(move == null)
        {
        	move = getFood(foodContainer, mySnakeInfo);
            LOG.info("MOVE getFood{}", move);
        }
        if(move == null && mySnakeInfo.getHeading() != null)
        {
        	move = mySnakeInfo.getHeading();
            LOG.info("MOVE continue{}", move);
        }
        
        LOG.info("MOVE {}", move);

        Map<String, String> response = new HashMap<>();
        response.put("move", move);
		return response;
	}

	private String getFood(FoodContainer foodContainer, SnakeInfo mySnakeInfo) {
		//direction snake should go
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		if(foodContainer != null && mySnakeInfo != null && mySnakeInfo.getHead() != null)
		{
			Food cloestFood = findTheCloestFood(foodContainer, mySnakeInfo);
	        LOG.info("findTheCloestFood " + cloestFood);
			if(mySnakeInfo.getHead().getX() > cloestFood.getX())
				left = true;
			else if(mySnakeInfo.getHead().getX() < cloestFood.getX())
				right = true;
			if(mySnakeInfo.getHead().getY() > cloestFood.getY())
				down = true;
			else if(mySnakeInfo.getHead().getY() < cloestFood.getY())
				up = true;

	        LOG.info("findTheCloestFood left " + left);
	        LOG.info("findTheCloestFood right " + right);
	        LOG.info("findTheCloestFood down " + down);
	        LOG.info("findTheCloestFood up " + up);
			if(mySnakeInfo.getHeading() != null)
			{
				if("up".equalsIgnoreCase(mySnakeInfo.getHeading())
						|| "down".equalsIgnoreCase(mySnakeInfo.getHeading()))
				{
					if(left)
						return "left";
					if(right)
						return "right";
					if ("up".equalsIgnoreCase(mySnakeInfo.getHeading()))
					{
						//same directory
						if(up)
							return "up";
						//need a u turn
						if(down)
							return "right";
					}
					if ("down".equalsIgnoreCase(mySnakeInfo.getHeading()))
					{
						//same directory
						if(down)
							return "down";
						//need a u turn
						if(up)
							return "right";
					}
				}
				if("left".equalsIgnoreCase(mySnakeInfo.getHeading())
						|| "right".equalsIgnoreCase(mySnakeInfo.getHeading()))
				{
					if(up)
						return "up";
					if(down)
						return "down";
					if ("left".equalsIgnoreCase(mySnakeInfo.getHeading()))
					{
						//same directory
						if(left)
							return "left";
						//need a u turn
						if(right)
							return "up";
					}
					if ("right".equalsIgnoreCase(mySnakeInfo.getHeading()))
					{
						//same directory
						if(right)
							return "right";
						//need a u turn
						if(left)
							return "up";
					}
				}
			}
		}
		return null;
	}

	private Food findTheCloestFood(FoodContainer foodContainer, SnakeInfo mySnakeInfo) {
		//well actually it is distance square but we are comparing so not a problem
		int distance = 10000000;
		Food cloestFood = null;
		if(foodContainer != null && mySnakeInfo != null && mySnakeInfo.getHead() != null)
		{
			if(foodContainer.getFoodList() != null)
			{
				for(Food food: foodContainer.getFoodList())
				{
					int xDiff = mySnakeInfo.getHead().getX() - food.getX();
					int yDiff = mySnakeInfo.getHead().getY() - food.getY();
					int localDistance = (xDiff*xDiff + yDiff*yDiff);
					if(distance > localDistance)
					{
						distance = localDistance;
						cloestFood = food;
					}
				}
			}
		}
		return cloestFood;
	}

	private String inDanger(int boardHeight, int boardWidth, SnakeInfo mySnakeInfo) {
		boolean leftEdge = false;
		boolean rightEdge = false;
		boolean topEdge = false;
		boolean bottomEdge = false;
		if(mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getX() == 0)
			leftEdge = true;
		if(mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getX() == boardWidth-1)
			rightEdge = true;
		if(mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getY() == 0)
			bottomEdge = true;
		if(mySnakeInfo.getHead() != null && mySnakeInfo.getHead().getY() == boardHeight-1)
			topEdge = true;
		//heading to border
		if("left".equalsIgnoreCase(mySnakeInfo.getHeading()))
		{
			if(leftEdge)
			{
				if(topEdge)
					return "down";
				if(bottomEdge)
					return "up";
				return "down";
			}
		}
		else if("right".equalsIgnoreCase(mySnakeInfo.getHeading()))
		{
			if(rightEdge)
			{
				if(topEdge)
					return "down";
				if(bottomEdge)
					return "up";
				return "down";
			}
		}
		else if("up".equalsIgnoreCase(mySnakeInfo.getHeading()))
		{
			if(topEdge)
			{
				if(leftEdge)
					return "right";
				if(rightEdge)
					return "left";
				return "right";
			}
		}
		else if("down".equalsIgnoreCase(mySnakeInfo.getHeading()))
		{
			if(bottomEdge)
			{
				if(leftEdge)
					return "right";
				if(rightEdge)
					return "left";
				return "right";
			}
		}
		return null;
		
	}

}

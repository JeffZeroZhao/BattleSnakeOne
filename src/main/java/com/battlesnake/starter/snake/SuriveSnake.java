package com.battlesnake.starter.snake;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        
        String[] possibleMoves = { "up", "down", "left", "right" };

        String move = possibleMoves[3];

        LOG.info("MOVE {}", move);

        Map<String, String> response = new HashMap<>();
        response.put("move", move);
		return response;
	}

}

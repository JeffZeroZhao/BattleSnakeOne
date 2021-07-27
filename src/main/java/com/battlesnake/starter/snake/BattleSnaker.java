package com.battlesnake.starter.snake;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class BattleSnaker {
    /**
     * This method is called on every turn of a game. It's how your snake decides
     * where to move.
     * 
     * Valid moves are "up", "down", "left", or "right".
     *
     * @param moveRequest a map containing the JSON sent to this snake. Use this
     *                    data to decide your next move.
     * @return a response back to the engine containing Battlesnake movement values.
     */
    public abstract Map<String, String> move(JsonNode moveRequest);
}

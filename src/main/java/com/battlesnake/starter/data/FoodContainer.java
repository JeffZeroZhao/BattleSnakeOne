package com.battlesnake.starter.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class FoodContainer {
	private List<Food> foodList = null;

	public void processFood(JsonNode foods) {
		try {
			foodList = new ArrayList<>();
			if (foods != null) {
				for (int i = 0; i < foods.size(); i++) {
					JsonNode item = foods.get(i);
					Food food = new Food();
					food.setX(item.get("x").asInt());
					food.setY(item.get("y").asInt());
					foodList.add(food);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String foods = "{";
		for (Food food : foodList) {
			foods = foods + food.toString();
		}
		foods = foods + "}";
		return "FoodContainer [foodList=" + foods + "]";
	}

}

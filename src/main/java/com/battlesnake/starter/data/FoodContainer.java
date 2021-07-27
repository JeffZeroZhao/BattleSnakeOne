package com.battlesnake.starter.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

public class FoodContainer {
	private List<Food> foodList = null;
    private static final Logger LOG = LoggerFactory.getLogger(FoodContainer.class);

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

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
			        LOG.info("processFood add food", food.toString());
				}
			}
		} catch (Exception e) {
	        LOG.info("processFood error ", e.getMessage());
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

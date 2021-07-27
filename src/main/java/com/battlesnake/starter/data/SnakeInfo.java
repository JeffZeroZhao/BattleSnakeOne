package com.battlesnake.starter.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class SnakeInfo {
	public class BodyItem {
		private int x;
		private int y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public String toString() {
			return "BodyItem [x=" + x + ", y=" + y + "]";
		}

	}

	private String name;
	private int health;
	private List<BodyItem> snakeBody;
	private BodyItem head;
	private int length;
	private String heading;

	public void processSnakeInfo(JsonNode snakeInfo) {
		snakeBody = new ArrayList<>();
		try {
			if (snakeInfo != null) {
				this.name = snakeInfo.get("name").asText();
				this.health = snakeInfo.get("health").asInt();
				for (int i = 0; i < snakeInfo.get("body").size(); i++) {
					if(snakeInfo.get("body").get(i) != null)
					{
						BodyItem bodyItem = new BodyItem();
						bodyItem.setX(snakeInfo.get("body").get(i).get("x").asInt());
						bodyItem.setY(snakeInfo.get("body").get(i).get("y").asInt());
						snakeBody.add(bodyItem);
					}
				}
				this.heading = getHeading(snakeBody);
				this.head = new BodyItem();
				this.head.setX(snakeInfo.get("head").get("x").asInt());
				this.head.setY(snakeInfo.get("head").get("y").asInt());
				this.length = snakeInfo.get("length").asInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getHeading(List<BodyItem> snakeBody) {
		if(snakeBody != null && snakeBody.size() > 2)
		{
			BodyItem first = snakeBody.get(0);
			BodyItem second = snakeBody.get(1);
			if(first.x == second.x)
			{
				if(first.y > second.y)
				{
					return "up";
				}
				else if(first.y < second.y)
				{
					return "down";
				}
			}
			else if(first.x > second.x)
			{
				return "right";
			}
			else if(first.x < second.x)
			{
				return "left";
			}
				
		}
		return null;
	}

	@Override
	public String toString() {
		String snakeBodyStr = "{";
		for(BodyItem bodyItem : this.snakeBody)
		{
			snakeBodyStr = snakeBodyStr + bodyItem.toString();
		}
		snakeBodyStr = snakeBodyStr + "}";
		return "SnakeInfo [name=" + name + ", health=" + health + ", snakeBody=" + snakeBodyStr + ", head=" + head
				+ ", length=" + length + ", heading=" + heading + "]";
	}
	
}

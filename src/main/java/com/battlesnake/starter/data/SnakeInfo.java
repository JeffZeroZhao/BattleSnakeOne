package com.battlesnake.starter.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

public class SnakeInfo {
	private static final Logger LOG = LoggerFactory.getLogger(SnakeInfo.class);
	private String name;
	private int health;
	private List<Location> snakeBody;
	private Location head;
	private int length;
	private String heading;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public List<Location> getSnakeBody() {
		return snakeBody;
	}

	public void setSnakeBody(List<Location> snakeBody) {
		this.snakeBody = snakeBody;
	}

	public Location getHead() {
		return head;
	}

	public void setHead(Location head) {
		this.head = head;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public void processSnakeInfo(JsonNode snakeInfo) {
		snakeBody = new ArrayList<>();
		try {
			if (snakeInfo != null) {
				this.id = snakeInfo.get("id").asText();
				this.name = snakeInfo.get("name").asText();
				this.health = snakeInfo.get("health").asInt();
				for (int i = 0; i < snakeInfo.get("body").size(); i++) {
					if (snakeInfo.get("body").get(i) != null) {
						Location bodyItem = new Location();
						bodyItem.setX(snakeInfo.get("body").get(i).get("x").asInt());
						bodyItem.setY(snakeInfo.get("body").get(i).get("y").asInt());
						snakeBody.add(bodyItem);
					}
				}
				this.heading = getHeading(snakeBody);
				this.head = new Location();
				this.head.setX(snakeInfo.get("head").get("x").asInt());
				this.head.setY(snakeInfo.get("head").get("y").asInt());
				this.length = snakeInfo.get("length").asInt();
			}
		} catch (Exception e) {
			LOG.info("processFood error ", e.getMessage());
			e.printStackTrace();
		}
	}

	private String getHeading(List<Location> snakeBody) {
		if (snakeBody != null && snakeBody.size() > 2) {
			Location first = snakeBody.get(0);
			Location second = snakeBody.get(1);
			if (first.getX() == second.getX()) {
				if (first.getY() > second.getY()) {
					return "up";
				} else if (first.getY() < second.getY()) {
					return "down";
				}
			} else if (first.getX() > second.getX()) {
				return "right";
			} else if (first.getX() < second.getX()) {
				return "left";
			}

		}
		return null;
	}

	@Override
	public String toString() {
		String snakeBodyStr = "{";
		for (Location bodyItem : this.snakeBody) {
			snakeBodyStr = snakeBodyStr + bodyItem.toString();
		}
		snakeBodyStr = snakeBodyStr + "}";
		return "SnakeInfo [name=" + name + ", id=" + id + ", health=" + health + ", snakeBody=" + snakeBodyStr
				+ ", head=" + head + ", length=" + length + ", heading=" + heading + "]";
	}

}

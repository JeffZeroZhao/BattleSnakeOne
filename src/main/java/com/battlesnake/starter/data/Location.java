package com.battlesnake.starter.data;

import java.util.List;

public class Location implements Comparable<Location> {
	private int x;
	private int y;

	public Location() {
	}

	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	public boolean isEmpty(int boardHeight, int boardWidth, List<SnakeInfo> otherSnakes, SnakeInfo mySnake, boolean topBottomRule) {
		if (this.x < 0 || this.x >= boardWidth || this.y < 0 || this.y >= boardHeight)
			return false;
		// need more thought
		// if x not left most and right most line, avoid bottom and top
		if (topBottomRule && mySnake.getLength() > boardHeight*2 
				&& (this.x > 0 && this.x < boardWidth && (this.y == 0 || this.y == boardHeight - 1)))
			return false;

		if (mySnake.getSnakeBody() != null) {
			// last of my body will be removed when I move forward, so skip the last one
			for (int i = 0; i < mySnake.getSnakeBody().size() - 1; i++) {
				if (mySnake.getSnakeBody().get(i) != null && mySnake.getSnakeBody().get(i).equals(this))
					return false;
			}
			if (otherSnakes != null) {
				for (SnakeInfo otherSnake : otherSnakes) {
					// last of my body will be removed when I move forward, so skip the last one
					for (int i = 0; i < otherSnake.getSnakeBody().size() - 1; i++) {
						if (otherSnake.getSnakeBody().get(i) != null && otherSnake.getSnakeBody().get(i).equals(this))
							return false;
					}
					// those are other snakes so check the dots directory connect to their head also
					// consider taken
					Location head = otherSnake.getHead();
					if (new Location(head.getX() - 1, head.getY()).equals(this))
						return false;
					if (new Location(head.getX() + 1, head.getY()).equals(this))
						return false;
					if (new Location(head.getX(), head.getY() - 1).equals(this))
						return false;
					if (new Location(head.getX(), head.getY() + 1).equals(this))
						return false;
				}
			}
		}
		return true;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[x: " + x + " y: " + y + "]";
	}

	@Override
	public int compareTo(Location arg0) {
		int diff = this.getX() - arg0.getX();
		if (diff == 0)
			diff = this.getY() - arg0.getY();
		return diff;
	}
}

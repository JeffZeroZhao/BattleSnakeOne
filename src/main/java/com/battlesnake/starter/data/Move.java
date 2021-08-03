package com.battlesnake.starter.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Move {
	private String move;
	private Location location;
	private int connectedDots;

	public int getConnectedDots() {
		return connectedDots;
	}

	public void setConnectedDots(int connectedDots) {
		this.connectedDots = connectedDots;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public static int connectingDots(int boardHeight, int boardWidth, List<SnakeInfo> otherSnakes, SnakeInfo mySnake,
			Location from, Location current, Location start, Set<Location> connectedLocation) {
		int connectionPoints = 0;
		Location left = new Location(current.getX() - 1, current.getY());
		Location right = new Location(current.getX() + 1, current.getY());
		Location up = new Location(current.getX(), current.getY() + 1);
		Location down = new Location(current.getX(), current.getY() - 1);
		if (!left.equals(from) && !left.equals(start)) {
			if (left.isEmpty(boardHeight, boardWidth, otherSnakes, mySnake)) {
				if (!connectedLocation.contains(left)) {
					connectedLocation.add(left);
					connectionPoints = connectionPoints + 1;
					connectionPoints = connectionPoints + connectingDots(boardHeight, boardWidth, otherSnakes, mySnake,
							current, left, start, connectedLocation);
				}
			}
		}
		if (!right.equals(from) && !right.equals(start)) {
			if (right.isEmpty(boardHeight, boardWidth, otherSnakes, mySnake)) {
				if (!connectedLocation.contains(right)) {
					connectedLocation.add(right);
					connectionPoints = connectionPoints + 1;
					connectionPoints = connectionPoints + connectingDots(boardHeight, boardWidth, otherSnakes, mySnake,
							current, right, start, connectedLocation);
				}
			}
		}
		if (!up.equals(from) && !up.equals(start)) {
			if (up.isEmpty(boardHeight, boardWidth, otherSnakes, mySnake)) {
				if (!connectedLocation.contains(up)) {
					connectedLocation.add(up);
					connectionPoints = connectionPoints + 1;
					connectionPoints = connectionPoints + connectingDots(boardHeight, boardWidth, otherSnakes, mySnake,
							current, up, start, connectedLocation);
				}
			}
		}
		if (!down.equals(from) && !down.equals(start)) {
			if (down.isEmpty(boardHeight, boardWidth, otherSnakes, mySnake)) {
				if (!connectedLocation.contains(down)) {
					connectedLocation.add(down);
					connectionPoints = connectionPoints + 1;
					connectionPoints = connectionPoints + connectingDots(boardHeight, boardWidth, otherSnakes, mySnake,
							current, down, start, connectedLocation);
				}
			}
		}

		return connectionPoints;

	};
}

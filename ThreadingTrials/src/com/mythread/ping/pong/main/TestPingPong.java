package com.mythread.ping.pong.main;

import com.mythread.game.Game;
import com.mythread.ping.pong.PingPongGame;

public class TestPingPong {

	public static void main(String[] args) {
		Game game = new PingPongGame();
		game.startGame();
		try {
			Thread.sleep(70000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.stopGame();
	}

}

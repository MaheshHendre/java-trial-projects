package com.mythread.ping.pong;

import java.util.Random;

import com.mythread.game.Game;
import com.mythread.game.Player;

public class PingPongPlayer implements Player {

	private String name;

	private Game game;

	public PingPongPlayer(String name, PingPongGame pingPongGame) {
		this.name = name;
		this.game = pingPongGame;
	}

	@Override
	public void play() {
		synchronized (game) {
			while (isGameOn()) {
				System.out.println(name);
				try {
					Thread.sleep(new Random(50).nextInt(5000));
					game.notify();
					Thread.sleep(new Random(50).nextInt(5000));
					game.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			game.notifyAll();
		}
	}

	@Override
	public void run() {
		this.play();
	}

	private boolean isGameOn() {
		return this.game.isGameOn();
	}

}

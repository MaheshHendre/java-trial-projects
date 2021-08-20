package com.mythread.ping.pong;

import com.mythread.game.Game;

public class PingPongGame implements Game {

	private boolean isGameOn = false;
	@Override
	public void startGame() {
		this.isGameOn = true;
		PingPongPlayer ping = new PingPongPlayer("ping",this);
		PingPongPlayer pong = new PingPongPlayer("pong",this);
		new Thread(ping).start();
		new Thread(pong).start();
	}

	@Override
	public boolean isGameOn() {
		return isGameOn;
	}
	
	@Override
	public void stopGame() {
		this.isGameOn = false;
	}

}

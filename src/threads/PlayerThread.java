package threads;

import main.Player;

public class PlayerThread extends Thread{
	
	private Player player;
	
	public PlayerThread(Player player) {
		this.player=player;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(player.isAlive()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(player.isMoving()) {
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(player.isJumping()) {
				while(player.getPosY()>=Player.HEIGHT_LIMIT) {
					player.jump();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(player.isHolding()) {
					 player.setHolding(false);
					 player.getE().setDir(player.getDir());
					 player.getE().setLimit();
					 player.getE().setMoving(false);
					 player.getE().setFlying(true);
				 }
				player.setJumping(false);
				while(player.getPosY()<=450) {
					player.gravity();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if(player.checkEgg()) {
				if(player.isSteal()) {
					player.getEgg();
				}
			}
		}
		
	}
	
	
	
}

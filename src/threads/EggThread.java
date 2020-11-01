package threads;

import main.Egg;

public class EggThread extends Thread{
	
	private Egg egg;
	
	public EggThread(Egg egg) {
		this.egg=egg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(egg.isAlive()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!egg.isMoving() && !egg.isFlying()) {
				while(egg.getPosY()<=578) {
					egg.gravity();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if(egg.isMoving()){
				if(egg.getP()!=null) {
					egg.setPosX(egg.getP().getPosX()+45);
					egg.setPosY(egg.getP().getPosY()+30);
				}
			}
			else if(egg.isFlying()) {
				while((egg.getPosX()<=egg.getLimit()+300) && (egg.getPosX()>=egg.getLimit()-300)) {
					egg.move();
					if(egg.isInBasket()) {
						egg.getP().setScore(egg.getP().getScore()+1);
						egg.respawn();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
						}
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				egg.setFlying(false);
				egg.setP(null);
				
			}
			
		}
		
	}
	
	
	
}

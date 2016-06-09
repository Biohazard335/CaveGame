package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import collision.shapes.Point;
import enemies.Crab;

public class Keyboard implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_M){
			CaveGame.returnMemory();
		}
		if(e.getKeyCode()== KeyEvent.VK_H){
			CaveGame.returnHV();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(CaveGame.frames==10){
				CaveGame.frames=100;
			}else if (CaveGame.frames==100){
				CaveGame.frames=10;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(CaveGame.frames==10){
				CaveGame.frames=700;
			}else if (CaveGame.frames==700){
				CaveGame.frames=10;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_U){
			CaveGame.rooms.get(CaveGame.player.roomnum).enemies.add(
					new Crab(new Point(CaveGame.player.location.x+(CaveGame.player.direction?170:-170),CaveGame.player.location.y)));
		}
		if(CaveGame.player.climbing==false){
			if(e.getKeyCode()== KeyEvent.VK_W && CaveGame.player.freefalling == false){
				CaveGame.player.freefalling=true;
				CaveGame.player.yvelocity=CaveGame.jump;
			}
			if(e.getKeyCode()== KeyEvent.VK_S){
				CaveGame.player.down=true;
			}
			if(e.getKeyCode()== KeyEvent.VK_S && CaveGame.player.freefalling == false
					&& CaveGame.rooms.get(CaveGame.player.roomnum).floors.get(CaveGame.player.floornum).fallable){
				CaveGame.player.freefalling=true;
				CaveGame.player.location.y+=5;
			}
			if(e.getKeyCode()== KeyEvent.VK_SPACE){
				CaveGame.player.throwrock();
			}
			if(e.getKeyCode()== KeyEvent.VK_A){
				CaveGame.player.xvelocity=-CaveGame.speed;
				CaveGame.player.direction=false;
			}
			if(e.getKeyCode()== KeyEvent.VK_D){
				CaveGame.player.xvelocity=CaveGame.speed;
				CaveGame.player.direction=true;
			}
			if(e.getKeyCode()== KeyEvent.VK_Q){
				CaveGame.player.changerock(-1);
			}
			if(e.getKeyCode()== KeyEvent.VK_E){
				CaveGame.player.changerock(1);
			}
			if(e.getKeyCode()== KeyEvent.VK_G){
				CaveGame.graphics=!CaveGame.graphics;
			}/*
			}*/
			if(e.getKeyCode()== KeyEvent.VK_L){
				System.out.println(CaveGame.player.location.x+", "+CaveGame.player.location.y
						+"\n"+CaveGame.player.xvelocity+", "+CaveGame.player.yvelocity);
				System.out.println(CaveGame.rooms.get(CaveGame.player.roomnum).rocks.size());
			}
			
		}else{
			if(e.getKeyCode()== KeyEvent.VK_W){
				CaveGame.player.yvelocity=-CaveGame.speed;
			}
			if(e.getKeyCode()== KeyEvent.VK_S){
				CaveGame.player.yvelocity=CaveGame.speed;
			}
			if(e.getKeyCode()== KeyEvent.VK_A){
				CaveGame.player.xvelocity=-CaveGame.speed;
				CaveGame.player.location.x-=21;
				CaveGame.player.yvelocity=0;
				CaveGame.player.direction=false;
				CaveGame.player.freefalling=true;
				CaveGame.player.climbing=false;
			}
			if(e.getKeyCode()== KeyEvent.VK_D){
				CaveGame.player.xvelocity=CaveGame.speed;
				CaveGame.player.location.x+=21;
				CaveGame.player.yvelocity=0;
				CaveGame.player.direction=true;
				CaveGame.player.freefalling=true;
				CaveGame.player.climbing=false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_A){
			if(CaveGame.player.xvelocity<0)
				CaveGame.player.xvelocity=0;
		}
		if(e.getKeyCode()== KeyEvent.VK_D){
			if(CaveGame.player.xvelocity>0)
				CaveGame.player.xvelocity=0;
		}
		if(e.getKeyCode()== KeyEvent.VK_S){
			CaveGame.player.down=false;
		}
		if(CaveGame.player.climbing){
			if(e.getKeyCode()== KeyEvent.VK_W){
				CaveGame.player.yvelocity=0;
			}
			if(e.getKeyCode()== KeyEvent.VK_S){
				CaveGame.player.yvelocity=0;
			}
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
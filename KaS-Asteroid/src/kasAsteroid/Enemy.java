package kasAsteroid;

import java.awt.Color;

public class Enemy implements GameComponent {
	
	private double posX;
	private double posY;
	private double speed = 0.5;
	private int size = 5;
	private Color color = Color.orange;
	
	public Enemy() {
		int width = Main.ventana.getWidth();
		
		double auxX = Math.random()*width*2;
		if(Math.random()>0.5)
			auxX *= -1;
		
		double auxY =  Math.sqrt(Math.abs( width*width - Math.pow((auxX-width/2),2)));
		
		auxY = auxY + Main.player.getPosY() - 300;
		
		if(Math.random()>0.5) {
			double a = auxY-Main.player.getPosY();
			auxY = Main.player.getPosY()-a;
		}
		this.posX = auxX + Main.player.getPosX() - 300;
		this.posY = auxY;
		
	}
	

	@Override
	public void actualizarPos() {
		
		//El movimiento sigue sin estar bien, se mueve de forma... hiperbólica???
		
		double dY = posY-Main.player.getPosY();
		double dX = posX-Main.player.getPosX();
		
		double rotX = Math.asin(dY/(Math.sqrt(dX*dX+dY*dY)));
		
		double vectorX = speed * Math.cos(rotX);
		double vectorY = speed * Math.sin(rotX) *-1;
		
		if(posX<Main.player.getPosX())
			vectorX *= -1;
		
		double calX = posX - vectorX;
		double calY = posY + vectorY;
		
		this.posX = calX;
		this.posY = calY;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}

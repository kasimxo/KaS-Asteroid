package kasAsteroid;

import java.awt.Point;

public class Player implements GameComponent {
	
	private int health = 5;
	private double posX;
	private double posY;
	private int radio;
	private int speedX;
	private int speedY;
	private double incRot = 0;
	private double rotX = 0;
	private int[] x;
	private int[] y;
	
	public Player() {
		this.radio = 10;
		this.posX = 0;
		this.posY = 0;
		this.speedX = 0;
		this.speedY = 0;
		this.x = new int[5];
		this.y = new int[5];
		this.x[0] = 0;
		this.y[0] = -radio;
		this.x[1] = radio;
		this.y[1] = radio;
		this.x[2] = 0;
		this.y[2] = radio/2;
		this.x[3] = -radio;
		this.y[3] = radio;
		this.x = getVertexX();
		this.y = getVertexY();
				
	}
	
	public void setPosition(double x, double y) {
		this.posX = x;
		this.posY = y;
	}
	
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	//Falta que tenga en cuenta la rotación del objeto para poder avanzar en esa línea
	public void actualizarPos() {
		
		
		rotX += incRot;
		
		double calX = posX - speedY * Math.sin(rotX) + speedX * Math.sin(rotX+1.57079633);
		double calY = posY + speedY * Math.cos(rotX) - speedX * Math.cos(rotX+1.57079633);
		
		if(calX>Main.ventana.getWidth()) {
			calX = Main.ventana.getWidth();
		} else if(calX<0) {
			calX = 0;
		} else if(calY>Main.ventana.getHeight()) {
			calY = Main.ventana.getHeight();
		} else if(calY<0) {
			calY = 0;
		} else {
			setPosition(calX, calY);
		}
			

	}

	public int[] getY() {
		return y;
	}

	public void setY(int[] y) {
		this.y = y;
	}

	public int[] getX() {
		return x;
	}

	public void setX(int[] x) {
		this.x = x;
	}
	
	/**
	 * A partir de la posición del objeto y la rotación estableciada, calcula la posición final de los vértices 
	 * teniendo en cuenta también la rotación.
	 * @return Un int[] con la posición final X de los vértices 
	 */
	public int[] getVertexX() {
		int[] fin = new int[4];
		
		for (int i = 0; i<4; i++) {
			fin[i] = (int) (x[i] + posX);
		}
		
		return fin;
	}
	
	/**
	 * A partir de la posición del objeto y la rotación estableciada, calcula la posición final de los vértices 
	 * teniendo en cuenta también la rotación.
	 * @return Un int[] con la posición final Y de los vértices
	 */
	public int[] getVertexY() {
		int[] fin = new int[4];
		
		for (int i = 0; i<4; i++) {
			fin[i] = (int) (y[i] + posY);
		}
		
		return fin;
	}
	
	/**
	 * Calcula las posiciones de los vértices y devuelve el array correspondiente.
	 * @param a -> Si es 0, devolverá el array de las posiciones X, si no, devuelve las posiciones y
	 */
	public int[] calculoPos(int a) {
		int[] calX = new int[4];
		int[] calY = new int[4];
		
		for (int i = 0; i<4; i++) {
			calX[i] = ((int) (Math.round( x[i] * Math.cos(rotX) - y[i] * Math.sin(rotX)) + posX));
			calY[i] = ((int) (Math.round( x[i] * Math.sin(rotX) + y[i] * Math.cos(rotX)) + posY));
		}
		
		
		if (a==0) {
			return calX;
		} else {
			return calY;
		}
	}
	
	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
	

	public double getRotX() {
		return rotX;
	}


	public void setRotX(double rotX) {
		this.rotX = rotX;
	}

	public double getIncRot() {
		return incRot;
	}

	public void setIncRot(double incRot) {
		this.incRot = incRot;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}

package kasAsteroid;

import java.awt.Color;

public class Proyectil implements GameComponent {
	
	private double posX;
	private double posY;
	private double rotX;
	private int radio = 5;
	private Color color = Color.red;
	//La variable de velocidad la declaramos estática para toda la clase
	private static int speed = -3;
	
	public Proyectil() {
		this.posX = Main.nucleo.getPosX();
		this.posY = Main.nucleo.getPosY();
		this.rotX = Main.nucleo.getRotX();
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
	public double getRot() {
		return rotX;
	}
	public void setRot(double rot) {
		this.rotX = rot;
	}

	public void actualizarPos() {
		double calX = posX - speed * Math.sin(rotX);
		double calY = posY + speed * Math.cos(rotX);
		
		this.posX = calX;
		this.posY = calY;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}

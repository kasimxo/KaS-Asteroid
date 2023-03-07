package kasAsteroid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5834674684066542115L;
	public static JFrame ventana;
	public static Center nucleo;
	public static Timer timer;
	public static ActionListener alistener;
	public static KeyListener klistener;
	public static int intervaloFrames = 25;
	public static List<Proyectil> proy;
	public static List<Enemy> enemys;
	public static List<Object> limpieza;

	public static void main(String[] args) {
		proy = new ArrayList<Proyectil>();
		enemys = new ArrayList<Enemy>();
		limpieza = new ArrayList<Object>();
		addKlistener();
		addVentana();
		addCenter();
		addAlistener();
		addTimer();
	}

	private static void addKlistener() {
		//Aquí configuramos todo el keylistener
        klistener = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                	nucleo.setSpeedY(-1);
                    break;
                case KeyEvent.VK_S:
                	nucleo.setSpeedY(1);
                    break;
                case KeyEvent.VK_A:
                	nucleo.setIncRot(-0.1);
                	//nucleo.setSpeedX(-1);
                    break;
                case KeyEvent.VK_D:
                	nucleo.setIncRot(0.1);
                	//nucleo.setSpeedX(1);
                    break;
                case KeyEvent.VK_UP:
                    break;
                case KeyEvent.VK_DOWN:
                    break;
                case KeyEvent.VK_LEFT:
                	//nucleo.setIncRot(-0.1);
                    break;
                case KeyEvent.VK_RIGHT:
                	//nucleo.setIncRot(0.1);
                    break;
                case KeyEvent.VK_SPACE:
                	proy.add(new Proyectil());
                	break;
                }
            }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				 switch(e.getKeyCode()) {
	                case KeyEvent.VK_W:
	                	nucleo.setSpeedY(0);
	                    break;
	                case KeyEvent.VK_S:
	                	nucleo.setSpeedY(0);
	                    break;
	                case KeyEvent.VK_A:
	                	nucleo.setIncRot(0);
	                	//nucleo.setSpeedX(0);
	                    break;
	                case KeyEvent.VK_D:
	                	nucleo.setIncRot(0);
	                	//nucleo.setSpeedX(0);
	                    break;
	                case KeyEvent.VK_UP:
	                    break;
	                case KeyEvent.VK_DOWN:
	                    break;
	                case KeyEvent.VK_LEFT:
	                	//nucleo.setIncRot(0);
	                    break;
	                case KeyEvent.VK_RIGHT:
	                	//nucleo.setIncRot(0);
	                    break;
				 }
			}
        };
		
	}

	private static void addAlistener() {
		alistener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(nucleo.getHealth()==0) {
					timer.stop();
				}
				
				spawnEnemy();
				
				for (Proyectil proyectil : proy) {
					proyectil.actualizarPos();
					if (proyectil.getPosX()>ventana.getWidth() || proyectil.getPosX()<0 || proyectil.getPosY()>ventana.getHeight() || proyectil.getPosY()<0) {
						limpieza.add(proyectil);
					} else {
						//Aquí checkeamos si le hemos dado a algún enemigo y lo eliminamos si eso
						for (Enemy enemy : enemys) {
							if(Math.abs(proyectil.getPosX()-enemy.getPosX())<proyectil.getRadio() && Math.abs(proyectil.getPosY()-enemy.getPosY())<proyectil.getRadio()) {
								limpieza.add(enemy);
								limpieza.add(proyectil);
							}
						}
					}
				}
				
				for (Enemy enemy : enemys) {
					if(Math.abs(enemy.getPosX()-nucleo.getPosX())<nucleo.getRadio() && Math.abs(enemy.getPosY()-nucleo.getPosY())<nucleo.getRadio()) {
						limpieza.add(enemy);
						nucleo.setHealth(nucleo.getHealth()-1);
					}
				}
				
				for (Object obj : limpieza) {
					//Aquí eliminamos los enemigos o proyectiles.
					if(obj instanceof Proyectil) {
						proy.remove(obj);
					} else if (obj instanceof Enemy) {
						enemys.remove(obj);
					}
				}
				limpieza.clear();
				
				
				nucleo.actualizarPos();
				ventana.repaint();
			}
        };		
	}
	
	
	/**
	 * Cada frame genera un valor aleatorio, si pasa el test, genera un enemigo
	 */
	protected static void spawnEnemy() {
		if(Math.random()>0 && enemys.size()<100) {
			enemys.add(new Enemy());
		}
	}

	private static void addTimer() {
		timer = new Timer(intervaloFrames,alistener);
		timer.start();
	}

	private static void addCenter() {
		nucleo = new Center();
		int x = ventana.getWidth()/2;
		int y = ventana.getHeight()/2;
		nucleo.setPosition(x,y);
	}

	private static void addVentana() {
		construirVentana();
	}
	
	
	public static void construirVentana() {
		
		
		ventana = new JFrame("KaS-Simulator");
		
		var panel = new Main();
		ventana.setBackground(Color.black);
		ventana.getContentPane().add(panel, BorderLayout.CENTER);
		//SE NECESITA AÑADIR EL PANEL PARA PODER PONER LOS GRAFICOS
        ventana.getContentPane().setPreferredSize(new Dimension(600,600));
        ventana.pack();
        ventana.addKeyListener(klistener);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
			ventana.setIconImage(ImageIO.read(new File("C:\\Users\\Andrés\\git\\KaS-Asteroid\\KaS-Asteroid\\imgs\\gameIcon.png")));
		} catch (IOException e) {
			System.err.println("No se ha encontrado la imagen: gameIcon.png");
		}
        //.setLocationRelativeTo(null)
        //Hace que la pantalla aparezca centrada, pero hay que poderlo despues de especificar el tamaño y el .pack()
        //Pero antes del .setVisible()
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		for (Proyectil proyectil : proy) {
			g.setColor(proyectil.getColor());
			g.fillOval((int) proyectil.getPosX() - proyectil.getRadio()/2, (int) proyectil.getPosY(), proyectil.getRadio(), proyectil.getRadio());
		}
		
		if(enemys.size()>0) {
			for (Enemy enemy : enemys) {
				enemy.actualizarPos();
				g.setColor(enemy.getColor());
				g.fillOval((int) enemy.getPosX(),(int) enemy.getPosY(), enemy.getSize(), enemy.getSize());
			}
		}
		
		//Aquí es donde dibujamos al jugador
		g.setColor(Color.gray);
		g.fillPolygon(nucleo.calculoPos(0), nucleo.calculoPos(1), 4);
		g.setColor(Color.red);
		g.fillRect((int) nucleo.getPosX()-10, (int) nucleo.getPosY()+15, 20, 5);
		g.setColor(Color.green);
		g.fillRect((int) nucleo.getPosX()-10, (int) nucleo.getPosY()+15, nucleo.getHealth()*4, 5);
		
	}

}

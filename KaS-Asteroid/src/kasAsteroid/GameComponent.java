package kasAsteroid;
/**
 * Interfaz para todos los elementos que se mueven por la pantalla.
 * <br/>Incluye varios métodos, como calcular posición o colisiones.
 * @author Andrés
 *
 */
interface GameComponent {
	
	/**
	 * Actualiza la posición del componente teniendo en cuenta la velocidad actual del mismo y su rotación.
	 */
	public void actualizarPos();
}

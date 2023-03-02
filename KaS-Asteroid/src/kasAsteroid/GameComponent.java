package kasAsteroid;
/**
 * Interfaz para todos los elementos que se mueven por la pantalla.
 * <br/>Incluye varios m�todos, como calcular posici�n o colisiones.
 * @author Andr�s
 *
 */
interface GameComponent {
	
	/**
	 * Actualiza la posici�n del componente teniendo en cuenta la velocidad actual del mismo y su rotaci�n.
	 */
	public void actualizarPos();
}

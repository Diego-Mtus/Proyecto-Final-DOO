package org.udec.util.comandos;

/** * Interfaz funcional que define un comando que puede ser ejecutado.
 * Esta interfaz es utilizada para implementar el patrón de diseño Command.
 */
@FunctionalInterface
public interface Command {

    /** * Método que ejecuta el comando.
     * Este método debe ser implementado por las clases que representen un comando específico.
     */
    void execute();
}

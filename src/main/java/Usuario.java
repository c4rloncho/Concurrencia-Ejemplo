import java.util.ArrayList;
import java.util.List;

public class Usuario implements Runnable {
    private String nombre;
    private Biblioteca biblioteca;
    private List<Libro> libros = new ArrayList<Libro>();

    public Usuario(String nombre,Biblioteca biblioteca ) {
        this.nombre = nombre;
        this.biblioteca = biblioteca;
    }

    @Override
    public void run() {
        try{
            Libro libro = biblioteca.solicitarLibro("Libro 1");
            libros.add(libro);
            libro = biblioteca.solicitarLibro("Libro 3");
            libros.add(libro);
            libro = biblioteca.solicitarLibro("Libro 2");
            libros.add(libro);
            Thread.sleep(1000);
            biblioteca.devolverLibro("Libro 1");
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario: ").append(nombre).append("\n");
        sb.append("Libros solicitados: ");
        if (libros.isEmpty()) {
            sb.append("Ninguno");
        } else {
            for (Libro libro : libros) {
                sb.append(libro.getNombre()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Eliminar la última coma y espacio
        }
        return sb.toString();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Biblioteca {
    private List<Libro> libros;
    private ReentrantLock lock = new ReentrantLock();
    private Condition hayStock = lock.newCondition();

    public Biblioteca() {
        libros = new ArrayList<Libro>();
    }
    public List<Libro> getLibros() {
        return libros;
    }
    public Libro solicitarLibro(String titulo) throws InterruptedException {
        try{
           lock.lock();
           for(Libro libro: libros){
               if(libro.getNombre().equals(titulo)){
                   System.out.println("entramos");
                    while(libro.getStock() == 0){
                        System.out.println("no estos");
                        hayStock.await();
                    }
                    System.out.println("prestando libroi");
                    libro.prestarLibro();
                    return libro;
               }
           }
        }
        finally {
            lock.unlock();
        }
        return null;
    }
    public void agregarLibro(String titulo)throws InterruptedException{
        try{
            lock.lock();
            for(Libro libro: libros){
                if(libro.getNombre().equals(titulo)){
                    libro.agregarLibro();
                    hayStock.signal();
                    return;
                }
            }
            //agregamos nuevo libro
            Libro libro = new Libro(titulo);
            libros.add(libro);
            hayStock.signal();

        }
        finally {
            lock.unlock();
        }
    }
    public void devolverLibro(String titulo)throws InterruptedException{
        try {
            lock.lock();
            for(Libro libro: libros){
                if(libro.getNombre().equals(titulo)){
                    libro.agregarLibro();
                    hayStock.signal();
                }
            }

        }
        finally {

            lock.unlock();
        }
    }


}

public class main {
    public static void main(String[] args) throws InterruptedException {
        Biblioteca biblioteca = new Biblioteca();
        for(int i = 0 ; i<7;i++){
            biblioteca.agregarLibro("Libro "+i);
        }
        for(Libro libro: biblioteca.getLibros()){
            System.out.println(libro.getNombre());
        }

        Usuario user = new Usuario("carlos",biblioteca);
        Usuario user2 = new Usuario("Andres",biblioteca);
        Thread thread = new Thread(user);
        Thread thread1 = new Thread(user2);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(user);
        System.out.println(user2);
    }
}


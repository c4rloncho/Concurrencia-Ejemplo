public class Libro{
    private String nombre;
    private static int stock;


    public void agregarLibro(){
        stock++;
    }
    public Libro(String nombre){
        this.nombre = nombre;
        stock++;
    }
    public void prestarLibro(){
        if(stock>0){
            stock--;
        }

    }
    public String getNombre(){
        return nombre;
    }
    public int getStock(){
        return stock;
    }

}

import java.util.concurrent.TimeUnit;

public class Creador implements Runnable{
    private Buffer bufferInicial;
    private int cantidadCreados;
    private final long demora;

    /**
     * Constructor con parámetros
     * Inicializa las variables de instancia
     * @param bufferInicial Buffer donde enviar los datos creados.
     * @param demora Cuanto tiempo demora en crear un dato
     */
    public Creador(Buffer bufferInicial, long demora){
        this.bufferInicial = bufferInicial;
        this.cantidadCreados = 0;
        this.demora = demora;
    }

    public void run(){
        while(Consumidor.getDatosconsumidos() < Consumidor.getMaximasConsumisiones()){
            crear();
        }
    }

    /**
     * Crea un nuevo dato (demorando el tiempo correspondiente)
     * y se lo entrega al buffer. 
     */
    public void crear(){
        try {
            Dato nuevoDato= new Dato();
            TimeUnit.SECONDS.sleep(this.demora);
            this.cantidadCreados++;
            System.out.println("Creados: " + cantidadCreados + ' ' + Thread.currentThread().getName());
            this.bufferInicial.agregarDato(nuevoDato);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getCantidadCreados() {
        return cantidadCreados;
    }
}
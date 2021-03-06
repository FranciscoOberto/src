import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class Log implements Runnable {

    private final Buffer bufferInicial;
    private final Buffer bufferValidado;
    private PrintWriter writer;
    private final double demora;
    private final long startTime;

    public Log(Buffer bufferInicial, Buffer bufferValidado, double demora) {
        this.startTime = System.currentTimeMillis();
        this.bufferInicial = bufferInicial;
        this.bufferValidado = bufferValidado;
        this.demora = demora;
        try {
            writer = new PrintWriter("log.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep((long) this.demora);
                imprimir();
            }catch (InterruptedException e){
                System.out.println("\nFinalizacion del programa exitosa");
                imprimir();
                writer.close();
            }
        }

    }

    public void imprimir() {
        this.writer.println("Tiempo transcurrido: " + (System.currentTimeMillis() - startTime));
        this.writer.println("Cantidad de datos procesados: " + bufferValidado.getConsumidos());
        this.writer.println("Ocupacion Buffer Inical: " + bufferInicial.getCantidadDatos());
        this.writer.println("Ocupacion Buffer Validado: " + bufferValidado.getCantidadDatos());
    }

}

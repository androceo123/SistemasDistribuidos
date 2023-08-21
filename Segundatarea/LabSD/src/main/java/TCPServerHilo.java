import java.net.*;
import java.util.Iterator;
import java.io.*;

public class TCPServerHilo extends Thread {

    private Socket socket = null;

    ServidorTCP servidor;
    
    public TCPServerHilo(Socket socket, ServidorTCP servidor ) {
        super("TCPServerHilo");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
        	 String inputLine, outputLine;
        	
        	
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));
            out.println("Bienvenido!");
            String mensaje;

            while ((inputLine = in.readLine()) != null) {
            	 mensaje = inputLine.toUpperCase();
                System.out.println("Mensaje recibido: " + mensaje);
                
                //out.println(inputLine);
                
                //to-do: utilizar json
                if (inputLine.equals("Bye")) {
                    outputLine = "Usted apago el hilo";
                    break;
                    
                }else if (inputLine.equals("Terminar todo")) {
                    servidor.listening = false;
                    outputLine = "Usted apago todo";
                    break;
                    
                }else {
                	outputLine = mensaje ;
                               	
                	Iterator<String> iter = servidor.usuarios.iterator();
                	
                    while (iter.hasNext()) { 
                    	outputLine = outputLine + " - " + iter.next(); 
                    } 
                }
                
                
                out.println(outputLine);
            }
            out.close();
            in.close();
            socket.close();
            System.out.println("Finalizando Hilo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

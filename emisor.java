import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author jais1
 */
public class emisor {
    public static void main(String[] args){
        byte[] bsend = new byte[100];
        byte[] brecv = new byte[100];
        InetAddress server_addr = null;
        DatagramSocket s= null;
        DatagramPacket in= null;
        DatagramPacket out= null;
        int res;
        int num []= new int[3];
        try {
            s= new DatagramSocket();
            server_addr = InetAddress.getByName("192.168.100.175");
            num[0]=100;
            num[1]=300;
            num[2]=200;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream dos =new ObjectOutputStream(baos);
            dos.writeObject(num);
            bsend= baos.toByteArray();
            out = new DatagramPacket(bsend, bsend.length, server_addr,2500);
            s.send(out);
            in = new DatagramPacket(brecv, 100);
            s.receive(in);
            brecv = in.getData();
            ByteArrayInputStream bais = new ByteArrayInputStream(brecv);
            DataInputStream dis = new DataInputStream(bais);
            res= dis.readInt();
            System.out.println(" He recibido " + res);
        } catch (Exception e) {
            System.out.println("Error en emisor");
        }
    }
}

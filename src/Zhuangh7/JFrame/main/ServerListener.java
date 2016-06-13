package Zhuangh7.JFrame.main;

import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
import javax.swing.JOptionPane;  
  
public class ServerListener extends Thread {  
  
    @Override  
    public void run() {  
        //1-65535  
        try {  
            ServerSocket serverSocket = new ServerSocket(12345);  
            while (true) {  
                //accept()���������̣߳���Ҫ��һ���µ��̴߳���  
                Socket socket = serverSocket.accept();  
                //��������  
                MainClass.print("�пͻ������ӵ��˱�����12345�˿�");  
                //��socket���ݸ��µ��߳�  
                ChatSocket cs = new ChatSocket(socket);  
                cs.start();  
                ChatManager.getChatManager().add(cs);  
            }  
              
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
package Zhuangh7.JFrame.main;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;  
import java.net.Socket;  
  
public class ChatSocket extends Thread {  
      
    Socket socket;  
      
    public ChatSocket(Socket s){  
        this.socket = s;  
    }  
      
    public void out(String out) {  
        try {  
        	//PrintWriter os=new PrintWriter(socket.getOutputStream());
            socket.getOutputStream().write((out+"\n").getBytes("UTF-8"));  
        	//os.println(out);
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            MainClass.print("�Ͽ���һ���ͻ�������");  
            ChatManager.getChatManager().remove(this);  
            e.printStackTrace();  
        }  
    }  
  
    @Override  
    public void run() {  
    	if(ServerListener.Client_num<=ServerListener.max_client){
    		out("���Ѿ����ӵ�����������"); 
    		out("���ǵ�"+ServerListener.Client_num+"���ͻ�");
    		if(ServerListener.Client_num==1){
    			out("�ȴ��ͻ�2");
    			while(ServerListener.Client_num<2){
    				
    			}
    		}
    		else{
    			out("��ͻ�1���");
    		}
    		try {  
    			BufferedReader br = new BufferedReader(  
    					new InputStreamReader(  
    							socket.getInputStream(),"UTF-8"));  
    			String line = null;  
    			while ((line = br.readLine()) != null) {  
    				MainClass.print(line);  
    				ChatManager.getChatManager().publish(this, line);  
    			}  
    			
    			
    			
	            br.close();
	            MainClass.print("�Ͽ���һ���ͻ�������");  
	            ChatManager.getChatManager().remove(this);  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	        	MainClass.print("�Ͽ���һ���ͻ�������");  
	            ChatManager.getChatManager().remove(this);  
	            e.printStackTrace();  
	        }  
    	}
    	else{
    		out("������������");
    		try {
				socket.close();//�ر�����
				ChatManager.getChatManager().remove(this);  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }  
}  
package example2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) {
		// 필요한 객체를 저장할 변수 만들기
		ServerSocket serverSocket=null;
		Socket socket=null;
		InputStream is=null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			//5000 번 port 에서 클라이언트의 접속을 기다린다.
			serverSocket=new ServerSocket(5000);
			while(true){
				System.out.println("연결 요청 대기중...");
				//연결이 성공되면 클라이언트와 연결된 Socket 객체가 리턴
				socket=serverSocket.accept();
				//접속된 클라이언트의 ip 주소 얻어오기
				String clientIp=
						socket.getInetAddress().getHostAddress();
				System.out.println("clientIp:"+clientIp);
				
				is=socket.getInputStream();
				isr=new InputStreamReader(is);
				br=new BufferedReader(isr);
				// 클라이언트가 전송할 문자열을 줄단위로 읽어온다.
				String msg = br.readLine();
				System.out.println("msg : "+msg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(is!=null)is.close();
				if(isr!=null)isr.close();
				if(br!=null)br.close();
				if(serverSocket!=null)serverSocket.close();
				if(socket!=null)socket.close();
			}catch(Exception e){}
		}
		System.out.println("Server main() 메소드가 종료 됩니다.");
	}
}


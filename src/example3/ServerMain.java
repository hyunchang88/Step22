package example3;

import java.net.ServerSocket;
import java.net.Socket;

/*
 * 		클라이언트가 전송하는 파일을 파일시스템에서 저장하는 예제
 */
public class ServerMain {
	public static void main(String[] args) {
		// 필요한 객체를 담을 지역 변수 만들기
		ServerSocket serverSocket = null;
		try{
			// 5000번 port 에서 클라이언트의 Socket 접속을 대기할 객체
			serverSocket = new ServerSocket(5000);
			// 반복문 돌면서 접속 대기
			while(true){
				System.out.println("Socket 접속 대기중 ...");
				/*
				 * 		.accept() 메소드를 호출하면 블러킹 되어 있다가
				 * 		클라이언트가 Socket 접속을 해 오면 요청을 한 
				 * 		클라이언트와 연결된 Socket 객체의 참조값이 반환 된다.
				 */
				Socket socket = serverSocket.accept();
				// 새로운 스레드를 시작 시켜서 파일 다운로드 작업을 한다.
				new FileDownThread(socket).start();
				System.out.println("새로운 스레드 시작 시킴!");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(serverSocket!=null)
				try {
					serverSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	} // main()
}

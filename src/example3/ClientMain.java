package example3;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 * 		파일 시스템에서 파일을 읽어 들여서 Socket 통신을 이용해서
 * 		전송하는 예제
 */
public class ClientMain {
	public static void main(String[] args) {
		// 필요한 객체를 담을 지역변수 만들기
		Socket socket = null;
		OutputStream os=null;
		FileInputStream fis = null;
		try{
			// Socket 객체를 생성해서 Socket 접속한다.
			socket=new Socket("192.168.0.122", 5000); // 강사님 컴퓨터 IP 주소, 열어놓으실 포트번호
			// Socket 을 통해서 출력할 객체의 참조값 얻어오기
			os=socket.getOutputStream();
			// 파일에서 읽어들일 수 있는 객체의 참조값
			fis = new FileInputStream("c:/myFolder/myImage.jpg");
			// 파일에서 byte 데이터를 읽어 들일 수 있는 byte[] 객체
			byte[] buffer = new byte[1024];
			// 반복문 돌면서 파일에서 읽어들이고 동시에 출력하기
			while(true){
				int readedByte = fis.read(buffer);
				if(readedByte==-1)break; // 더이상 읽을게 없을때-1 이 됌. 그러면 반복문 탈출
				// byte[] 에 읽어온 만큼만 출력하기
				os.write(buffer, 0, readedByte); // 0번방에서 부터 읽은 갯수만큼 // write 니까 출력할 준비만
				os.flush(); // flush 하니까 출력함
			}
			System.out.println("파일 전송 성공");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(os!=null)os.close();
				if(fis!=null)fis.close();
				if(socket!=null)socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("메인 스레드가 종료됩니다.");
	} // main()
}

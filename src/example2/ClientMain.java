package example2;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
   public static void main(String[] args) {
	   // Scanner 객체를 이용해서 서버에 전송할 문자열을 입력 받는다.
	   Scanner scan = new Scanner (System.in);
	   System.out.println("서버로 전송할 메세지 입력 : ");
	   String msg = scan.nextLine();
	   
	   // 필요한 객체를 담을 변수 만들기
      Socket socket = null;
      OutputStream os = null;
      OutputStreamWriter osw = null;
      BufferedWriter bw = null;
      try{
         /*
          * new Socket("ip 주소", port 번호)
          * - 객체를 생성하는 시점에 연결이 요청되고
          * - 객체의 참조값이 리턴되면 Socket 연결이 성공한 것이다.
          */
         socket = new Socket("192.168.0.122", 5000); // (연결하려는 ip주소 , port)
         System.out.println("Socket 연결 성공!");
         // 서버에 출력할수 있는 객체의 참조값
         os = socket.getOutputStream();
         osw = new OutputStreamWriter(os);
         bw = new BufferedWriter(osw);
         // 문자열 출력
         bw.write(msg);
         bw.newLine(); // 개행기호 출력
         bw.flush(); // 방출
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{
        	 if(os!=null)os.close();
        	 if(osw!=null)osw.close();
        	 if(bw!=null)bw.close();
        	 if(socket != null)socket.close();
         }catch(Exception e){}
      }
      System.out.println("메인 메소드가 종료됩니다.");
   }
}

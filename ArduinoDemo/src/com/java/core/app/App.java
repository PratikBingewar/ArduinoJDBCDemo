package com.java.core.app;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.java.dao.DAO;
import com.java.dao.impl.DAOImpl;
import com.java.gsdfa.arduino.ArduinoCommunicator;
import com.java.gsdfa.arduino.ArduinoListener;

public class App{

	public static DAO dao;
	public static Integer prevDv = 0;
	public static Integer prevAv = 0;
	
	public static void main(String[] args) throws Exception {
		dao= new DAOImpl();
		dao.setconnection();
		ArduinoListenerImpl lstnImpl = new ArduinoListenerImpl();

		String port = "COM13";

		ArduinoCommunicator arduino = new ArduinoCommunicator(port, lstnImpl);
		Scanner scanner = new Scanner(new InputStreamReader(System.in));


		while(true) {
			System.out.println("Enter text: ");
			arduino.write(scanner.nextLine());
		}
	}
}

class ArduinoListenerImpl  implements ArduinoListener {
	
	DAO dao = App.dao;

	@Override
	public void getSerialData(String data) {
		
		System.out.println(data);
			/*StringTokenizer stkn = new StringTokenizer(data,"-");
			String ds = stkn.nextToken();
			String as = stkn.nextToken();
			try {
				Integer dv = Integer.parseInt(ds);
				Integer av = Integer.parseInt(as);
				if(App.prevDv != dv || App.prevAv != av) {
					dao.AddData(""+dv,""+av);
					App.prevDv = dv;
					App.prevAv = av;
				}
			}catch(Exception e) {
				
			}*/
	}
}		

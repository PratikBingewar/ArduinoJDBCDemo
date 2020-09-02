package com.java.gsdfa.arduino;

import java.io.IOException;
import java.io.OutputStream;
import java.net.PortUnreachableException;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

/**
 * This class communicate with Arduino using serial port
 * 
 * @author Gajendra Jore
 * @version 1.0
 */
public class ArduinoCommunicator {
	private SerialPort commPort = null;
	private ArduinoListener listener = null;
	private Scanner scanner = null;
	private OutputStream writter = null;

	public ArduinoCommunicator(String port, ArduinoListener lstnr) throws Exception {
		if(lstnr == null) {
			throw new Exception("ArduinoListener instance can't be null");
		}
		this.listener = lstnr;
		validateSerialPort(port);
		initCommPort();
		initInputOutputStream();
		start();
	}

	private void initInputOutputStream() throws Exception {
		if (this.commPort != null && this.commPort.openPort()) {
			scanner = new Scanner(this.commPort.getInputStream());
			writter = this.commPort.getOutputStream();
		} else {
			throw new Exception("Invalid COM port");
		}
	}

	private void start() {
		Thread thread = getThreadToReadData();
		thread.start();
	}

	private Thread getThreadToReadData() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				super.run();
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					callCallback(line);
				}
			}
		};
		return thread;
	}

	private void callCallback(String line) {
		this.listener.getSerialData(line);
	}
	
	public void write(String data) {
		try {
			this.writter.write(data.getBytes());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void stop() {
		if(this.scanner != null)	this.scanner = null;
		if(this.writter != null) {
			try {
				this.writter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (commPort != null)		this.commPort.closePort();
	}

	private void initCommPort() {
		commPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
	}

	private void validateSerialPort(String port) throws PortUnreachableException {
		commPort = SerialPort.getCommPort(port);
		if (commPort == null) {
			throw new PortUnreachableException();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.stop();
	}
}

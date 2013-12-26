package test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestTimer extends JFrame implements ActionListener, Runnable {
	private static TestTimer obj;
	private JButton btnStart;
	private JButton btnPause;
	private JButton btnResume;
	private JButton btnStop;
	private JLabel lblTime;
	private static Thread th;
	private long count;
	
	public TestTimer(){
		super("Ãë±í");
		btnStart = new JButton("¿ªÊ¼");
		btnPause = new JButton("ÔÝÍ£");
		btnResume = new JButton("¼ÌÐø");
		btnStop = new JButton("Í£Ö¹");
		lblTime = new JLabel("00:00:00.000");
		this.setLayout(new FlowLayout());
		this.add(btnStart);
		this.add(btnPause);
		this.add(btnResume);
		this.add(btnStop);
		this.add(lblTime);
		btnStart.addActionListener(this);
		btnPause.addActionListener(this);
		btnResume.addActionListener(this);
		btnStop.addActionListener(this);
		this.setSize(150, 200);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		obj = new TestTimer();
	}

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals("¿ªÊ¼")){
			th = new Thread(obj);
			count = 0;
			th.start();
		}
		else if(btn.getText().equals("ÔÝÍ£")){
			th.suspend();
		}
		else if(btn.getText().equals("¼ÌÐø")){
			th.resume();
		}
		else if(btn.getText().equals("Í£Ö¹")){
			th.stop();
		}
	}

	public void run() {
		while(true){
			int ms, seconds, minutes, hours;
			String msg = "";
			hours = (int)(count / 3600000);
			minutes = (int)((count - hours * 3600000) / 60000);
			seconds = (int)((count - hours * 3600000 - minutes * 60000) / 1000);
			ms = (int)(count % 1000);
			if(hours < 10){
				msg += "0" + hours + ":";
			}
			else{
				msg += hours + ":";
			}
			if(minutes < 10){
				msg += "0" + minutes + ":";
			}
			else{
				msg += minutes + ":";
			}
			if(seconds < 10){
				msg += "0" + seconds + ":";
			}
			else{
				msg += seconds + ":";
			}
			if(ms < 10){
				msg += "00" + ms;
			}
			else if(ms < 100){
				msg += "0" + ms;
			}
			else{
				msg += ms;
			}
			
			lblTime.setText(msg);
			count++;
			try {
				Thread.sleep(1);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

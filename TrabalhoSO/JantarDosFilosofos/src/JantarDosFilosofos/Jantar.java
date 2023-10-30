package JantarDosFilosofos;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.concurrent.Semaphore;

public class Jantar extends JFrame {
	private static final long serialVersionUID = 8531554653309568273L;
	private Image mesa, filosofo0, filosofo1, filosofo2, filosofo3, filosofo4;
	private Filosofo f0, f1, f2, f3, f4;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jantar frame = new Jantar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void paint(Graphics g){	
		Graphics2D graficos = (Graphics2D) g;
		
		graficos.drawImage(mesa, 0, 0, null);
		graficos.drawImage(filosofo0, 0, 0, null);
		graficos.drawImage(filosofo1, 0, 0, null);
		graficos.drawImage(filosofo2, 0, 0, null);
		graficos.drawImage(filosofo4, 0, 0, null);
		graficos.drawImage(filosofo3, 0, 0, null);
	}
	
	public void jantar_WindowDestroy(Object target) {
		System.exit(0);
	}
	
	public Jantar() {
		setTitle("Jantar dos Fil\u00F3sofos");;
		ImageIcon referencia = new ImageIcon("src\\Imagens\\Mesa.png");
		mesa = referencia.getImage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 1024);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		referencia = new ImageIcon("src\\Imagens\\mesa.png");
		

		Semaphore[] garfosSem = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            garfosSem[i] = new Semaphore(1);
        }
		
		f0 = new Filosofo(0, this, garfosSem);
		f1 = new Filosofo(1, this, garfosSem);
		f2 = new Filosofo(2, this, garfosSem);
		f3 = new Filosofo(3, this, garfosSem);
		f4 = new Filosofo(4, this, garfosSem);
		
		f0.start();
		f1.start();
		f2.start();
		f3.start();
		f4.start();
	}
	
	public void SetInfo(int chave, eEstadoDoFilosofo estado) {
		
		if (chave == 0) {		
			if (estado == eEstadoDoFilosofo.PENSANDO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo0Pensando.png");		
				filosofo0 = referencia.getImage();
				
			} else if (estado == eEstadoDoFilosofo.FAMINTO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo0Faminto.png");		
				filosofo0 = referencia.getImage();
				
			} else if (estado == eEstadoDoFilosofo.COM_DOIS_GARFOS) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo0Comendo.png");		
				filosofo0 = referencia.getImage();				
			}
		}
		else if (chave == 1) {
			if (estado == eEstadoDoFilosofo.PENSANDO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo1Pensando.png");		
				filosofo1 = referencia.getImage();
			
			} else if (estado == eEstadoDoFilosofo.FAMINTO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo1Faminto.png");		
				filosofo1 = referencia.getImage();
			

			} else if (estado == eEstadoDoFilosofo.COM_DOIS_GARFOS) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo1Comendo.png");		
				filosofo1 = referencia.getImage();
			}
		}
		else if (chave == 2) {
			if (estado == eEstadoDoFilosofo.PENSANDO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo2Pensando.png");		
				filosofo2 = referencia.getImage();
			
			} else if (estado == eEstadoDoFilosofo.FAMINTO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo2Faminto.png");		
				filosofo2 = referencia.getImage();
			

			} else if (estado == eEstadoDoFilosofo.COM_DOIS_GARFOS) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo2Comendo.png");		
				filosofo2 = referencia.getImage();
			}
		}
		else if (chave == 3) {
			if (estado == eEstadoDoFilosofo.PENSANDO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo3Pensando.png");		
				filosofo3 = referencia.getImage();
			
			} else if (estado == eEstadoDoFilosofo.FAMINTO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo3Faminto.png");		
				filosofo3 = referencia.getImage();
			

			} else if (estado == eEstadoDoFilosofo.COM_DOIS_GARFOS) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo3Comendo.png");		
				filosofo3 = referencia.getImage();
			}
		}
		else if (chave == 4) {
			if (estado == eEstadoDoFilosofo.PENSANDO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo4Pensando.png");		
				filosofo4 = referencia.getImage();
			
			} else if (estado == eEstadoDoFilosofo.FAMINTO) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo4Faminto.png");		
				filosofo4 = referencia.getImage();
			

			} else if (estado == eEstadoDoFilosofo.COM_DOIS_GARFOS) {
				ImageIcon referencia = new ImageIcon("src\\Imagens\\imgFilosofo4Comendo.png");		
				filosofo4 = referencia.getImage();
			}
		}
		
		repaint();
	}
	
	public void start() {
	}

	public void stop() {
	}

	public void destroy() {
	}
}

package JantarDosFilosofos;

import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
	private int filosofo;
	private Jantar jantar;
	private Semaphore garfos[];

	public Filosofo(int chave, Jantar jantar, Semaphore[] garfos) {
		this.filosofo = chave;		
		this.jantar = jantar;
		this.garfos = garfos;
	}

	public int getChave() { 
		return filosofo;
	}

	public void setStatus(eEstadoDoFilosofo estado) {
		jantar.SetInfo(filosofo, estado);
	}

	private void pensando() { 
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	private void comendo() { 
		try {
			Thread.sleep(2500);
		} catch (Exception e) {
		}
	}
	 
	public void run() {
        while (true) {
            setStatus(eEstadoDoFilosofo.PENSANDO);
            pensando();
            setStatus(eEstadoDoFilosofo.FAMINTO);
            try {
                garfos[getChave()].acquire();
                garfos[(getChave() + 1) % 5].acquire();
                setStatus(eEstadoDoFilosofo.COM_DOIS_GARFOS);
                comendo();
                garfos[getChave()].release();
                garfos[(getChave() + 1) % 5].release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

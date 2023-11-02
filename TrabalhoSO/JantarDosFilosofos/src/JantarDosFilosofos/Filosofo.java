package JantarDosFilosofos;

import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
	private int filosofo;
	private Jantar jantar;
	private Semaphore garfos[];
	private eEstadoDoFilosofo estado;

	public Filosofo(int chave, Jantar jantar, Semaphore[] garfos) {
		this.filosofo = chave;		
		this.jantar = jantar;
		this.garfos = garfos;
	}

	public int getChave() { 
		return filosofo;
	}

	public void setStatus(eEstadoDoFilosofo estado) {
		this.estado = estado;
		jantar.SetInfo(filosofo, estado);
	}

	private void pensando() { 
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
		}
	}

	private void comendo() { 
		try {
			Thread.sleep(12500);
		} catch (Exception e) {
		}
	}
	 
	public void run() {
        while (true) {
			if(this.estado != eEstadoDoFilosofo.FAMINTO){
				setStatus(eEstadoDoFilosofo.PENSANDO);
            	pensando();
            	setStatus(eEstadoDoFilosofo.FAMINTO);
			}     
            try {
                garfos[getChave()].acquire();
				if(garfos[(getChave() + 1) % 5].tryAcquire()) //Tenta pegar o garfo da direita
					setStatus(eEstadoDoFilosofo.COMENDO); //Se conseguir come
                else{
					garfos[getChave()].release(); //Se não conseguir devolve o garfo da esquerda e volta pro inicio do while com estado FAMINTO
					continue;
				}	
                comendo();
                garfos[getChave()].release();
                garfos[(getChave() + 1) % 5].release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

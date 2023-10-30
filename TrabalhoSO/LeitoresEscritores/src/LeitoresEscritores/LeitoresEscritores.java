package LeitoresEscritores;

import java.util.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

class LeitoresEscritores {
    private int leitores;
    private int escritoresEsperando;
    private Semaphore escritor;
    private Semaphore leitorMutex;
    private Semaphore escritorMutex;

    public LeitoresEscritores() {
        leitores = 0;
        escritoresEsperando = 0;
        escritor = new Semaphore(1);
        leitorMutex = new Semaphore(1);
        escritorMutex = new Semaphore(1);
    }

    public void iniciarLeitura() throws InterruptedException {
        leitorMutex.acquire();
        leitores++;
        if (leitores == 1) {
            escritorMutex.acquire();
        }
        leitorMutex.release();
    }

    public void terminarLeitura() throws InterruptedException {
        leitorMutex.acquire();
        leitores--;
        if (leitores == 0) {
            escritorMutex.release();
        }
        leitorMutex.release();
    }

    public void iniciarEscrita() throws InterruptedException {
        escritoresEsperando++;
        escritorMutex.acquire();
        escritoresEsperando--;
        escritor.acquire();
    }

    public void terminarEscrita() {
        escritor.release();
        escritorMutex.release();
    }
}


class Leitor implements Runnable {
    private LeitoresEscritores leitoresEscritores;
    private int id;

    public Leitor(LeitoresEscritores leitoresEscritores, int id) {
        this.leitoresEscritores = leitoresEscritores;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                leitoresEscritores.iniciarLeitura();
                System.out.println("Leitor " + id + " está lendo.");
                Thread.sleep(1000); // Simula a leitura
                leitoresEscritores.terminarLeitura();
                System.out.println("Leitor " + id + " está utilizando as informações adquiridas.");
                Thread.sleep(1000); // Espera um pouco antes de ler novamente
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Escritor implements Runnable {
    private LeitoresEscritores leitoresEscritores;
    private int id;

    public Escritor(LeitoresEscritores leitoresEscritores, int id) {
        this.leitoresEscritores = leitoresEscritores;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                leitoresEscritores.iniciarEscrita();
                System.out.println("Escritor " + id + " está escrevendo.");
                Thread.sleep(2000); // Simula a escrita
                leitoresEscritores.terminarEscrita();
                System.out.println("Escritor " + id + " está pensando no que escrever.");
                Thread.sleep(10000); // Espera um pouco antes de escrever novamente
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



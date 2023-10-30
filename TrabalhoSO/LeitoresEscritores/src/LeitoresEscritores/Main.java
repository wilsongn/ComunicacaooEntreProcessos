package LeitoresEscritores;

public class Main {
    public static void main(String[] args) {
        LeitoresEscritores leitoresEscritores = new LeitoresEscritores();

        Thread[] leitores = new Thread[5];
        Thread[] escritores = new Thread[2];

        for (int i = 0; i < leitores.length; i++) {
            leitores[i] = new Thread(new Leitor(leitoresEscritores, i + 1));
            leitores[i].start();
        }

        for (int i = 0; i < escritores.length; i++) {
            escritores[i] = new Thread(new Escritor(leitoresEscritores, i + 1));
            escritores[i].start();
        }
    }
}

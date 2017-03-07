import java.util.Random;

public class Host {

  private Maquina mqPadrao;
  private int numeroDeMaquinas;
  private Maquina[] maquinas;



  public Host(Maquina mqPadrao, int numeroDeMaquinas) {
    super();
    this.mqPadrao = mqPadrao;
    this.numeroDeMaquinas = numeroDeMaquinas;
    maquinas = inicializaMaquinas(mqPadrao);
  }

/*Inicializa maquinas do Host com a configuracao (memoria e CPU) padrao passada no construtor */
  private Maquina[] inicializaMaquinas(Maquina mqPadrao) {
    Maquina[] maquinas = new Maquina[numeroDeMaquinas];
    for (int i = 0; i < maquinas.length; i++) {
      maquinas[i] = new Maquina(mqPadrao.getCpu(), mqPadrao.getMemo());
    }
    return maquinas;
  }

/* Metodo para alocacao de maquina utilizando o algoritmo First Fit, onde o codigo percorre toda a lista 
de mequinas de host ate que encontre a primeira maquina de host que caiba (memoria e CPU) a VM solicitada */
  public boolean aloqueFF(Maquina maquina) {
    int mqIndex = 0;
    Maquina mqAtaul;
    while (mqIndex < numeroDeMaquinas) {
      mqAtaul = maquinas[mqIndex];
      if (mqAtaul.alocar(maquina)) {
        return true;
      } else {
        mqIndex++;
      }
    }
    return false;
  }


/* Metodo para alocacao de maquina utilizando o algoritmo Best Fit, onde o codigo percorre toda a lista de maquinas 
de host para encontrar a maquina de host que caiba (memoria e CPU) a VM solicitada com menor fragmentacao*/

  public boolean aloqueBF(Maquina maquina) {

    final int MEMO = 0;
    final int CPU = 1;

	/* A melhor aloca��o ser� na m�quinde host onde se pode minimizar a fragmentacao */
    double[] melhorAlocacao = new double[2];
    melhorAlocacao[MEMO] = Integer.MAX_VALUE;
    melhorAlocacao[CPU] = Integer.MAX_VALUE;
    int bestFitIndex = -1;

    for (int i = 0; i < numeroDeMaquinas; i++) {
	/*Verifica se a VM solicitada cabe a maquina atual do host que esta sendo visitada */
      if ((maquinas[i].getMemoRestante() >= maquina.getMemo())
          && (maquinas[i].getCpuRestante() >= maquina.getCpu())) {
	
	/* Se a fragmentacao da maquina de host atual for menor que a fragmentacao da maquina 
	com melhor alocacao ate o momento, atualiza-se os valores de melhor alocacao e indice */
        if (((maquinas[i].getMemoRestante() - maquina.getMemo()) < melhorAlocacao[MEMO])
            && ((maquinas[i].getCpuRestante() - maquina.getCpu()) < melhorAlocacao[CPU])) {
          melhorAlocacao[MEMO] = maquinas[i].getMemoRestante() - maquina.getMemo();
          melhorAlocacao[CPU] = maquinas[i].getCpuRestante() - maquina.getCpu();
          bestFitIndex = i;
        }
      }
    }

    /* Caso o indice da melhor alocacao nao seja diferente de -1 nao houve alocacao */
    if (bestFitIndex != -1) {
      return maquinas[bestFitIndex].alocar(maquina);
    } else {
      return false;
    }
  }

/* Metodo para alocacao de maquina utilizando o algoritmo Random Fit, 
onde o codigo acessa aleatoriamente uma maquina de host e verifica se a VM pode ser alocada nela*/

  public boolean aloqueRF(Maquina maquina) {
    Random rn = new Random();
    int randomIndex = rn.nextInt(numeroDeMaquinas) + 0;
    if (maquinas[randomIndex].alocar(maquina)) {
      return true;
    }

    return false;
  }

/* Calcular a fragmentacao total do host */
  public double calcularFrag() {
    double frag = 0;
    for (int i = 0; i < maquinas.length; i++) {
      frag += maquinas[i].getFragCpu();
    }
    return frag;
  }

  public Maquina[] getMaquinas() {
    return maquinas;
  }

}

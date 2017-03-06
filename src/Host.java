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

  private Maquina[] inicializaMaquinas(Maquina mqPadrao) {
    Maquina[] maquinas = new Maquina[numeroDeMaquinas];
    for (int i = 0; i < maquinas.length; i++) {
      maquinas[i] = new Maquina(mqPadrao.getCpu(), mqPadrao.getMemo());
    }
    return maquinas;
  }

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

  public boolean aloqueBF(Maquina maquina) {

    final int MEMO = 0;
    final int CPU = 1;

    double[] melhorAlocacao = new double[2];
    melhorAlocacao[MEMO] = Integer.MAX_VALUE;
    melhorAlocacao[CPU] = Integer.MAX_VALUE;
    int bestFitIndex = -1;

    for (int i = 0; i < numeroDeMaquinas; i++) {
      if ((maquinas[i].getMemoRestante() >= maquina.getMemo())
          && (maquinas[i].getCpuRestante() >= maquina.getCpu())) {
        if (((maquinas[i].getMemoRestante() - maquina.getMemo()) < melhorAlocacao[MEMO])
            && ((maquinas[i].getCpuRestante() - maquina.getCpu()) < melhorAlocacao[CPU])) {
          melhorAlocacao[MEMO] = maquinas[i].getMemoRestante() - maquina.getMemo();
          melhorAlocacao[CPU] = maquinas[i].getCpuRestante() - maquina.getCpu();
          bestFitIndex = i;
        }
      }
    }

    if (bestFitIndex != -1) {
      return maquinas[bestFitIndex].alocar(maquina);
    } else {
      return false;
    }
  }

  public boolean aloqueRF(Maquina maquina) {
    Random rn = new Random();
    int randomIndex = rn.nextInt(numeroDeMaquinas) + 0;
    if (maquinas[randomIndex].alocar(maquina)) {
      return true;
    }

    return false;
  }

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

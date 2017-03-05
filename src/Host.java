
public class Host {

  private Maquina mqPadrao;
  private int numeroDeMaquinas;
  private Maquina[] maquinas;



  public Host( Maquina mqPadrao,
      int numeroDeMaquinas) {
    super();
    this.mqPadrao = mqPadrao;
    this.numeroDeMaquinas = numeroDeMaquinas;
    maquinas = inicializaMaquinas(mqPadrao);
  }

  private Maquina[] inicializaMaquinas(Maquina mqPadrao) {
    Maquina[] maquinas = new Maquina[numeroDeMaquinas];
    for (int i = 0; i < maquinas.length; i++) {
      maquinas [i] = new Maquina(mqPadrao.getCpu(), mqPadrao.getMemo());
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
    int mqIndex = -1;
    double[] melhorAlocacao = new double[2];
    //melhorAlocacao[0] ->  mem melhorAlocacao[1] -> CPU
    melhorAlocacao[0] = -1;
    melhorAlocacao[1] = -1;

    for (int i = 0; i < numeroDeMaquinas; i++) {
      double[] alocacao = maquinas[i].alocar_BF(maquina);
      if (alocacao[0]> 0 && alocacao[1]>0){
        if (melhorAlocacao[0]==-1 && melhorAlocacao[0]==-1){
          melhorAlocacao[0] = alocacao[0];
          melhorAlocacao[1] = alocacao[0];
        } else if (alocacao[0]< melhorAlocacao[0] && alocacao[1]<melhorAlocacao[1]){
          melhorAlocacao[0] = alocacao[0];
          melhorAlocacao[1] = alocacao[0];
        }
    }

    if (melhorAlocacao[0]!=-1 && melhorAlocacao[0]!=-1){
        return true
    }
    return false;
  }


  public boolean aloqueRF(Maquina maquina) {
    Random rn = new Random();
    int range = numeroDeMaquinas - 0 + 1;
    int randomIndex =  rn.nextInt(range) + minimum;
    if (mqAtaul.alocar(maquina)) {
      return true;
    }

    return false;
  }

  public double calcularFrag(){
    double frag=0;
    for (int i = 0; i < maquinas.length; i++) {
      frag += maquinas[i].getFragCpu();
    }
    return frag;
  }

}

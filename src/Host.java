
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
  
  public double calcularFrag(){
    double frag=0;
    for (int i = 0; i < maquinas.length; i++) {
      frag += maquinas[i].getFragCpu();
    }
    return frag;
  }



}

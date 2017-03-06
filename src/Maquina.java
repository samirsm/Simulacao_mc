import java.util.ArrayList;

public class Maquina {

  private double memo;
  private double cpu;
  private double cpuEmUso;
  private double memoEmUso;
  private int requisicao;
  ArrayList<Maquina> maquinas = new ArrayList<>();
  
  

  public Maquina(double memo, double cpu) {
    this.memo = memo;
    this.cpu = cpu;
    cpuEmUso = 0;
    memoEmUso = 0;
  
  }
  
  public int getRequisicao() {
    return requisicao;
  }

  public Maquina(int requisicao, double memo, double cpu) {
    this.requisicao = requisicao;
    this.memo = memo;
    this.cpu = cpu;
    cpuEmUso = 0;
    memoEmUso = 0;
  
  }

  public double getMemo() {
    return memo;
  }


  public double getCpuEmUso() {
    return cpuEmUso;
  }
  
  public double getMemoEmUso() {
    return memoEmUso;
  }


  public double getMemoRestante() {
    return memo - memoEmUso;
  }

  public double getCpuRestante() {
    return cpu-cpuEmUso;
  }

  public double getCpu() {
    return cpu;
  }
  

  public boolean alocar(Maquina maquina){
    if(maquina.memo <= getMemoRestante() && maquina.cpu <= getCpuRestante()) {
      memoEmUso += maquina.memo;
      cpuEmUso += maquina.cpu;
      maquinas.add(maquina);
      return true;
    }
    else return false;
  }
  
  public ArrayList<Maquina> getMaquinas() {
    return maquinas;
  }

  public double getFragMemo(){
    return memo - memoEmUso;
  }
  
  public double getFragCpu(){
    return cpu - cpuEmUso;
  }
  
  public String toString(){
    return "resta: " + getCpuRestante();
  }
  
}

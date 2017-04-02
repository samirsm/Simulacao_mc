import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.SingleSelectionModel;

public class Simulador {

	public static void main(String[] args) {

		FileWriter arq1;
		FileWriter arq2;

		// configurações do host pequeno
		int numeroDemaquinas_hostP = 10000;
		double cpuHost_hostP = 0.6;
		double memoHost_hostP = 0.6;
		// Capacidade total de 12000 de CPU e mem

		// configurações do host grande
		int numeroDemaquinas_hostG = 6000;
		double cpuHost_hostG = 1;
		double memoHost_hostG = 1;
		// Capacidade total de 12000 de CPU e mem

		int requisicoes = 150000;

		try {
			arq1 = new FileWriter("Simulacao_VMP.txt");

			PrintWriter gravarArq1 = new PrintWriter(arq1);

			gravarArq1.println("############### VMS PEQUENAS #############");
			gravarArq1.println("(CPU [0 - 0.1] e  RAM [0 - 0.1])");
			gravarArq1.println("##########################################");

			for (int i = 0; i < 20; i++) {

				/* VMS PEQUENAS */

				// configurações das VMs pequenas
				double confMinima_VMP = 0.00001;
				double confMaxima_VMP = 0.1;

				// Hosts pequenos
				int reqAceitasFF_hostP = 0;
				int reqNegadasFF_hostP = 0;

				int reqAceitasBF_hostP = 0;
				int reqNegadasBF_hostP = 0;

				// Desconsiderar o algoritmo RF int reqAceitasRF_hostP = 0; int
				// reqNegadasRF_hostP = 0;

				// Hosts grandes
				int reqAceitasFF_hostG = 0;
				int reqNegadasFF_hostG = 0;

				int reqAceitasBF_hostG = 0;
				int reqNegadasBF_hostG = 0;

				// Desconsiderar o algoritmo RF int reqAceitasRF_hostG = 0; int
				// reqNegadasRF_hostG = 0;

				Host hostFF_hostP = new Host(new Maquina(cpuHost_hostP, memoHost_hostP), numeroDemaquinas_hostP);
				Host hostBF_hostP = new Host(new Maquina(cpuHost_hostP, memoHost_hostP), numeroDemaquinas_hostP);
				// Host hostRF_hostP = new Host(new Maquina(cpuHost_hostP,
				// memoHost_hostP), numeroDemaquinas_hostP);

				Host hostFF_hostG = new Host(new Maquina(cpuHost_hostG, memoHost_hostG), numeroDemaquinas_hostG);
				Host hostBF_hostG = new Host(new Maquina(cpuHost_hostG, memoHost_hostG), numeroDemaquinas_hostG);
				// Host hostRF_hostG = new Host(new Maquina(cpuHost_hostG,
				// memoHost_hostG), numeroDemaquinas_hostG);

				// Requisições para VMS Pequenas
				for (int y = 0; y < requisicoes; y++) {

					/*
					 * Para cada requisição é gerada uma configuração (memória e
					 * CPU) de VM randômica entre os intervalos da menor
					 * configuração possível e a maior configuração possível, a
					 * mesma VM será alocada nos diferentes hosts com os
					 * diferentes algoritmos de alocação
					 */
					Random r = new Random();
					double randomValue = confMinima_VMP + (confMaxima_VMP - confMinima_VMP) * r.nextDouble();

					Maquina maquina = new Maquina(i, randomValue, randomValue);
					// Maquina maquina = new Maquina(randomValue,randomValue);
					// Maquinas com mesma configuração para poder avaliar os
					// diferentes
					// hosts e algoritmos

					// Hosts pequenos
					if (hostFF_hostP.aloqueFF(maquina)) {
						reqAceitasFF_hostP++;
					} else {
						reqNegadasFF_hostP++;
					}

					if (hostBF_hostP.aloqueBF(maquina)) {
						reqAceitasBF_hostP++;
					} else {
						reqNegadasBF_hostP++;
					}

					// Desconsiderar o algoritmo RF
					// if(hostRF_hostP.aloqueRF(maquina)){
					// reqAceitasRF_hostP ++; }else{ reqNegadasRF_hostP ++; }

					// Hosts grandes
					if (hostFF_hostG.aloqueFF(maquina)) {
						reqAceitasFF_hostG++;
					} else {
						reqNegadasFF_hostG++;
					}

					if (hostBF_hostG.aloqueBF(maquina)) {
						reqAceitasBF_hostG++;
					} else {
						reqNegadasBF_hostG++;
					}

					// Desconsiderar o algoritmo RF
					// if(hostRF_hostG.aloqueRF(maquina)){
					// reqAceitasRF_hostG ++; }else{ reqNegadasRF_hostG ++; }

				}

				// Hosts pequenos

				gravarArq1.println("***************** Repetição " + i + " *****************");

				gravarArq1.println("Host pequeno");
				gravarArq1.println("requisicoes negadas pelo código Fist Fit: " + reqNegadasFF_hostP);
				gravarArq1.println("Fragmentacao do código First Fit: " + hostFF_hostP.calcularFrag());
				/*
				 * for (Maquina mq : hostFF_hostP.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				gravarArq1.println("-------------------------------------------------------");

				gravarArq1.println("requisicoes negadas pelo código Best Fit: " + reqNegadasBF_hostP);
				gravarArq1.println("Fragmentacao do código Best Fit: " + hostBF_hostP.calcularFrag());
				/*
				 * for (Maquina mq : hostBF_hostP.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				/*
				 * Desconsiderar o algoritmo RF System.out.
				 * println("requisicoes negadas pelo código Random Fit: " +
				 * reqNegadasRF_hostP);
				 * System.out.println("Fragmentacao do código Random Fit: " +
				 * hostRF_hostP.calcularFrag());
				 */
				gravarArq1.println("--------------------------//---------------------------");

				// Hosts grandes
				gravarArq1.println("Host grande");
				gravarArq1.println("requisicoes negadas pelo código Fist Fit: " + reqNegadasFF_hostG);
				gravarArq1.println("Fragmentacao do código First Fit: " + hostFF_hostG.calcularFrag());

				/*
				 * for (Maquina mq : hostFF_hostG.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				gravarArq1.println("-------------------------------------------------------");

				gravarArq1.println("requisicoes negadas pelo código Best Fit: " + reqNegadasBF_hostG);
				gravarArq1.println("Fragmentacao do código Best Fit: " + hostBF_hostG.calcularFrag());
				/*
				 * for (Maquina mq : hostBF_hostG.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				/*
				 * Desconsiderar o algoritmo RF System.out.
				 * println("requisicoes negadas pelo código Random Fit: " +
				 * reqNegadasRF_hostG);
				 * System.out.println("Fragmentacao do código Random Fit: " +
				 * hostRF_hostG.calcularFrag());
				 */

			}
			arq1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			arq2 = new FileWriter("Simulacao_VMG.txt");
			PrintWriter gravarArq2 = new PrintWriter(arq2);

			gravarArq2.println("############### VMS GRANDES #############");
			gravarArq2.println("(CPU [0.5 - 0.6] e  RAM [0.5 - 0.6])");
			gravarArq2.println("#########################################");

			for (int z = 0; z < 20; z++) {

				/* VMS GRANDES */
				// configurações das VMs grandes

				double confMinima_VMG = 0.5;
				double confMaxima_VMG = 0.6;

				int reqAceitasFF_hostP_VMG = 0;
				int reqNegadasFF_hostP_VMG = 0;

				int reqAceitasBF_hostP_VMG = 0;
				int reqNegadasBF_hostP_VMG = 0;

				/*
				 * Desconsiderar o algoritmo RF int reqAceitasRF_hostP = 0; int
				 * reqNegadasRF_hostP = 0;
				 */

				// Hosts grandes
				int reqAceitasFF_hostG_VMG = 0;
				int reqNegadasFF_hostG_VMG = 0;

				int reqAceitasBF_hostG_VMG = 0;
				int reqNegadasBF_hostG_VMG = 0;

				/*
				 * Desconsiderar o algoritmo RF int reqAceitasRF_hostG = 0; int
				 * reqNegadasRF_hostG = 0;
				 */

				Host hostFF_hostP_VMG = new Host(new Maquina(cpuHost_hostP, memoHost_hostP), numeroDemaquinas_hostP);
				Host hostBF_hostP_VMG = new Host(new Maquina(cpuHost_hostP, memoHost_hostP), numeroDemaquinas_hostP);
				// Host hostRF_hostP = new Host(new Maquina(cpuHost_hostP,
				// memoHost_hostP), numeroDemaquinas_hostP);

				Host hostFF_hostG_VMG = new Host(new Maquina(cpuHost_hostG, memoHost_hostG), numeroDemaquinas_hostG);
				Host hostBF_hostG_VMG = new Host(new Maquina(cpuHost_hostG, memoHost_hostG), numeroDemaquinas_hostG);
				// Host hostRF_hostG = new Host(new Maquina(cpuHost_hostG,
				// memoHost_hostG), numeroDemaquinas_hostG);

				/* end VMS grandes */

				// Requisições para VMS Pequenas

				for (int i = 0; i < requisicoes; i++) {

					/*
					 * Para cada requisição é gerada uma configuração (memória e
					 * CPU) de VM randômica entre os intervalos da menor
					 * configuração possível e a maior configuração possível, a
					 * mesma VM será alocada nos diferentes hosts com os
					 * diferentes algoritmos de alocação
					 */
					Random r = new Random();
					double randomValue = confMinima_VMG + (confMaxima_VMG - confMinima_VMG) * r.nextDouble();

					Maquina maquina = new Maquina(i, randomValue, randomValue);
					// Maquina maquina = new Maquina(randomValue,randomValue);
					// Maquinas com mesma configuração para poder avaliar os
					// diferentes
					// hosts e algoritmos

					// Hosts pequenos
					if (hostFF_hostP_VMG.aloqueFF(maquina)) {
						reqAceitasFF_hostP_VMG++;
					} else {
						reqNegadasFF_hostP_VMG++;
					}

					if (hostBF_hostP_VMG.aloqueBF(maquina)) {
						reqAceitasBF_hostP_VMG++;
					} else {
						reqNegadasBF_hostP_VMG++;
					}

					/*
					 * Desconsiderar o algoritmo RF
					 * if(hostRF_hostP.aloqueRF(maquina)){ reqAceitasRF_hostP
					 * ++; }else{ reqNegadasRF_hostP ++; }
					 */
					// Hosts grandes
					if (hostFF_hostG_VMG.aloqueFF(maquina)) {
						reqAceitasFF_hostG_VMG++;
					} else {
						reqNegadasFF_hostG_VMG++;
					}

					if (hostBF_hostG_VMG.aloqueBF(maquina)) {
						reqAceitasBF_hostG_VMG++;
					} else {
						reqNegadasBF_hostG_VMG++;
					}

					/*
					 * Desconsiderar o algoritmo RF
					 * if(hostRF_hostG.aloqueRF(maquina)){ reqAceitasRF_hostG
					 * ++; }else{ reqNegadasRF_hostG ++; }
					 */

				}

				gravarArq2.println("****************** Repetição " + z + " *****************");

				gravarArq2.println("Host pequeno");
				gravarArq2.println("requisicoes negadas pelo código Fist Fit: " + reqNegadasFF_hostP_VMG);
				gravarArq2.println("Fragmentacao do código First Fit: " + hostFF_hostP_VMG.calcularFrag());
				/*
				 * for (Maquina mq : hostFF_hostP.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				gravarArq2.println("-------------------------------------------------------");

				gravarArq2.println("requisicoes negadas pelo código Best Fit: " + reqNegadasBF_hostP_VMG);
				gravarArq2.println("Fragmentacao do código Best Fit: " + hostBF_hostP_VMG.calcularFrag());
				/*
				 * for (Maquina mq : hostBF_hostP.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				/*
				 * Desconsiderar o algoritmo RF System.out.
				 * println("requisicoes negadas pelo código Random Fit: " +
				 * reqNegadasRF_hostP);
				 * System.out.println("Fragmentacao do código Random Fit: " +
				 * hostRF_hostP.calcularFrag());
				 */
				gravarArq2.println("--------------------------//---------------------------");

				// Hosts grandes
				gravarArq2.println("Host grande");
				gravarArq2.println("requisicoes negadas pelo código Fist Fit: " + reqNegadasFF_hostG_VMG);
				gravarArq2.println("Fragmentacao do código First Fit: " + hostFF_hostG_VMG.calcularFrag());

				/*
				 * for (Maquina mq : hostFF_hostG.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				gravarArq2.println("-------------------------------------------------------");

				gravarArq2.println("requisicoes negadas pelo código Best Fit: " + reqNegadasBF_hostG_VMG);
				gravarArq2.println("Fragmentacao do código Best Fit: " + hostBF_hostG_VMG.calcularFrag());
				/*
				 * for (Maquina mq : hostBF_hostG.getMaquinas()) { String show =
				 * ""; for (Maquina mv : mq.getMaquinas()) { show +=
				 * mv.getRequisicao() + "-" + mv.getCpu() + ", "; }
				 * System.out.println(show); }
				 */

				gravarArq2.println("-------------------------------------------------------");

				/*
				 * Desconsiderar o algoritmo RF System.out.
				 * println("requisicoes negadas pelo código Random Fit: " +
				 * reqNegadasRF_hostG);
				 * System.out.println("Fragmentacao do código Random Fit: " +
				 * hostRF_hostG.calcularFrag());
				 */
			}

			arq2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

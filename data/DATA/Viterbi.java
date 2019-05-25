package DATA;

public class Viterbi {

	/**
	 * ���HMMģ��
	 * 
	 * @param obs
	 *            �۲�����
	 * @param states
	 *            ��״̬
	 * @param start_p
	 *            ��ʼ���ʣ���״̬��
	 * @param trans_p
	 *            ת�Ƹ��ʣ���״̬��
	 * @param emit_p
	 *            ������� ����״̬����Ϊ��״̬�ĸ��ʣ�
	 * @return ����ܵ�����
	 */

	public static int[] compute(int[] obs, int[] states, double[] start_p,
			double[][] trans_p, double[][] emit_p) {
		double[][] V = new double[obs.length][states.length];
		int[][] path = new int[states.length][obs.length];
		
    /*foreach������ʽ����ÿ��������б�����for(int y = 0;y  < states.length; y++){} ��һ����˼

      for(Ԫ������t Ԫ�ر���x : ��������obj){

              ������x��java���;

       }*/
		for (int y : states) {
			V[0][y] = start_p[y] * emit_p[y][obs[0]];//��λ��0����y״̬Ϊĩβ��״̬���е�������

			path[y][0] = y;
		}
		
		for (int t = 1; t < obs.length; ++t) {
			int[][] newpath = new int[states.length][obs.length];
			for (int y : states) {
				double prob = -1;
				int state;
				for (int y0 : states) {
//					System.out.println(t+","+y0+","+y+","+obs[t]);
					double nprob = V[t - 1][y0] * trans_p[y0][y]
							* emit_p[y][obs[t]];
					if (nprob > prob) {
						prob = nprob;
						state = y0;

						// ��¼������
						V[t][y] = prob;

						// ��¼·��
						System.arraycopy(path[state], 0, newpath[y], 0, t);
						newpath[y][t] = y;
					}
				}
			}
			path = newpath;
		}
		
		
		double prob = -1;
		int state = 0;
		for (int y : states) {
			if (V[obs.length - 1][y] > prob) {
				prob = V[obs.length - 1][y];
				state = y;
			}
		}
		return path[state];
	}
}



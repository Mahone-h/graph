package DATA;

public class Viterbi {

	/**
	 * 求解HMM模型
	 * 
	 * @param obs
	 *            观测序列
	 * @param states
	 *            隐状态
	 * @param start_p
	 *            初始概率（隐状态）
	 * @param trans_p
	 *            转移概率（隐状态）
	 * @param emit_p
	 *            发射概率 （隐状态表现为显状态的概率）
	 * @return 最可能的序列
	 */

	public static int[] compute(int[] obs, int[] states, double[] start_p,
			double[][] trans_p, double[][] emit_p) {
		double[][] V = new double[obs.length][states.length];
		int[][] path = new int[states.length][obs.length];
		
    /*foreach的语句格式：对每个数组进行遍历，for(int y = 0;y  < states.length; y++){} 是一个意思

      for(元素类型t 元素变量x : 遍历对象obj){

              引用了x的java语句;

       }*/
		for (int y : states) {
			V[0][y] = start_p[y] * emit_p[y][obs[0]];//在位置0，以y状态为末尾的状态序列的最大概率

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

						// 记录最大概率
						V[t][y] = prob;

						// 记录路径
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



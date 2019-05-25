

public class HmmProbabilities {

	private final double sigma;
    private final double beta;
    
    
    public HmmProbabilities(double sigma, double beta) {
        this.sigma = sigma;
        this.beta = beta;
    }
    
    public double emissionLogProbability(double distance) {
        return Distributions.logNormalDistribution(sigma, distance);
    }
    
    public double transitionLogProbability(double routeLength, double linearDistance) {
        
        Double transitionMetric = Math.abs(linearDistance - routeLength);

        return Distributions.logExponentialDistribution(beta, transitionMetric);
    }

}

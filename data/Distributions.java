
import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;



public class Distributions {
	static double normalDistribution(double sigma, double x) {
        return 1.0 / (sqrt(2.0 * PI) * sigma) * exp(-0.5 * pow(x / sigma, 2));
    }

    /**
     * Use this function instead of Math.log(normalDistribution(sigma, x)) to avoid an
     * arithmetic underflow for very small probabilities.
     */
    public static double logNormalDistribution(double sigma, double x) {
        return Math.log(1.0 / (sqrt(2.0 * PI) * sigma)) + (-0.5 * pow(x / sigma, 2));
    }

    /**
     * @param beta =1/lambda with lambda being the standard exponential distribution rate parameter
     */
    static double exponentialDistribution(double beta, double x) {
        return 1.0 / beta * exp(-x / beta);
    }

    /**
     * Use this function instead of Math.log(exponentialDistribution(beta, x)) to avoid an
     * arithmetic underflow for very small probabilities.
     *
     * @param beta =1/lambda with lambda being the standard exponential distribution rate parameter
     */
    static double logExponentialDistribution(double beta, double x) {
        return log(1.0 / beta) - (x / beta);
    }
}



package br.nataliakt.segmentation.model;

import br.nataliakt.id.model.ImageFilter;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class HoughLineFilter extends ImageFilter {

    public Mat filter(Mat mat, String... param) {
        double rho = 5;
        int threshold = 50;
        double minLineLenght = 40;
        double maxLineGap = 10;
        try {
            rho = Double.parseDouble(param[0]);
            threshold = Integer.parseInt(param[1]);
            minLineLenght = Double.parseDouble(param[2]);
            maxLineGap = Double.parseDouble(param[3]);
        } catch (Exception e) {
            //throw new RuntimeException("param must be double, double, int");
        }

        Mat gray = new Mat();
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(gray, gray, new Size(3, 3));

        // detect the edges
        Mat edges = new Mat();
        int lowThreshold = 50;
        int ratio = 3;
        Imgproc.Canny(gray, edges, lowThreshold, lowThreshold * ratio);

        Mat lines = new Mat();
        Imgproc.HoughLinesP(edges, lines, rho, Math.PI / 180,
                threshold, minLineLenght, maxLineGap);

        Mat result = mat.clone();

        for(int i = 0; i < lines.cols(); i++) {
            double[] val = lines.get(0, i);
            Imgproc.line(result, new Point(val[0], val[1]), new Point(val[2], val[3]),
                    new Scalar(0, 0, 255), 2);
        }
        return result;
    }

    @Override
    public boolean hasParam() {
        return true;
    }

    public String toString() {
        return "Hough Linhas";
    }
}

package br.nataliakt.segmentation.model;

import br.nataliakt.id.model.ImageFilter;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class HoughCircleFilter extends ImageFilter {

    public Mat filter(Mat mat, String... param) {
        int minRadius = 70;
        int maxRadius = 100;
        try {
            minRadius = Integer.parseInt(param[0]);
            maxRadius = Integer.parseInt(param[1]);
        } catch (Exception e) {}

        Mat gray = new Mat();
        mat.copyTo(gray);

        // convert to grayscale
        Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);

        // detect the edges
        Mat edges = new Mat();
        int lowThreshold = 50;
        int ratio = 3;
        Imgproc.Canny(gray, edges, lowThreshold, lowThreshold * ratio);

        // do hough circles
        Mat circles = new Mat();
        Imgproc.HoughCircles(edges, circles, Imgproc.CV_HOUGH_GRADIENT, 1, minRadius,
                120, 10, minRadius, maxRadius);

        Mat result = mat.clone();

        for(int i = 0; i < circles.cols(); i++) {
            double[] c = circles.get(0, i);
            Point center = new Point(c[0], c[1]);
            Imgproc.circle(result, center, 1, new Scalar(0,100,100), 3);
            int radius = (int) c[2];
            Imgproc.circle(result, center, radius, new Scalar(255,0,255), 3);
        }
        return result;
    }

    @Override
    public boolean hasParam() {
        return true;
    }

    @Override
    public String toString() {
        return "Hough Circulos";
    }
}

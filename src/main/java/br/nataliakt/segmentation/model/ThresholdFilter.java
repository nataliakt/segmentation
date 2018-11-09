package br.nataliakt.segmentation.model;

import br.nataliakt.id.model.ImageFilter;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ThresholdFilter extends ImageFilter {

    public Mat filter(Mat mat, String... param) {
        int thresh = -1;
        try {
            thresh = Integer.parseInt(param[0]);
        } catch (Exception e) {
            throw new RuntimeException("Param must be betwenn 0 and 255");
        }

        Mat gray = new Mat(mat.rows(), mat.cols(), mat.type());
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGB2GRAY);
        Mat destination = new Mat(mat.rows(), mat.cols(), mat.type());
        Imgproc.threshold(gray, destination, thresh,255, Imgproc.THRESH_BINARY);
        return destination;
    }

    @Override
    public String toString() {
        return "Threshold";
    }

    @Override
    public boolean hasParam() {
        return true;
    }
}

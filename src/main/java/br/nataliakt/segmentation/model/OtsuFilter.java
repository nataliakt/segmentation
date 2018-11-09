package br.nataliakt.segmentation.model;

import br.nataliakt.id.model.ImageFilter;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class OtsuFilter extends ImageFilter {

    public Mat filter(Mat mat, String... param) {
        Mat gray = new Mat(mat.rows(), mat.cols(), mat.type());
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGRA2GRAY);
        Mat destination = new Mat(mat.rows(), mat.cols(), mat.type());
        Imgproc.threshold(gray, destination, 0,255,
                Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        return destination;
    }

    @Override
    public String toString() {
        return "Método Otsu";
    }
}

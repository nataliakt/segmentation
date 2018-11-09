package br.nataliakt.segmentation;

import br.nataliakt.id.ImageDashboard;
import br.nataliakt.segmentation.model.HoughCircleFilter;
import br.nataliakt.segmentation.model.HoughLineFilter;
import br.nataliakt.segmentation.model.OtsuFilter;
import br.nataliakt.segmentation.model.ThresholdFilter;

public class Main {

    public static void main(String[] args) {
        ImageDashboard.showFilters(
                new OtsuFilter(),
                new HoughLineFilter(),
                new HoughCircleFilter(),
                new ThresholdFilter()
        );
    }

}

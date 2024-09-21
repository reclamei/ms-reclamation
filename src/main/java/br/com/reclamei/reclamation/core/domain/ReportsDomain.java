package br.com.reclamei.reclamation.core.domain;

import java.util.List;

public record ReportsDomain(
        List<HeatmapDataDomain> heatmapData
) {
}

package br.com.reclamei.reclamation.core.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record ResponseTimeGraphDomain(
        LocalDate day,
        double averageResponseTime,
        int quantityAnswered
) {

    public static List<ResponseTimeGraphDomain> from(final List<ReclamationDomain> reclamations) {
        return reclamations.stream()
                .filter(item -> Objects.nonNull(item.getAnalyzedAt()))
                .collect(Collectors.groupingBy(item -> item.getAnalyzedAt().toLocalDate()))
                .entrySet()
                .stream()
                .map(entry -> {
                    final LocalDate analyzedAt = entry.getKey();
                    final double averageResponseTime = entry.getValue().stream()
                        .mapToLong(reclamation -> ChronoUnit.DAYS.between(reclamation.getCreatedAt().toLocalDate(), reclamation.getAnalyzedAt().toLocalDate()))
                        .average()
                        .orElse(0.0);
                    final int quantityAnswered = entry.getValue().size();

                    return new ResponseTimeGraphDomain(analyzedAt, averageResponseTime, quantityAnswered);
                })
                .sorted(Comparator.comparing(ResponseTimeGraphDomain::day))
                .toList();
    }


}

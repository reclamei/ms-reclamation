package br.com.reclamei.reclamation.core.domain;

public record DashboardDomain(
        long totalCount,
        long answeredCount,
        long unansweredCount,
        long resolvedCount,
        long unresolvedCount
) {
}

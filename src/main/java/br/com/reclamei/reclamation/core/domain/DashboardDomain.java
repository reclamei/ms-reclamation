package br.com.reclamei.reclamation.core.domain;

public record DashboardDomain(
        long totalCount,
        long responseCount,
        long unansweredCount,
        long resolvedCount,
        long unresolvedCount
) {
}

package br.com.reclamei.reclamation.core.type;

import br.com.reclamei.reclamation.core.exception.NotFoundException;

import java.util.Arrays;

import static java.lang.String.format;

public enum ReclamationStatusType {
    OPEN,
    REJECTED,
    IN_ANALYSIS,
    UNIDENTIFIED,
    NO_FORECAST,
    FORECAST,
    RESOLVED;

    public static ReclamationStatusType getByName(final String status) {
        return Arrays.stream(ReclamationStatusType.values())
            .filter(item -> item.name().equals(status))
            .findFirst()
            .orElseThrow(() -> new NotFoundException(format("[ReclamationStatus] Enum %s not found.", status)));
    }
}

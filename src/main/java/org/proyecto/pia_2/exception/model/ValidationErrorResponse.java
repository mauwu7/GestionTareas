package org.proyecto.pia_2.exception.model;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<ConstrViolation> violations = new ArrayList<>();

    public List<ConstrViolation> getViolations() {
        return violations;
    }

    public void setViolations(List<ConstrViolation> violations) {
        this.violations = violations;
    }
}

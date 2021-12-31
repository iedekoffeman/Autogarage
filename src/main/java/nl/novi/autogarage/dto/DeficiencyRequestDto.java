package nl.novi.autogarage.dto;

import javax.validation.constraints.NotEmpty;

public class DeficiencyRequestDto {

    @NotEmpty
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package nl.novi.autogarage.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ItemRequestDto {

    @NotBlank
    private String name;
    @DecimalMin(value = "0.0", inclusive = false, message="{error.price.notZero}")
    @NotNull
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

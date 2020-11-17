package com.maerks.dto.request;

import com.maerks.enums.ContainerType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    //not handled if the value of enum is something different like DRAY instead of DRY
    @NotNull
    @ApiModelProperty(example = "DRY")
    private ContainerType containerType;

    @NotNull
    @Pattern(regexp = "20|40", message = "Alllowed Values are 20 or 40")
    @ApiModelProperty(example = "20", allowableValues = "20,40")
    private String containerSize;

    @NotNull
    @Size(min = 5,max = 20,message = "Character length should be between 5 to 20")
    @ApiModelProperty(example = "Southampton")
    private String origin;

    @NotNull
    @Size(min = 5,max = 20,message = "Character length should be between 5 to 20")
    @ApiModelProperty(example = "Singapore")
    private String destination;

    @NotNull
    @Min(value = 1, message = "The minimum quantity is 1")
    @Max(value = 100, message = "The maximum quantity is 100")
    @ApiModelProperty(example = "5")
    private Integer quantity;
}
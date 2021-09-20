package com.booking.api.controller.data.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class BookCreationRequest {

    @NotNull(message = "'userId' cannot be null")
    private Long userId;

    @NotNull(message = "'dateFrom' cannot be null")
    private LocalDate dateFrom;

    @NotNull(message = "'dateTo' cannot be null")
    private LocalDate dateTo;
}

package com.booking.api.controller.data.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class BookUpdateRequest {
    @NotNull(message = "'dateFrom' cannot be null")
    private LocalDate dateFrom;

    @NotNull(message = "'dateTo' cannot be null")
    private LocalDate dateTo;
}

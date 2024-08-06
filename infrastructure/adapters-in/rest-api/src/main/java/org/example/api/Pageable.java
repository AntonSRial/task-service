package org.example.api;

import javax.validation.constraints.Min;

public record Pageable(@Min(1) int size, @Min(0) int page) {

}

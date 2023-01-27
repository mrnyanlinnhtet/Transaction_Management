package com.ultron.demo.dto;

import java.util.List;

public record PropagationResult(
		int headerId,
		List<Integer> detailsId
		) {

}

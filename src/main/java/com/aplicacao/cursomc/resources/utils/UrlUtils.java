package com.aplicacao.cursomc.resources.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UrlUtils {

	public static List<Integer> decodeList(String ids) {
		return Arrays.asList(ids.split(","))
				.stream()
				.map(x-> Integer.parseInt(x))
				.collect(Collectors.toList());
	}
}

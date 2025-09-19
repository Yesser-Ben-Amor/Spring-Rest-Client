package org.example.springrestclient.model;

import java.util.List;

public record CharacterResponseModel(
        InfoModel info,
        List<CharacterModel> results
) {}

record InfoModel(
        int count,
        int pages,
        String next,
        String prev
) {}
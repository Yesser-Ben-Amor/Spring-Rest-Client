package org.example.springrestclient.model;

import java.util.List;
//Record bauen mit der gleichen Struktur, wie die angefordeten Data aus dem Server
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

package org.example.springrestclient.model;

import java.util.List;

public record CharacterModel(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        OriginModel origin,
        LocationModel location,
        String image,
        List<String> episode,
        String url,
        String created
) {}

record OriginModel(String name, String url) {}
record LocationModel(String name, String url) {}

package org.example.springrestclient.service;


import org.example.springrestclient.model.CharacterModel;
import org.example.springrestclient.model.CharacterResponseModel;
import org.example.springrestclient.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service

public class CharacterServiceImpl implements CharacterService{
    //base url als Konstante
    private static final String API_BASE_URL = "https://rickandmortyapi.com/api/character";
    //Rest Client einbinden mit Dependency Injection
    private final RestClient restClient;
    //Konstruktor bauen,aber mit Build
    public CharacterServiceImpl(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(API_BASE_URL)
                .build();
    }
    //get CharacterByID implementieren
    @Override
    public CharacterModel getCharacterById(int id) {
        return restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .body(CharacterModel.class);
    }
    //getAllCharacters implementieren
    @Override
    public List<CharacterModel> getAllCharacters() {
        CharacterResponseModel response = restClient.get()
                .uri("")
                .retrieve()
                .body(CharacterResponseModel.class);

        return response != null ? response.getResults() : List.of();
    }
    //getCharactersByPage implementieren
    @Override
    public CharacterResponseModel getCharactersByPage(int page) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(CharacterResponseModel.class);
    }
    //SearchCharacter implementieren
    @Override
    public List<CharacterModel> searchCharacters(String name) {
        CharacterResponseModel response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .body(CharacterResponseModel.class);

        return response != null ? response.getResults() : List.of();
    }
    //getMultipleCharacters implementieren
    @Override
    public List<CharacterModel> getMultipleCharacters(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        String idString = String.join(",", ids.stream()
                .map(String::valueOf)
                .toList());

        // Für multiple IDs gibt die API ein Array zurück
        if (ids.size() > 1) {
            CharacterModel[] characters = restClient.get()
                    .uri("/{ids}", idString)
                    .retrieve()
                    .body(CharacterModel[].class);

            return characters != null ? List.of(characters) : List.of();
        } else {
            // Für single ID gibt die API ein einzelnes Objekt zurück
            CharacterModel character = restClient.get()
                    .uri("/{id}", ids.get(0))
                    .retrieve()
                    .body(CharacterModel.class);

            return character != null ? List.of(character) : List.of();
        }
    }


}

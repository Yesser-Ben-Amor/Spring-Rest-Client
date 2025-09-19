package org.example.springrestclient.service;

import org.example.springrestclient.model.CharacterModel;
import org.example.springrestclient.model.CharacterResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private static final String API_BASE_URL = "https://rickandmortyapi.com/api/character";
    private final RestClient restClient;

    public CharacterServiceImpl(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(API_BASE_URL)
                .build();
    }

    @Override
    public CharacterModel getCharacterById(int id) {
        return restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .body(CharacterModel.class);
    }

    @Override
    public List<CharacterModel> getAllCharacters() {
        CharacterResponseModel response = restClient.get()
                .uri("")
                .retrieve()
                .body(CharacterResponseModel.class);

        return response != null ? response.results() : List.of();
    }

    @Override
    public CharacterResponseModel getCharactersByPage(int page) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(CharacterResponseModel.class);
    }

    @Override
    public List<CharacterModel> searchCharacters(String name) {
        CharacterResponseModel response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .body(CharacterResponseModel.class);

        return response != null ? response.results() : List.of();
    }

    @Override
    public List<CharacterModel> getMultipleCharacters(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        String idString = String.join(",", ids.stream()
                .map(String::valueOf)
                .toList());

        if (ids.size() > 1) {
            CharacterModel[] characters = restClient.get()
                    .uri("/{ids}", idString)
                    .retrieve()
                    .body(CharacterModel[].class);

            return characters != null ? List.of(characters) : List.of();
        } else {
            CharacterModel character = restClient.get()
                    .uri("/{id}", ids.get(0))
                    .retrieve()
                    .body(CharacterModel.class);

            return character != null ? List.of(character) : List.of();
        }
    }
}
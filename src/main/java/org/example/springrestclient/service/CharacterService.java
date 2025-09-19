package org.example.springrestclient.service;


import org.example.springrestclient.model.CharacterModel;

import java.util.List;

public interface CharacterService {
    CharacterModel getCharacterById(int id);
    List<CharacterModel> getAllCharacters();
    CharacterResponseModel getCharactersByPage(int page);

    List<CharacterModel> searchCharacters(String name);

    List<CharacterModel> getMultipleCharacters(List<Integer> ids);


}

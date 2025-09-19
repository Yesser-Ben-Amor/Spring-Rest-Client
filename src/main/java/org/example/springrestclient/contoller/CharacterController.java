package org.example.springrestclient.contoller;


import org.example.springrestclient.model.CharacterModel;
import org.example.springrestclient.model.CharacterResponseModel;
import org.example.springrestclient.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/character")
public class CharacterController {
    //Dependency injection
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterModel> getCharacterById(@PathVariable int id) {
        try {
            CharacterModel character = characterService.getCharacterById(id);
            return ResponseEntity.ok(character);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CharacterModel>> getAllCharacters() {
        try {
            List<CharacterModel> characters = characterService.getAllCharacters();
            return ResponseEntity.ok(characters);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/page/{page}")
    public ResponseEntity<CharacterResponseModel> getCharactersByPage(@PathVariable int page) {
        try {
            CharacterResponseModel response = characterService.getCharactersByPage(page);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<CharacterModel>> searchCharacters(@RequestParam String name) {
        try {
            List<CharacterModel> characters = characterService.searchCharacters(name);
            return ResponseEntity.ok(characters);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/multiple")
    public ResponseEntity<List<CharacterModel>> getMultipleCharacters(@RequestParam List<Integer> ids) {
        try {
            List<CharacterModel> characters = characterService.getMultipleCharacters(ids);
            return ResponseEntity.ok(characters);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Character API is running!");
    }




}

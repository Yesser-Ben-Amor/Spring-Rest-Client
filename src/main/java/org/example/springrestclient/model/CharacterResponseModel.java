package org.example.springrestclient.model;

import lombok.Data;
import java.util.List;

@Data
public class CharacterResponseModel {
    private InfoModel info;
    private List<CharacterModel> results;
}

@Data
class InfoModel {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
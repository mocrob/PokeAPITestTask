package com.example.pokeapitesttask.features.pokes.domain.model;

import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public final class Pokemon implements Serializable {
    public int id;
    public int height;
    public int weight;
    public String name;
    public String url;
    public String sprite;
    //public List<stat> stats;
    public Object stats;
    public Object types;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Object getTypes() {
        return types;
    }

    public void setTypes(Object types) {
        this.types = types;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Object getStats() {
        return stats;
    }

    public void setStats(Object stats) {
        this.stats = stats;
    }

    public double getAttack(){
        LinkedHashMap tmp = (LinkedHashMap) convertObjectToList(stats).get(3);
        return (double)tmp.get("base_stat");
    }

    public double getDefence(){
        LinkedHashMap tmp = (LinkedHashMap) convertObjectToList(stats).get(4);
        return (double)tmp.get("base_stat");
    }

    public double getHp(){
        LinkedHashMap tmp = (LinkedHashMap) convertObjectToList(stats).get(5);
        return (double)tmp.get("base_stat");
    }

    public String getTypesOfPokemon(){
        String typesOfPoke = "";
        ArrayList tmp = (ArrayList) convertObjectToList(types);
        for (int i=0;i<tmp.size();i++){
            LinkedHashMap test = (LinkedHashMap)tmp.get(i);
            test = (LinkedHashMap)test.get("type");
            typesOfPoke+=((String)test.get("name")+", ");
        }
        return typesOfPoke.substring(0,typesOfPoke.length()-2);
    }

    public Pokemon(int id, int height, int weight, String name, String url, String sprite, Object stats, Object types) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.url = url;
        this.sprite = sprite;
        this.stats = stats;
        this.types = types;
    }

    public Pokemon(int id, String name, String url, String sprite, Object stats) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.sprite = sprite;
        this.stats = stats;
    }

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }

}

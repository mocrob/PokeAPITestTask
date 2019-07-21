package com.example.pokeapitesttask.features.pokes.domain.model;

public final class stat {
    public int base_stat;
    public int effort;
    public String stat_name;

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }

    public stat(int base_stat, int effort, String stat_name) {
        this.base_stat = base_stat;
        this.effort = effort;
        this.stat_name = stat_name;
    }
}

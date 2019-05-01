package com.example.aspect;

import java.util.List;

public interface Sample<T> {
    void showGenetic(T param);

    void showGeneticList(List<T> params);
}

package com.caselab.warship.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ship {
    private int size;
    @Builder.Default
    private List<Point> coordinates = new ArrayList<>();
    @Builder.Default
    private List<Boolean> hitStatus = new ArrayList<>();

    public void initializeHitStatus() {
        hitStatus.clear();
        for (int i = 0; i < size; i++) {
            hitStatus.add(false);
        }
    }
}

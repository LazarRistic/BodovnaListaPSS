package com.overswayit.tuple;

import java.util.Objects;

/**
 * Created by lazarristic on 26/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class Quadruple<F, S, T, D> {
    public final F first;
    public final S second;
    public final T third;
    public final D fourth;

    public Quadruple(F first, S second, T third, D fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Quadruple)) {
            return false;
        }
        Quadruple<?, ?, ?, ?> t = (Quadruple<?, ?, ?, ?>) o;
        return Objects.equals(t.first, first) && Objects.equals(t.second, second) && Objects.equals(t.third, third) && Objects.equals(t.fourth, fourth);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode()) ^ (third == null ? 0 : third.hashCode()) ^ (fourth == null ? 0 : fourth.hashCode());
    }

    @Override
    public String toString() {
        return "Quadruple{" + String.valueOf(first) + " " + String.valueOf(second) + " " + String.valueOf(third) + " " + String.valueOf(fourth) + "}";
    }

    public static <A, B, C, D> Quadruple<A, B, C, D> create(A a, B b, C c, D d) {
        return new Quadruple<A, B, C, D>(a, b, c, d);
    }
}

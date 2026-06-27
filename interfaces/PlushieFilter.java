package interfaces;

import model.Plushie;

@FunctionalInterface
public interface PlushieFilter {
    boolean matches(Plushie plushie);
}
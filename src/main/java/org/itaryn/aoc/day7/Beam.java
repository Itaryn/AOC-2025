package org.itaryn.aoc.day7;

import java.util.List;

public class Beam {
    private int position;
    private long particles;

    public Beam(int position) {
        this.position = position;
        this.particles = 1;
    }

    public Beam(int position, long particles) {
        this.position = position;
        this.particles = particles;
    }

    public List<Beam> split() {
        return List.of(
                new Beam(getPosition() - 1, getParticles()),
                new Beam(getPosition() + 1, getParticles())
        );
    }

    public int getPosition() {
        return position;
    }

    public Beam setPosition(int position) {
        this.position = position;
        return this;
    }

    public long getParticles() {
        return particles;
    }

    public Beam setParticles(long particles) {
        this.particles = particles;
        return this;
    }
}

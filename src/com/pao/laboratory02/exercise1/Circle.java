package com.pao.laboratory02.exercise1;

/**
 * TODO: Implementează Circle extends Shape.
 * - Atribut: private double radius
 * - Constructor: super("Circle"), this.radius = radius
 * - area() = Math.PI * radius * radius
 * - perimeter() = 2 * Math.PI * radius
 */
public class Circle extends Shape {

    // TODO
    private double radius;

    public Circle(double radius) {
        super("Circle");
        // TODO
        this.radius = radius;
    }
    //Math.PI*r*r 2*Math.PI*r

    @Override
    public double area() {
        // TODO
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        // TODO
        return Math.PI * 2 * radius;
    }
}
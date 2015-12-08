package com.ikamobile.mbean;

public class Car implements CarMBean{
	private String color = "red";
	
	public String getColor(){
		return color;
	}
	public void setColor(String color){
		this.color = color;
	}
	public void drive(Car a){
		System.out.println("Baby you can drive my "+a+color+" car.");
	}
}

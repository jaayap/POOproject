package sysexp.builders;

import java.io.IOException;

public class Director {
	private Builder builder;
	
	public Director(Builder builder){
		this.builder = builder;
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}
	
	public void construct() throws IOException{
		//for all object in structure loop
		//builder.buildPart();
		//end loop;
		
		builder.buildPart();//dans notre cas on fabrique l'objet en un seul pas
	}
}

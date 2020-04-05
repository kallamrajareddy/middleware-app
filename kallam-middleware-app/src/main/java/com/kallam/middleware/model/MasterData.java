package com.kallam.middleware.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

	@Document(collection = "mastersdata")
	public class MasterData {
		
		@Id
		private String id;
		
		private List<Company> company;
		
		private List<String> typeOfItems = new ArrayList<>();
		
		private List<String> typeOfRelation = new ArrayList<>();
		
		private List<String> typeOfMeasurment = new ArrayList<>();
		
		private List<String> typeOfIntrest = new ArrayList<>();

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<Company> getCompany() {
			return company;
		}

		public void setCompany(List<Company> company) {
			this.company = company;
		}

		public List<String> getTypeOfItems() {
			return typeOfItems;
		}

		public void setTypeOfItems(List<String> typeOfItems) {
			this.typeOfItems = typeOfItems;
		}

		public List<String> getTypeOfRelation() {
			return typeOfRelation;
		}

		public void setTypeOfRelation(List<String> typeOfRelation) {
			this.typeOfRelation = typeOfRelation;
		}

		public List<String> getTypeOfMeasurment() {
			return typeOfMeasurment;
		}

		public void setTypeOfMeasurment(List<String> typeOfMeasurment) {
			this.typeOfMeasurment = typeOfMeasurment;
		}

		public List<String> getTypeOfIntrest() {
			return typeOfIntrest;
		}

		public void setTypeOfIntrest(List<String> typeOfIntrest) {
			this.typeOfIntrest = typeOfIntrest;
		}

		public MasterData(String id, List<Company> company, List<String> typeOfItems, List<String> typeOfRelation,
				List<String> typeOfMeasurment, List<String> typeOfIntrest) {
			super();
			this.id = id;
			this.company = company;
			this.typeOfItems = typeOfItems;
			this.typeOfRelation = typeOfRelation;
			this.typeOfMeasurment = typeOfMeasurment;
			this.typeOfIntrest = typeOfIntrest;
		}

		public MasterData() {
			super();
		}
		
}

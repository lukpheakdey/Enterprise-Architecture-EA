package edu.mum.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import edu.mum.domain.ProductionStatus;

@Converter(autoApply = true)
	public class ProductionStatusConverter implements AttributeConverter<ProductionStatus, String> {

	 @Override
	 public String convertToDatabaseColumn(ProductionStatus productionStatus) {
	  switch (productionStatus) {
	  case INVALID:
	   return "I";
	  case BASIC:
	   return "B";
	  case DETAILS:
	   return "D";
	  case PRODUCTION:
	   return "P";
	  default:
	   throw new IllegalArgumentException("Unknown value: " + productionStatus);
	  }
	 }

	 @Override
	 public ProductionStatus convertToEntityAttribute(String fromDatabase) {
	  switch (fromDatabase) {
	  case "B":
	   return ProductionStatus.BASIC;
	  case "D":
	   return ProductionStatus.DETAILS;
	  case "P":
	   return ProductionStatus.PRODUCTION;
	  case "I":
	   return ProductionStatus.INVALID;
	  default:
	   throw new IllegalArgumentException("Unknown value: " + fromDatabase);
	  }
	 }

	}
 

package com.taller2.service.interfaces;

import com.taller2.model.prod.Productsubcategory;

public interface ProductsubcategoryService {
	public Productsubcategory saveProductSubcategory(Productsubcategory psc);
	public Productsubcategory searchProductSubcategory(Integer pscId);
	public Productsubcategory updateProductSubcategory(Integer pscId, Productsubcategory psc);
	public void deleteProductSubcategory(Integer pscId);
}

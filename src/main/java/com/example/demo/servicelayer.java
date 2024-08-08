package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class servicelayer {
	

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductsRepoLayer productsRepoLayer;

	public List<category> getAllCategories() 
	{
		return categoryRepo.findAll();
	}
	public List<products> getAllProducts(Integer categoryId) 
	{
		if (categoryId == null) 
		{
			return productsRepoLayer.findAll();	
		} 
		else 
		{
			return productsRepoLayer.findByCategoryId(categoryId);
		}
		
	}
	public Optional<category> getCategoryById(Integer id) 
	{
		return categoryRepo.findById(id);
	}
	public category addCategory(category categ) throws CustomException 
	{
        Optional<category> existingCategory = categoryRepo.findByName(categ.getName());
        if (!existingCategory.isPresent()) 
        {
            return categoryRepo.save(categ);
        }
        else 
        {
            throw new CustomException("Category already exists");
        }
    }
	public products addproduct(products product)
	{
		return productsRepoLayer.save(product);
	}
	public category updatecategory(Integer id, category categ) 
	{
	    category categorydb = categoryRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Category id not exsits"));
	    categorydb.setName(categ.getName());
	    categorydb.setDescription(categ.getDescription());
	    return categoryRepo.save(categorydb);
	}
	public category deleteCategory(Integer id) 
	{
        category categorydb = categoryRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Category id was not found"));
        categoryRepo.delete(categorydb);
        return categorydb;
    }
	public List<products> findProductsByName(String name) {
        return productsRepoLayer.findByNameContainsIgnoreCase(name);
    } 	
	public Map<String, List<products>> getProductsByCategory()
	{
		List<products> productsList = productsRepoLayer.findAll();
		Map<String, List<products>> categoryMap = new HashMap<>();
		
		for (products product: productsList) 
		{
			String categoryName = product.getCategory().getName();
			if (!categoryMap.containsKey(categoryName)) 
			{
				categoryMap.put(categoryName, new ArrayList<products>());
			}	
			categoryMap.get(categoryName).add(product);
		}
		return categoryMap;
	}
		public LinkedHashMap<String, List<products>> SortedByCategoryandProducts ()
		{
			
			List<products> productList = productsRepoLayer.findAll();
			LinkedHashMap<String, List<products>> categoryMap1 = new LinkedHashMap<>();
			for(products product : productList)
			{
				String categoryName = product.getCategory().getName();
				if(!categoryMap1.containsKey(categoryName))
				{
					categoryMap1.put(categoryName, new LinkedList<products>());
				}
				categoryMap1.get(categoryName).add(product);
			}
			return categoryMap1;
		}


	/*public Map<category, List<products>> getProductsByCategory() 
	{
	    List<products> productList = productsRepoLayer.findAll();
	    Map<category, List<products>> categoryMap = new HashMap<>();
	    
	    category categoryObj1 = new category();
	    categoryObj1.setName("Home");
	    categoryObj1.setId(100);
	    for (products product : productList) 
	    {
	    	category categoryObject = product.getCategory();
	    	if (!categoryMap.containsKey(categoryObject)) 
	    	{
	            	categoryMap.put(categoryObject, new ArrayList<>());
	    	}
	        	categoryMap.get(categoryObject).add(product);
	    }
	    categoryMap.get(categoryObj1).add(null);
	    
	    return categoryMap;
	}*/
		@Transactional
		public void saveCsvDataToDatabase(String csvfile)
		{
		    String line;
		    boolean isHeader = true;
		    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\KASAMSETTY CHARAN\\Desktop\\input-products.csv"))) 
		    {
		        while ((line = reader.readLine()) != null) 
		        { 
		        	if (isHeader) 
		        {
	                isHeader = false;
	                continue;
		        }
		            String[] values = line.split(",");
		            if (values.length >= 3) 
		            {
		            
		                Optional<products> newProduct = productsRepoLayer.findByName(values[1]);
	                    if (newProduct.isPresent()) 
	                    {
	                        continue;
	                    }
	                    products product = new products();
		                
		                product.setSno(Integer.parseInt(values[0])); 
		                product.setName(values[1]); 
		          
	                    
		                Optional<category> categoryObj = categoryRepo.findById(Integer.parseInt(values[2]));
		                product.setCategory(categoryObj.get()); 
		                productsRepoLayer.save(product);
		            }
		        }
		    } 
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
		}
		
}  


package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.awt.PageAttributes.MediaType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@RestController
public class ControllerLayer {
    
    @Autowired
    private servicelayer serviceLayer;

    @GetMapping("/categories")
    public List<category> getAllCategories() 
    {
        return serviceLayer.getAllCategories();
    }
    @GetMapping("/products")
    public List<products> getAllProducts(@RequestParam Integer categoryId)
    {
        return serviceLayer.getAllProducts(categoryId);
    }
    
    
    @GetMapping("/category")
    public Optional<category> getCategoryById(@RequestParam Integer id) 
    {
        return serviceLayer.getCategoryById(id);
    }
    @PostMapping("/categories")
    public category addCategory(@RequestBody category categ) 
    {
        try 
        {
            return serviceLayer.addCategory(categ);
        } 
        catch (CustomException e) 
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @PostMapping("/Products")
    public products addproduct(@RequestBody products product) // Create Products 
    {
        return serviceLayer.addproduct(product);
    }
    @PutMapping("/updatecategory/{id}") // Update CATEGORY
    public category updatecategory(@PathVariable Integer id, @RequestBody category categ)
    {
    	return serviceLayer.updatecategory(id, categ);
    }
    @DeleteMapping("/category/{id}")
    public category deleteCategory(@PathVariable Integer id)
    {
        return serviceLayer.deleteCategory(id);
    }
    @GetMapping("/getproducts")
    public List<products> findProductsByName(@RequestParam String name) {
        return serviceLayer.findProductsByName(name);
    }
    @GetMapping("/getallproducts")
    public Map<String, List<products>> getProductsByCategory()
    {
    	return serviceLayer.getProductsByCategory();
    }
    @GetMapping("/getsortedcategory")
    public LinkedHashMap<String, List<products>> SortedByCategoryandProducts()
    {
    	return serviceLayer.SortedByCategoryandProducts();
    }
    @PostMapping("/upload-products")
    public void uploadProducts()
    {
    	serviceLayer.saveCsvDataToDatabase("\"C:\\\\Users\\\\KASAMSETTY CHARAN\\\\Desktop\\\\input-products.csv\"");
    }
}


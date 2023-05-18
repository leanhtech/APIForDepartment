package com.ptit.springbootdepartmentstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data

public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", nullable = false)
    private Integer id;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="product_name")
    private String productName;

    @Column(name="status")
    private String status;
    
    @Column(name = "price")
	private Double price;
    
    @Column(name = "specificaion")
    private String specification;
    
    @Column(name = "calculation_unit")
    private Integer calculationUnit;
    
    @Column(name = "discount")
    private Integer discount;
    
    @Column(name = "sold")
    private Integer sold;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id", nullable=false)
    private Brand brand;

//    @JsonIgnore
//    @OneToMany(mappedBy = "product")
//	private List<Image> imageList;
}

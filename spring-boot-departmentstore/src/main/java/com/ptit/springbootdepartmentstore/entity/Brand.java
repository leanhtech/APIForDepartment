package com.ptit.springbootdepartmentstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", nullable = false)
    private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String descipttion;
	
	@Column(name = "image_byte")
	@Lob
	private byte[] imageByte;
	
	@OneToOne
	@JoinColumn(name = "image_id")
    private Image image;
	
	@JsonIgnore
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private List<Product> productList;
}

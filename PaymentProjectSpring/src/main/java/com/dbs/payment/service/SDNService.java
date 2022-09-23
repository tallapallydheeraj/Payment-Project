package com.dbs.payment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.SDNList;
import com.dbs.payment.repo.SDNRepository;

@Service
public class SDNService {
	@Autowired
	private SDNRepository repo;
	
	public List<String> findAll(){
		List<String> l=new ArrayList<>();
		this.repo.findAll().forEach(ele -> l.add(ele.getName().toLowerCase()
				.replaceAll("[\\s,.';*-]", "")));
		/*System.out.println(l.get(2)+"hello");
		System.out.println(l.get(6000));
		System.out.println(l.get(6789));
		System.out.println(l.get(45));
		System.out.println(l.get(912));
		System.out.println(l.get(100));
		System.out.println(l.get(400));
		System.out.println(l.get(340));
		System.out.println(l.get(3456));
		System.out.println(l.get(90));*/
		return l;
	}
}

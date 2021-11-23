package com.taller2.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller2.model.sales.Specialoffer;
import com.taller2.repository.SpecialofferRepository;
import com.taller2.service.interfaces.SpecialofferService;

@Service
public class SpecialOfferServiceImpl implements SpecialofferService {
	
	@Autowired
	private SpecialofferRepository soRepo;
	
	public SpecialOfferServiceImpl(SpecialofferRepository soRepo) {
		this.soRepo = soRepo;
	}

	@Override
	public Specialoffer saveSpecialOffer(Specialoffer s) throws Exception{
		if(s==null) {
			throw new NullPointerException("SpecialOffer cannot be null");
		}else {
			if(s.getCategory()==null || s.getCategory().isBlank()) {
				throw new Exception("SpecialOffer's category cannot be null");
			}else if(s.getDiscountpct()==null || s.getDiscountpct().longValue()<=0) {
				throw new Exception("SpecialOffer's discount must be greater than 0");
			}else if(s.getModifieddate()==null || (s.getModifieddate().getTime()<System.currentTimeMillis()-10000 || s.getModifieddate().getTime()>System.currentTimeMillis()+10000)) {
				throw new Exception("SpecialOffer's modified date must be now and cannot be null");
			}else {
				soRepo.save(s);
			}
		}
		return s;
	}

	@Override
	public Specialoffer searchSpecialOffer(Integer sId) {
		Optional<Specialoffer> opSO = soRepo.findById(sId);
		return (opSO.isEmpty()) ? null : opSO.get();
	}

	@Override
	public Specialoffer updateSpecialOffer(Integer sId, Specialoffer s) throws Exception {
		Specialoffer toChange = soRepo.findById(sId).get();
		String cat = s.getCategory();
		BigDecimal dis = s.getDiscountpct();
		Timestamp mod = s.getModifieddate();
		if(cat==null || cat.isBlank()) {
			throw new Exception("Category cannot be null or blank");
		}else if(dis==null || dis.longValue()<=0) {
			throw new Exception("Discount cannot be null nor less than 0");
		}else if(mod==null || (mod.getTime()<System.currentTimeMillis()-10000 || mod.getTime()>System.currentTimeMillis()+10000)) {
			throw new Exception("Modified date must be now and not null");
		}else {
			toChange.setCategory(cat);
			toChange.setDiscountpct(dis);
			toChange.setModifieddate(mod);
			toChange.setDescription(s.getDescription());
			toChange.setEnddate(s.getEnddate());
			toChange.setMaxqty(s.getMaxqty());
			toChange.setMinqty(s.getMinqty());
			toChange.setRowguid(s.getRowguid());
			toChange.setSpecialofferproducts(s.getSpecialofferproducts());
			toChange.setStartdate(s.getStartdate());
			toChange.setEnddate(s.getEnddate());
			toChange.setType(s.getType());
			soRepo.save(toChange);
		}
		return s;
	}

	@Override
	public void deleteSpecialOffer(Integer sId) {
		soRepo.delete(soRepo.findById(sId).get());
	}

}

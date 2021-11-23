package com.taller2.service.implementation;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller2.model.prod.*;
import com.taller2.model.sales.*;
import com.taller2.repository.*;
import com.taller2.service.interfaces.SpecialofferproductService;

@Service
public class SpecialOfferProductServiceImpl implements SpecialofferproductService {
	@Autowired
	public ProductRepository pRep;
	@Autowired
	public SpecialofferRepository soRep;
	@Autowired
	public SpecialofferproductRepository sopRep;
	
	public SpecialOfferProductServiceImpl() {
	}

	@Override
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId) throws Exception {
		if(sp==null) {
			throw new NullPointerException("SpecialOfferProduct must not be null");
		}else {
			Timestamp mod = sp.getModifieddate();
			if(mod==null || (mod.getTime()<System.currentTimeMillis()-10000 || mod.getTime()>System.currentTimeMillis()+10000)) {
				throw new Exception("SpecialOfferProduct modified date must be now and cannot be null");
			}else {
				Product p = pRep.findById(pId).get();
				Specialoffer so = soRep.findById(soId).get();
				sp.setSpecialoffer(so);
				sp.getId().setProductid(pId);
				sp.getId().setSpecialofferid(soId);
				sopRep.save(sp);
			}
		}
		return sp;
	}

	@Override
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId) {
		Optional<Specialofferproduct> op = sopRep.findById(spId);
		return (op.isEmpty()) ? null : op.get();
	}

	@Override
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp) throws Exception {
		Specialofferproduct toChange = sopRep.findById(spId).get();
		Timestamp mod = sp.getModifieddate();
		if(mod==null || (mod.getTime()<System.currentTimeMillis()-10000 || mod.getTime()>System.currentTimeMillis()+10000)) {
			throw new Exception("Modified date must be now and not null");
		}else {
			toChange.setModifieddate(mod);
			toChange.setRowguid(sp.getRowguid());
			toChange.setSpecialoffer(sp.getSpecialoffer());
			sopRep.save(toChange);
		}
		return sp;
	}

	@Override
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId) {
		sopRep.delete(sopRep.findById(spId).get());
	}

}

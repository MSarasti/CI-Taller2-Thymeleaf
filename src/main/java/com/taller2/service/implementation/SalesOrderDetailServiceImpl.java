package com.taller2.service.implementation;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller2.model.prod.*;
import com.taller2.model.sales.*;
import com.taller2.repository.*;
import com.taller2.service.interfaces.SalesorderdetailService;

@Service
public class SalesOrderDetailServiceImpl implements SalesorderdetailService {
	@Autowired
	public ProductRepository pRep;
	@Autowired
	public SpecialofferRepository soRep;
	@Autowired
	public SalesorderdetailRepository sodRep;
	
	public SalesOrderDetailServiceImpl() {
	}

	@Override
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId) throws Exception {
		if(sd==null) {
			throw new NullPointerException("SalesOrderDetail must not be null");
		}else {
			BigDecimal up = sd.getUnitprice();
			BigDecimal upd = sd.getUnitpricediscount();
			if(up==null || up.longValue()<=0) {
				throw new Exception("SOD's unit price must be greater than 0 and cannot be null");
			}else if(upd==null || upd.longValue()<0) {
				throw new Exception("SOD's unit price discount must non-negative and cannot be null");
			}else {
				Product p = pRep.findById(pId).get();
				Specialoffer so = soRep.findById(soId).get();
				sodRep.save(sd);
			}
		}
		return sd;
	}

	@Override
	public Salesorderdetail searchSalesOrderDetail(SalesorderdetailPK sdId) {
		Optional<Salesorderdetail> op = sodRep.findById(sdId);
		return (op.isEmpty()) ? null : op.get();
	}

	@Override
	public Salesorderdetail updateSalesOrderDetail(SalesorderdetailPK sdId, Salesorderdetail sd) throws Exception {
		Salesorderdetail toChange = sodRep.findById(sdId).get();
		if(sd==null) {
			throw new NullPointerException("SalesOrderDetail must not be null");
		}else {
			BigDecimal up = sd.getUnitprice();
			BigDecimal upd = sd.getUnitpricediscount();
			if(up==null || up.longValue()<=0) {
				throw new Exception("SOD's unit price must be greater than 0 and cannot be null");
			}else if(upd==null || upd.longValue()<0) {
				throw new Exception("SOD's unit price discount must non-negative and cannot be null");
			}else {
				toChange.setCarriertrackingnumber(sd.getCarriertrackingnumber());
				toChange.setModifieddate(sd.getModifieddate());
				toChange.setOrderqty(sd.getOrderqty());
				toChange.setRowguid(sd.getRowguid());
				toChange.setSpecialofferproduct(sd.getSpecialofferproduct());
				toChange.setUnitprice(up);
				toChange.setUnitpricediscount(upd);
				sodRep.save(toChange);
			}
		}
		return sd;
	}

	@Override
	public void deleteSalesOrderDetail(SalesorderdetailPK sdId) {
		sodRep.delete(sodRep.findById(sdId).get());
	}

}

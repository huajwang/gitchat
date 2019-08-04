package com.longmaple.ttmall.vendorsvr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmaple.ttmall.vendorsvr.data.VendorRepo;
import com.longmaple.ttmall.vendorsvr.event.SimpleSourceBean;
import com.longmaple.ttmall.vendorsvr.model.Vendor;

import java.util.UUID;

@Service
public class VendorService {
    @Autowired
    private VendorRepo orgRepository;
    
    @Autowired
    SimpleSourceBean simpleSourceBean;

    public Vendor getVendor(String organizationId) {
        return orgRepository.findById(organizationId).get();
    }

    public void saveVendor(Vendor vendor) {
        vendor.setVendorId( UUID.randomUUID().toString());
        orgRepository.save(vendor);
        simpleSourceBean.publishVendorChange("SAVE", vendor.getVendorId());
    }

    public void updateVendor(Vendor vendor){
        orgRepository.save(vendor);
        simpleSourceBean.publishVendorChange("UPDATE", vendor.getVendorId());
    }

    public void deleteVendor(Vendor vendor) {
        orgRepository.delete(vendor);
        simpleSourceBean.publishVendorChange("DELETE", vendor.getVendorId());
    }
}
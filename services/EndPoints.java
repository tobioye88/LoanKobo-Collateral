package com.services;

import com.dao.CollateralRepository;
import com.factory.AbstractFactory;
import com.factory.ProducerFactory;
import com.models.Collateral;
import com.models.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndPoints implements CollateralServiceInterface {

    @Autowired
    CollateralRepository collateralRepository;

    @Override
    public List<Collateral> getCollaterals(ID payload){
        Iterable<Collateral> result = collateralRepository.findAll();
        if (((List<Collateral>) result).isEmpty()) return new ArrayList<>();
        List<Collateral> values = (List<Collateral>) result;
        return values;
    }

    @Override
    public String addCollateral(Collateral payload){
        try {
            collateralRepository.save(payload);
            return "Successfull";
        }catch (Exception ex){
            System.out.println(ex);
            return "unsuccessfull";
        }
    }

    @Override
    public String deleteCollateral(ID payload){
        try {
            collateralRepository.delete(payload.Id);
            return "deleted successfully";
        }catch (Exception ex){
            return "delete unsuccessfull";
        }
    }

    @Override
    public String editCollateral(Collateral payload){

        AbstractFactory entityFactory = ProducerFactory.newEntityFactory();
        Collateral collateral = entityFactory.getCollateral();

        collateral.borrowersName = payload.borrowersName;
        collateral.condition = payload.condition;
        collateral.property_value = payload.property_value;
        collateral.status = payload.status;
        collateral.propertyName = payload.propertyName;
        collateral.id = payload.id;

        try {
            collateralRepository.save(collateral);
            return "successfull";
        }catch (Exception ex){
            return "unsuccessfull";
        }

    }


}

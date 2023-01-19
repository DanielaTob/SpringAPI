package com.project.marketapi.domain.service;

import com.project.marketapi.domain.Product;
import com.project.marketapi.domain.Purchase;
import com.project.marketapi.domain.repository.PurchaseRepository;
import com.project.marketapi.persistence.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    //1. Metodo que obtiene toda la lista de compras
    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    //2. Metodo que encuentra una compra por id
    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    //3. Metodo para guardar
    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }
}

package com.project.marketapi.domain.repository;

import com.project.marketapi.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    //1. Metodo que retorna una lista de compras
    List<Purchase> getAll();

    //2. Metodo que retorna una compra en especifico
    Optional<List<Purchase>> getByClient(String clientId);

    //3. Metodo que guarda una compra
    Purchase save(Purchase purchase);
}

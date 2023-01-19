package com.project.marketapi.persistence.mapper;

import com.project.marketapi.domain.Purchase;
import com.project.marketapi.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    //Metodo a partir de una compra - La clase Destino debe tener todos los mapeos
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items")
    })
    Purchase toPurchase(Compra compra);

    //Lista de Compras
    List<Purchase> toPurchases(List<Compra> compras);

    //Realiza el mapeo inverso de lo que se realizó arriba - Y si no los tiene todos, se deben ignorar aquí
    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}

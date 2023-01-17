package com.project.marketapi.persistence.mapper;

import com.project.marketapi.domain.Category;
import com.project.marketapi.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") //Componente de tipo spring
public interface CategoryMapper {

    //Mapper que nos retorna una categoria
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    //Conversion externa

    @InheritInverseConfiguration //Realiza el mapeo inverso de lo que se realizó arriba
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}

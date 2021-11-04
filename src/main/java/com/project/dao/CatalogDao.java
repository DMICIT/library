package com.project.dao;

import com.project.entities.Catalog;

public interface CatalogDao extends EntityDao<Catalog> {

    Catalog getCatalogByBookId(int bookId);
}

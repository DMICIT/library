package com.project.services;

import com.project.dao.CatalogDao;
import com.project.dao.impl.CatalogDaoImpl;
import com.project.entities.Catalog;
import com.project.web.data.CatalogData;

public class CatalogService {

    private CatalogDao catalogDao;

    public CatalogService(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    public CatalogService() {
        this(CatalogDaoImpl.getInstance());

    }

    public CatalogData getCatalogData(int id) {
        Catalog catalogByBookId = catalogDao.getCatalogByBookId(id);
        CatalogData catalogData = new CatalogData();
        if (catalogByBookId != null) {
            catalogData.setTotalQuantity(catalogByBookId.getCount());
        }
        return catalogData;
    }

    public void create(int bookId, int count) {
        Catalog catalog = new Catalog(bookId, count);
        catalogDao.create(catalog);
    }

    public void update(int bookId, int count) {
        Catalog catalog = catalogDao.getCatalogByBookId(bookId);
        catalog.setCount(count);
        catalogDao.update(catalog);
    }
}

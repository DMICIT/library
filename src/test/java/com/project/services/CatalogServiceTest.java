package com.project.services;

import com.project.dao.CatalogDao;
import com.project.dao.OrderDao;
import com.project.entities.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private CatalogDao catalogDao;

    @Mock
    private Catalog catalog;

    @Test
    void testCreate() {
        catalogService.create(1,2);
        verify(catalogDao).create(any(Catalog.class));
    }

    @Test
    void testUpdate() {
        when(catalogDao.getCatalogByBookId(2)).thenReturn(catalog);

        catalogService.update(2,2);
        verify(catalog).setCount(2);
        verify(catalogDao).update(catalog);
    }
}
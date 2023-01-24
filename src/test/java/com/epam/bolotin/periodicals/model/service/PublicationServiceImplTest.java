package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.db.repository.AccountRepository;
import com.epam.bolotin.periodicals.model.db.repository.PublicationRepository;
import com.epam.bolotin.periodicals.model.service.implementation.PublicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Viacheslav Bolotin
 * @date: 23.01.2023
 */
@ExtendWith(MockitoExtension.class)
class PublicationServiceImplTest {
    @Mock
    HttpServletRequest req;
    @Mock
    PublicationRepository publicationRepository;
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    PublicationServiceImpl publicationService;

    Publication publication = new Publication();

    @BeforeEach
    public void setUp() {

    }

    @Test
    void TestValidateAndFillPublicationSuccess() {

        PublicationService publicationServiceLocal = new PublicationServiceImpl(publicationRepository, accountRepository);
        Mockito.when(req.getParameter("publication_topic")).thenReturn("1");
        Mockito.when(req.getParameter("publication_title")).thenReturn("Publication One");
        Mockito.when(req.getParameter("publication_price")).thenReturn("10.56");
        Mockito.when(req.getParameter("publication_text")).thenReturn("Test text");
        boolean result = publicationServiceLocal.validateAndFillPublication(publication, req);
        assertEquals(1L, publication.getTopicId());
        assertEquals("Publication One", publication.getTitle());
        assertEquals(BigDecimal.valueOf(10.56), publication.getPrice());
        assertEquals("Test text", publication.getText());
        assertEquals(true, result);
    }

    @Test
    void TestValidateAndFillPublicationError() {

        PublicationService publicationServiceLocal = new PublicationServiceImpl(publicationRepository, accountRepository);
        Mockito.when(req.getParameter("publication_topic")).thenReturn("1");
        Mockito.when(req.getParameter("publication_title")).thenReturn("Publication One");
        Mockito.when(req.getParameter("publication_price")).thenReturn("10 F 56");
        boolean result = publicationServiceLocal.validateAndFillPublication(publication, req);
        assertEquals(1L, publication.getTopicId());
        assertEquals("Publication One", publication.getTitle());
        assertEquals(null, publication.getPrice());
        assertEquals(null, publication.getText());
        assertEquals(false, result);
    }

    @Test
    void TestSubscribeToPublication() throws AppException {

        publication.setPrice(BigDecimal.valueOf(50));
        Mockito.when(accountRepository.getAmountByUserId(1)).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(publicationRepository.isSubscribeToPublication(1, 1)).thenReturn(false);
        Mockito.when(publicationService.findByID(1)).thenReturn(publication);
        Mockito.when(publicationRepository.subscribeToPublication(1, 1, publication.getPrice())).
                thenReturn(0);
        int result = publicationService.subscribeToPublication(1, 1);
        Mockito.verify(publicationRepository, Mockito.times(1)).
                subscribeToPublication(1, 1, publication.getPrice());
        assertEquals(0, result);

        publication.setPrice(BigDecimal.valueOf(300));
        Mockito.when(accountRepository.getAmountByUserId(1)).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(publicationService.findByID(1)).thenReturn(publication);
        int result1 = publicationService.subscribeToPublication(1, 1);
        assertEquals(-2, result1);

        publication.setPrice(BigDecimal.valueOf(50));
        Mockito.when(accountRepository.getAmountByUserId(1)).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(publicationRepository.isSubscribeToPublication(1, 1)).thenReturn(true);
        Mockito.when(publicationService.findByID(1)).thenReturn(publication);
        int result2 = publicationService.subscribeToPublication(1, 1);
        assertEquals(-1, result2);
    }

    @Test
    void TestFindSize() {

        PublicationService publicationServiceLocal = new PublicationServiceImpl(publicationRepository, accountRepository);
        long topicFilter = 1L;
        String titleFilter = "Title";
        long userFilter = 1L;

        Mockito.when(publicationRepository.getSize(1L, "Title", 1L)).thenReturn(10L);
        long size = publicationServiceLocal.findSize(topicFilter, titleFilter, userFilter);

        assertEquals(10L, size);
    }

    @Test
    void TestFindByTopicId() {

        PublicationService publicationServiceLocal = new PublicationServiceImpl(publicationRepository, accountRepository);
        Mockito.when(publicationRepository.getByTopicId(1L)).thenReturn(publication);
        Publication result = publicationServiceLocal.findByTopicId(1L);

        assertEquals(publication, result);
    }

    @Test
    void TestFindById() {

        PublicationService publicationServiceLocal = new PublicationServiceImpl(publicationRepository, accountRepository);
        Mockito.when(publicationRepository.getById(1L)).thenReturn(publication);
        Publication result = publicationServiceLocal.findByID(1L);

        assertEquals(publication, result);
    }
}
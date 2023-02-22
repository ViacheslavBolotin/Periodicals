package com.epam.bolotin.periodicals.model.db.repository;

import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.service.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Publication;

import java.math.BigDecimal;
import java.util.List;

public interface PublicationRepository {

    void create(Publication publication) throws DBException;

    void update(Publication publication) throws DBException;

    void delete(long id) throws DBException;

    Publication getById(long id);

    Publication getByTopicId(long id);

    PublicationDto getFullInfoById(long id);
    List<Publication> getAll();

    List<PublicationDto> getAllFullInfo();

    long getSize(long topicFilter, String titleFilter, long userFilter);

    List<PublicationDto> getAllLimitSort(long from, long size,
                                         String sortType, long topicFilter, String titleFilter, long userFilter);

    int subscribeToPublication(long userId, long publicationId, BigDecimal sum) throws DBException;

    boolean isSubscribeToPublication(long userId, long publicationId);
}

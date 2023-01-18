package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Publication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PublicationService {

    void save(Publication publication) throws AppException;
    void update(Publication publication) throws AppException;
    void delete(long id) throws AppException;

    List<Publication> findAll();
    List<PublicationDto> findAllFullInfo();
    Publication findByID(long id);
    Publication findByTopicId(long id);

    PublicationDto findFullInfoByID(long id);

    List<PublicationDto> findAllLimitSort(long from, long size,
                                          String sortType, long topicFilter, String titleFilter, long userFilter);
    long findSize(long topicFilter, String titleFilter, long userFilter);

    boolean validateAndFillPublication(Publication publication, HttpServletRequest request);

    int subscribeToPublication(long userId, long publicationId) throws AppException;

}
